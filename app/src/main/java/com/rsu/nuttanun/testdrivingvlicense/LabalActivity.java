package com.rsu.nuttanun.testdrivingvlicense;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class LabalActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labal);

        listView = (ListView) findViewById(R.id.listView3);


        SynLabel synLabel = new SynLabel(this, listView);
        synLabel.execute();



    }   // main method


    private class SynLabel extends AsyncTask<Void, Void, String> {

        private Context context;
        private ListView myListView;
        private static final String urlJSON = "http://www.swiftcodingthai.com/toey/get_label.php";

        public SynLabel(Context context, ListView myListView) {
            this.context = context;
            this.myListView = myListView;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();



            } catch (Exception e) {
                return null;
            }



        }   //Doinback

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("10AugV4", "JSON ==>" + s);

        }   //onPost
    }





}   //main class
