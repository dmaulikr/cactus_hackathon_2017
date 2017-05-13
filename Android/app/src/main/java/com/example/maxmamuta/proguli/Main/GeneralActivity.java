package com.example.maxmamuta.proguli.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.maxmamuta.proguli.Main.Fragments.Menu;
import com.example.maxmamuta.proguli.Main.Timeline.Timeline;
import com.example.maxmamuta.proguli.R;

/**
 * Created by maxmamuta on 5/13/17.
 */

public class GeneralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        FragmentManager fm = getSupportFragmentManager();

        Fragment menuFragment = fm.findFragmentById(R.id.menuFrame);
        if (menuFragment == null)
            fm.beginTransaction().add(R.id.menuFrame, new Menu()).commit();

        Fragment timelineFragment = fm.findFragmentById(R.id.mainFrame);
        if (timelineFragment == null)
            fm.beginTransaction().add(R.id.mainFrame, new Timeline()).commit();

    }
}
