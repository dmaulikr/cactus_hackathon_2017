package com.example.maxmamuta.proguli;

import android.content.Context;
import android.util.Log;

import com.example.maxmamuta.proguli.Main.Timeline.PGTimeline;
import com.example.maxmamuta.proguli.Main.Timeline.Timeline;
import com.example.maxmamuta.proguli.Main.getGroups;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.maxmamuta.proguli.Main.Timeline.Timeline.isBe;

/**
 * Created by Maxim on 5/14/2017.
 */

public class GlobalCache {

    OkHttpClient client = new OkHttpClient();

    ArrayList<getGroups> list = new ArrayList<>();

    public ArrayList<getGroups> getList() {
        return list;
    }

    static GlobalCache singleton = null;

    public static GlobalCache getInstance() {
        if (singleton == null) {
            singleton = new GlobalCache();
        }
        return singleton;
    }

    private GlobalCache() {
        getGroupList();
    }

    private void getGroupList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isBe) {
                    String aa = getData("http://178.62.238.65:8841/api/subjects/");
                    Log.e("TAG", aa);
                    Gson gson = new Gson();
                    getGroups[] it = gson.fromJson(aa, getGroups[].class);
                    list = new ArrayList<getGroups>(Arrays.asList(it));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    String getData(String url) {
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

}
