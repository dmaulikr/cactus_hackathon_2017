package com.example.maxmamuta.proguli.Main.Projects;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maxmamuta.proguli.GlobalCache;
import com.example.maxmamuta.proguli.Main.getGroups;
import com.example.maxmamuta.proguli.R;

import java.util.ArrayList;

/**
 * Created by Maxim on 5/14/2017.
 */

public class Projects extends Fragment {

    ArrayList<getGroups> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_project, container, false);

        GlobalCache gc = GlobalCache.getInstance();
        list = gc.getList();

        TextView tx1 = (TextView)v.findViewById(R.id.o1);
        tx1.setText(list.get(0).name);

        TextView tx2 = (TextView)v.findViewById(R.id.o2);
        tx2.setText(list.get(1).name);

        TextView tx3 = (TextView)v.findViewById(R.id.o3);
        tx3.setText(list.get(2).name);

        TextView tx4 = (TextView)v.findViewById(R.id.o4);
        tx4.setText(list.get(3).name);

        TextView tx5 = (TextView)v.findViewById(R.id.o5);
        tx5.setText(list.get(4).name);

        TextView tx6 = (TextView)v.findViewById(R.id.o6);
        tx6.setText(list.get(5).name);

        RelativeLayout re1 = (RelativeLayout) v.findViewById(R.id.physics);
        re1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Lesson.class);
                i.putExtra("aa", "1");
                startActivity(i);
            }
        });

        RelativeLayout re2 = (RelativeLayout) v.findViewById(R.id.math);
        re2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Lesson.class);
                i.putExtra("aa", "2");
                startActivity(i);
            }
        });

        RelativeLayout re3 = (RelativeLayout) v.findViewById(R.id.oop);
        re3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Lesson.class);
                i.putExtra("aa", "3");
                startActivity(i);
            }
        });

        RelativeLayout re4 = (RelativeLayout) v.findViewById(R.id.nummeth);
        re4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Lesson.class);
                i.putExtra("aa", "4");
                startActivity(i);
            }
        });

        RelativeLayout re5 = (RelativeLayout) v.findViewById(R.id.discrete);
        re5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Lesson.class);
                i.putExtra("aa", "5");
                startActivity(i);
            }
        });

        RelativeLayout re6 = (RelativeLayout) v.findViewById(R.id.fp);
        re6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), Lesson.class);
                i.putExtra("aa", "6");
                startActivity(i);
            }
        });

        return v;
    }
}
