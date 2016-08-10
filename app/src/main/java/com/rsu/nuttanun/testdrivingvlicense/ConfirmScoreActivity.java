package com.rsu.nuttanun.testdrivingvlicense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmScoreActivity extends AppCompatActivity {


    ///explicit

    private String[] loginStrings;
    private String scoreString;
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

        // Show View
        nameTextView.setText(loginStrings[1] + " " + loginStrings[2]);
        scoreTextView.setText("คะแนนของคุณ = " + scoreString);


    } // main method

    public void clickOKConfirm(View view) {

    }




}   // main class
