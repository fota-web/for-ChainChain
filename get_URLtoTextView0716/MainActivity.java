package com.codavel.howto_okhttp;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "test";

    public static String a;


    public class data {
        public String MessageCode;
        String MemberKey;

        public data() {

        }

        public void setMessageCode(String MessageCode) {
            this.MessageCode = MessageCode;
        }

        public String getMessageCode() {
            return MessageCode;
        }

        public void setMemberKey(String MemberKey) {
            this.MemberKey = MemberKey;
        }

        public String getMemberKey() {
            return MemberKey;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient();

        // POST
        JsonObject postData = new JsonObject();
        postData.addProperty("Token", "202007161151006E7B02AAE42DBC2E5DE26EF8DC31DA85CC22D351B5BFCDF3521F6DC21D1B6F1C");
        postData.addProperty("Account", "DDDDD");
        postData.addProperty("Password", "DDDDD");

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody postBody = RequestBody.create(JSON, postData.toString());
        Request post = new Request.Builder()
                .url("https://www.netown.tw/app/webapi/Login/")
                .post(postBody)
                .build();
        final TextView text = findViewById(R.id.text);
        final data[] data1 = new data[1];

        client.newCall(post).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    // Log.i("data01", responseBody.string());

                    Gson gson = new Gson();
//                    data data1 = gson.fromJson(responseBody.string(), data.class);

                    data1[0] = gson.fromJson(responseBody.string(), data.class);
//                    Log.e(TAG, "data1: " + data1.getMessageCode());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        final TextView txtTest = findViewById(R.id.text);
        Handler mTimeHandler = new Handler() {
            @SuppressLint("HandlerLeak")
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 0) {
                    Log.e("data1",""+data1);
                    txtTest.setText(String.valueOf(data1[0].getMessageCode()));
                    sendEmptyMessageDelayed(0, 1000);
                }
            }
        };

        mTimeHandler.sendEmptyMessageDelayed(0,1000);

    }

}

