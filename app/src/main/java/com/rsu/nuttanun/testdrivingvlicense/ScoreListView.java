package com.rsu.nuttanun.testdrivingvlicense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static com.rsu.nuttanun.testdrivingvlicense.R.id.textView22;

public class ScoreListView extends AppCompatActivity {

    // Explicit
    private TextView textView;
    private ListView listView;
    private String[] loginStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list_view);

        //Bind Widget
        textView = (TextView) findViewById(R.id.textView22);
        listView = (ListView) findViewById(R.id.listView2);


        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("login");

        // show view
        textView.setText(loginStrings[1]);

        //Create Listview
        createListView();


    }   //main method

    private void createListView() {

        String urlPHP = "http://swiftcodingthai.com/toey/get_score_where.php";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id_login", loginStrings[0])
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

                String strResponse = response.body().string();
                Log.d("10AugV2", "strResponse ==>" + strResponse);

                try {

                    JSONArray jsonArray = new JSONArray(strResponse);

                    String[] dateStrings = new String[jsonArray.length()];
                    String[] scoreStrings = new String[jsonArray.length()];

                    for (int i=0;i<jsonArray.length();i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dateStrings[i] = jsonObject.getString("Date");
                        scoreStrings[i] = jsonObject.getString("Score") + " คะแนน";



                    }   //for

                    CoaurseAdapter coaurseAdapter = new CoaurseAdapter(ScoreListView.this, 1,
                            dateStrings, scoreStrings);
                    listView.setAdapter(coaurseAdapter);


                } catch (Exception e) {
                    e.printStackTrace();

                }



            }   // onRespose
        });


    }   // createlistview


    public void clickExit(View view) {
        finish();

    }

    public void clickHome(View view) {

        Intent intent = new Intent(ScoreListView.this, ServiceActivity.class);
        intent.putExtra("login", loginStrings);
        startActivity(intent);
        finish();

    }

}   //main class
