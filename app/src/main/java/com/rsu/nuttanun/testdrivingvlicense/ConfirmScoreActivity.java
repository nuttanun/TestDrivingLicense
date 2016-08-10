package com.rsu.nuttanun.testdrivingvlicense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

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
        nameTextView.setText(loginStrings[1] + " " + loginStrings[2]);
        scoreTextView.setText("คะแนนของคุณ = " + scoreString);
        dateTextView.setText(dateString);


    } // main method

    public void clickOKConfirm(View view) {

    }




}   // main class
