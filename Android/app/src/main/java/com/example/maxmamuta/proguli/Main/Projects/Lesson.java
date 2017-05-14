package com.example.maxmamuta.proguli.Main.Projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maxmamuta.proguli.GlobalCache;
import com.example.maxmamuta.proguli.Main.getGroups;
import com.example.maxmamuta.proguli.R;

/**
 * Created by Maxim on 5/14/2017.
 */

public class Lesson extends AppCompatActivity {

    ImageView back;
    TextView name;
    LinearLayout l1;
    LinearLayout l2;
    getGroups a;
    int aa = 0, bb = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lesson);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final GlobalCache cac = GlobalCache.getInstance();
        final String extra = getIntent().getStringExtra("aa");
        name = (TextView) findViewById(R.id.name);
        l1  = (LinearLayout) findViewById(R.id.lesv);
        l2  = (LinearLayout) findViewById(R.id.labv);
        a = cac.getList().get(Integer.parseInt(extra)-1);
        name.setText(a.name);
        TextView teacher = (TextView) findViewById(R.id.teacher);
        teacher.setText(a.teacher);
        l1.getLayoutParams().width = a.lessons_visited * 5;
        l1.requestLayout();
        l2.getLayoutParams().width = a.labs_passed * 6;
        l2.requestLayout();
        aa = a.lessons_visited;
        bb = a.labs_passed;
    }
}
