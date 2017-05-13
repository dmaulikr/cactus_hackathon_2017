package com.example.maxmamuta.proguli.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.maxmamuta.proguli.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 5/13/2017.
 */

public class Menu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_menu, parent, false);
        ListView lv = (ListView) v.findViewById(R.id.listView);
        List<String> aa = new ArrayList<>();
        aa.add("a");
        aa.add("b");
        aa.add("c");
        lv.setAdapter(new TeamListAdapter(getActivity().getApplicationContext(), aa));
        return v;
    }

    public class TeamListAdapter extends ArrayAdapter<String>
    {

        public TeamListAdapter(Context context, List<String> items)
        {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.team, parent, false);
            return  convertView;
        }
    }

}
