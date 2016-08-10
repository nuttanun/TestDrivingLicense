package com.rsu.nuttanun.testdrivingvlicense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmScoreActivity extends AppCompatActivity {


    ///explicit

    private String[] loginStrings;
    private String scoreString, dateString;
    private TextView nameTextView, dateTextView, scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_score);


        // Bind Widget
        nameTextView = (TextView) findViewById(R.id.textView19);
        dateTextView = (TextView) findViewById(R.id.textView20);
        scoreTextView = (TextView) findViewById(R.id.textView21);



        // get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("login");
        scoreString = getIntent().getStringExtra("Score");

        // Get Current time

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateString = dateFormat.format(calendar.getTime());

        // Show View
        nameTextView.setText(loginStrings[1]);
        scoreTextView.setText("คะแนนของคุณ = " + scoreString);
        dateTextView.setText(dateString);


    } // main method

    public void clickOKConfirm(View view) {

        String urlPHP = "http://swiftcodingthai.com/toey/add_score.php";

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id_login", loginStrings[0])
                .add("Date", dateString)
                .add("Score", scoreString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                Intent intent = new Intent(ConfirmScoreActivity.this, ScoreListView.class);
                intent.putExtra("login", loginStrings);
                startActivity(intent);
                finish();
            }
        });


    } // Click ok




}   // main class
