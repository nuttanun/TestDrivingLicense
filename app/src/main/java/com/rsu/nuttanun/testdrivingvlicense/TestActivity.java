package com.rsu.nuttanun.testdrivingvlicense;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class TestActivity extends AppCompatActivity {

    //Explicit
    private TextView textView;
    private ImageView imageView;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton;
    private String[] questionStrings, imageStrings, choice1Strings,
            choice2Strings, choice3Strings, choice4Strings, answerStrings;
    private int[] indexInts = new int[4];   // จำนวนข้อที่ต้องการให้ทดสอบ เปลี่ยน 4 ==> 50
    private int timesAnInt = 1;
    private int myTimes = 0, scoreAnInt = 0, loginAnInt, myIndex =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //Bind Widget
        textView = (TextView) findViewById(R.id.textView18);
        imageView = (ImageView) findViewById(R.id.imageView7);
        radioGroup = (RadioGroup) findViewById(R.id.ragChoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);

        SynQuestion synQuestion = new SynQuestion(this);
        synQuestion.execute();

        //radio controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton:
                        loginAnInt = 1;
                        break;
                    case R.id.radioButton2:
                        loginAnInt = 2;
                        break;
                    case R.id.radioButton3:
                        loginAnInt = 3;
                        break;
                    case R.id.radioButton4:
                        loginAnInt = 4;
                        break;
                }

            }   // on checkked
        });


    }   // Main Method

    private class SynQuestion extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private static final String urlJSON = "http://swiftcodingthai.com/toey/get_question_toey.php";


        public SynQuestion(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("21JulyV4", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                questionStrings = new String[jsonArray.length()];
                imageStrings = new String[jsonArray.length()];
                choice1Strings = new String[jsonArray.length()];
                choice2Strings = new String[jsonArray.length()];
                choice3Strings = new String[jsonArray.length()];
                choice4Strings = new String[jsonArray.length()];
                answerStrings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i += 1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    questionStrings[i] = jsonObject.getString("Question");
                    imageStrings[i] = jsonObject.getString("Image");
                    choice1Strings[i] = jsonObject.getString("Choice1");
                    choice2Strings[i] = jsonObject.getString("Choice2");
                    choice3Strings[i] = jsonObject.getString("Choice3");
                    choice4Strings[i] = jsonObject.getString("Choice4");
                    answerStrings[i] = jsonObject.getString("Answer");

                }   // for


                for (int i = 0; i < indexInts.length; i += 1) {

                    Random random = new Random();
                    indexInts[i] = random.nextInt(questionStrings.length) + 1;
                    Log.d("21JulyV5", "index(" + i + ") = " + indexInts[i]);

                }   // for

                //Show Times 1
                showText(indexInts[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // onPost

    }   // SynQuestion Class

    private void showText(int indexInt) {

        textView.setText(Integer.toString(myTimes+=1) + ". " + questionStrings[indexInt]);

        if (imageStrings[indexInt].length() != 0) {

            try {

                Picasso.with(this)
                        .load(imageStrings[indexInt])
                        .resize(400, 300)
                        .into(imageView);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // if

        choice1RadioButton.setText(choice1Strings[indexInt]);
        choice2RadioButton.setText(choice2Strings[indexInt]);
        choice3RadioButton.setText(choice3Strings[indexInt]);
        choice4RadioButton.setText(choice4Strings[indexInt]);


    }   // showText


    public void clickAnswer(View view) {

        //Check Choose
        if (!(choice1RadioButton.isChecked() || choice2RadioButton.isChecked() ||
                choice3RadioButton.isChecked() || choice4RadioButton.isChecked())) {
            //No Choose
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ยังไม่ได้เลือกคำตอบ",
                    "โปรดเลือกคำตอบ ด้วยคะ");

        } else if (timesAnInt < indexInts.length) {

            if (loginAnInt == Integer.parseInt(answerStrings[myIndex])) {
                scoreAnInt += 1;
            }   //if

            myIndex += 1;
            Log.d("21julyV5", "score ==>" + scoreAnInt);

            showText(timesAnInt);
            timesAnInt += 1;
            radioGroup.clearCheck();

        } else {

            Intent intent = new Intent(TestActivity.this, ConfirmScoreActivity.class);
            startActivity(intent);

        }   // if


    }   // clickAnswer

}   // Main Class