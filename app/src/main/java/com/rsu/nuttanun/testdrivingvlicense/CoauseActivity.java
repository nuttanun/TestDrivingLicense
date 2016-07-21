package com.rsu.nuttanun.testdrivingvlicense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coause);
    }   //main method

    public void clickBackCoaurse(View view) {
        finish();
    }

}   //main class
