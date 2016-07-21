package com.rsu.nuttanun.testdrivingvlicense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailCoaurseActivity extends AppCompatActivity {

    //Explicit
    private TextView titlTextView, detailTextView;
    private ImageView imageView;
    private String titleString;
    private String detailString;
    private String imageString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coaurse);

        //  Intial widget

        titlTextView = (TextView) findViewById(R.id.textView15);
        detailTextView = (TextView) findViewById(R.id.textView16);
        imageView = (ImageView) findViewById(R.id.imageView6);

        //get value from Intent

        titleString = getIntent().getStringExtra("Title");
        detailString = getIntent().getStringExtra("Detail");
        imageString = getIntent().getStringExtra("Image");



        //Show text
        titlTextView.setText(titleString);
        detailTextView.setText(detailString);

        //Show image

        if (imageString .length() !=0) {

            try {
                Picasso.with(this)
                        .load(imageString)
                        .resize(400, 300)
                        .into(imageView);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // if


    }   // main method

    public void clickBackDetailCoaurse(View view) {
        finish();
    }

}   //main class
