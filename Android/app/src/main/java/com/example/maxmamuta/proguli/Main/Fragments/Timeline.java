package com.example.maxmamuta.proguli.Main.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.maxmamuta.proguli.R;

/**
 * Created by Maxim on 5/13/2017.
 */

public class Timeline extends Fragment {

    View vi;
    LinearLayout li;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        vi = inflater.inflate(R.layout.layout_timeline, parent, false);


        ImageView im = (ImageView) vi.findViewById(R.id.timeLineGo);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.to_right);
                anim.setFillAfter(true);
                vi.startAnimation(anim);
                li.setEnabled(true);
                li.setClickable(true);
            }
        });

        li = (LinearLayout) vi.findViewById(R.id.timeLineBack);
        li.setEnabled(false);
        li.setClickable(false);
        li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.to_left);
                anim.setFillAfter(true);
                vi.startAnimation(anim);
                li.setEnabled(false);
                li.setClickable(false);
            }
        });

        return vi;
    }

}