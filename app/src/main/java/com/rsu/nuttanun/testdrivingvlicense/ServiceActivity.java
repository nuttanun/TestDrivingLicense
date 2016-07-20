package com.rsu.nuttanun.testdrivingvlicense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private String[] loginStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("login");
        Log.d("20JulyV3", "name Login ==>" + loginStrings[1]);



    }   //main Method
}   // Main class
