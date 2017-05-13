package com.example.maxmamuta.proguli;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);

        ListView lv = (ListView) findViewById(R.id.listView);
        List<String> aa = new ArrayList<>();
        aa.add("a");
        aa.add("b");
        aa.add("c");
        lv.setAdapter(new ContactListAdapter(getApplicationContext(), aa));
    }

    public class ContactListAdapter extends ArrayAdapter<String>
    {

        public ContactListAdapter(Context context, List<String> items)
        {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView = null;
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line, parent, false);
            return  convertView;
        }
    }
}
