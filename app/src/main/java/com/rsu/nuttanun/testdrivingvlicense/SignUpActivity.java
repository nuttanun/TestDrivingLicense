package com.rsu.nuttanun.testdrivingvlicense;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    // Explicit
    private EditText nameEditText
    ,surnameEditText,ageEditText,userEditText,
    passwordEditText;

    private String nameString
    ,surnameString,ageString,userString,passwordString;
    private String urlPHP = "http://swiftcodingthai.com/toey/add_user_toey.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initail Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2
        );
        ageEditText = (EditText) findViewById(R.id.editText3
        );
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);


    }//  Main mehod


    public void clickSignUpSign(View view) {

     // get value from edit text

        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        ageString = ageEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();


        // check space

        if (nameString.equals("") || surnameString.equals("") ||
                ageString.equals("")|| userString.equals("")  ||passwordString.equals("" )) {
                //have space

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกข้อมูล ทุกช่องค่ะ");

        } else {
            // no space
            confirmUser();


        }   //if




    } //clickSign

    private void confirmUser() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.nobita48);
        builder.setTitle("โปรดตรวจข้อมูล");
        builder.setMessage("Name = " +nameString + "\n"+
        "Surname = " + surnameString + "\n"+
        "Age = " + ageString+"\n"+
        "User = " + userString +"\n"+
        "Password = " + passwordString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                upLoadNewUser();
                dialog.dismiss();
            }
        });
        builder.show();

    }   // confrimuser

    private void upLoadNewUser() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("Surname", surnameString)
                .add("Age", ageString)
                .add("User", userString)
                .add("Password", passwordString)
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
                finish();

            }
        });



    }   //Upload


}       //main class
