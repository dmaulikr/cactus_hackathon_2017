package com.example.maxmamuta.proguli.Main.Timeline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maxmamuta.proguli.Main.Timeline.PGTimeline;
import com.example.maxmamuta.proguli.Main.Timeline.TimelineItem;
import com.example.maxmamuta.proguli.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maxim on 5/14/2017.
 */

public class Groups extends Fragment {

    ListView listView;
    public static PGTimeline connection = new PGTimeline();
    ArrayList<TimelineItem> items = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            listView.setAdapter(new TimelineAdapter(items));
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_group, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        dada();

        ImageView me = (ImageView) v.findViewById(R.id.aaaa);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), AddGroupline.class));
            }
        });

        return v;
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

    private int count = 0;

    private void dada() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(true) {
                                String arr = connection.getData("http://178.62.238.65:8841/api/group/tasks/", getActivity().getApplicationContext());
                                Log.e("TAG", arr);
                                Gson gson = new Gson();
                                TimelineItem itemes[] = gson.fromJson(arr, TimelineItem[].class);
                                items = new ArrayList<>(Arrays.asList(itemes));
                                if (items.size() > count) {
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
        }).start();
    }
}
