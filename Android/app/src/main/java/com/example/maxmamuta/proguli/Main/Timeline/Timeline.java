package com.example.maxmamuta.proguli.Main.Timeline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maxmamuta.proguli.GlobalCache;
import com.example.maxmamuta.proguli.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Maxim on 5/13/2017.
 */

public class Timeline extends Fragment {

    View vi;
    LinearLayout li;
    ListView listView;
    ArrayList<TimelineItem> items = new ArrayList<>();
    private int count = 0;

    public static PGTimeline connection = new PGTimeline();

    public static boolean isBe = false;
    boolean isGet = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            listView.setAdapter(new TimelineAdapter(items));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        vi = inflater.inflate(R.layout.layout_timeline, parent, false);

        listView = (ListView) vi.findViewById(R.id.listView);
        isBe = true;
        setListView();

        ImageView im = (ImageView) vi.findViewById(R.id.timeLineGo);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.to_right);
                anim.setFillAfter(true);
                vi.startAnimation(anim);
                li.setEnabled(true);
                li.setClickable(true);
                li.bringToFront();
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

        ImageView me = (ImageView) vi.findViewById(R.id.showAddDialog);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), AddTimeline.class));
            }
        });
        GlobalCache gc = GlobalCache.getInstance();

        return vi;
    }

    @Override
    public void onResume() {
        super.onResume();
        isBe = true;
        setListView();
    }

    @Override
    public void onPause() {
        super.onPause();
        isBe = false;
    }

    private void setListView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isBe) {
                    String arr = connection.getData("http://178.62.238.65:8841/api/timeline/", getActivity().getApplicationContext());
                    Log.e("TAG", arr);
                    Gson gson = new Gson();
                    TimelineItem itemes[] = gson.fromJson(arr, TimelineItem[].class);
                    items = new ArrayList<>(Arrays.asList(itemes));

                    if(items.size() > count) {
                        handler.sendEmptyMessage(0);
                        count = items.size();
                    }

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private class TimelineAdapter extends ArrayAdapter<TimelineItem> {
        public TimelineAdapter(ArrayList<TimelineItem> messages) {
            super(getActivity().getApplicationContext(), 0, messages);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TimelineItem coc = getItem(position);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line, parent, false);
            if (coc != null) {
                TextView title = (TextView) convertView.findViewById(R.id.title);
                title.setText(coc.title);
                TextView description = (TextView) convertView.findViewById(R.id.description);
                description.setText(coc.description);
                ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
                Picasso.with(getActivity().getApplicationContext()).load(coc.photo_url).into(image);
                TextView clock = (TextView) convertView.findViewById(R.id.clock);
                clock.setText(coc.time);
            }

            return convertView;
        }
    }

}