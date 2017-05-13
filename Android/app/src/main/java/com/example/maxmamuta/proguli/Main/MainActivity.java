package com.example.maxmamuta.proguli.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.maxmamuta.proguli.Auth.RegisterActivity;
import com.example.maxmamuta.proguli.Main.Timeline.MakePhoto;
import com.example.maxmamuta.proguli.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), GeneralActivity.class));
    }

}
