package com.example.maxmamuta.proguli.Main.Timeline;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Maxim on 5/13/2017.
 */

public class PGTimeline {

    OkHttpClient client = new OkHttpClient();

    Context appContext;

    String getData(String url, Context appContext) {
        this.appContext = appContext;
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public void postData(String url, RequestBody formBody, Context appContext) {
        this.appContext = appContext;
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(loginCallback);
    }

    Callback loginCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            try {
                Log.i("TAG", "login failed: " + call.execute().code());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String loginResponseString = response.body().string();
            Log.i("TAG", "loginResponseString: " + loginResponseString);
        }
    };
}
