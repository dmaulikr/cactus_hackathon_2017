package com.example.maxmamuta.proguli.Main;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maxmamuta.proguli.Main.Attachment.Attachment;
import com.example.maxmamuta.proguli.Main.Groups.Groups;
import com.example.maxmamuta.proguli.Main.Projects.Projects;
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

        final FragmentManager fm = getSupportFragmentManager();

        final Fragment menuFragment = fm.findFragmentById(R.id.menuFrame);
        if (menuFragment == null)
            fm.beginTransaction().add(R.id.menuFrame, new Menu()).commit();

        final Fragment timelineFragment = fm.findFragmentById(R.id.mainFrame);
        if (timelineFragment == null)
            fm.beginTransaction().add(R.id.mainFrame, new Timeline()).commit();

        final ImageView timelineImage = (ImageView)findViewById(R.id.timelineImage);
        final ImageView projectImage = (ImageView)findViewById(R.id.projectImage);
        final ImageView attachmentImage = (ImageView)findViewById(R.id.attachmentImage);
        final ImageView groupImage = (ImageView)findViewById(R.id.groupImage);

        final TextView timelineText = (TextView)findViewById(R.id.timelineText);
        final TextView projectText = (TextView)findViewById(R.id.projectText);
        final TextView attachmentText = (TextView)findViewById(R.id.attachmentText);
        final TextView groupText = (TextView)findViewById(R.id.groupText);

        final LinearLayout timelineBlock = (LinearLayout)findViewById(R.id.timelineBlock);
        timelineBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.mainFrame, new Timeline()).commit();
                timelineImage.setColorFilter(getResources().getColor(R.color.colorBack));
                timelineText.setTextColor(getResources().getColor(R.color.colorBack));
                projectImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                projectText.setTextColor(getResources().getColor(R.color.colorDisable));
                attachmentImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                attachmentText.setTextColor(getResources().getColor(R.color.colorDisable));
                groupImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                groupText.setTextColor(getResources().getColor(R.color.colorDisable));
            }
        });
        LinearLayout projectBlock = (LinearLayout)findViewById(R.id.projectBlock);
        projectBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.mainFrame, new Projects()).commit();
                projectImage.setColorFilter(getResources().getColor(R.color.colorBack));
                projectText.setTextColor(getResources().getColor(R.color.colorBack));
                timelineImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                timelineText.setTextColor(getResources().getColor(R.color.colorDisable));
                attachmentImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                attachmentText.setTextColor(getResources().getColor(R.color.colorDisable));
                groupImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                groupText.setTextColor(getResources().getColor(R.color.colorDisable));
            }
        });
        LinearLayout attachmentBlock = (LinearLayout)findViewById(R.id.attachmentBlock);
        attachmentBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.mainFrame, new Attachment()).commit();
                attachmentImage.setColorFilter(getResources().getColor(R.color.colorBack));
                attachmentText.setTextColor(getResources().getColor(R.color.colorBack));
                projectImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                projectText.setTextColor(getResources().getColor(R.color.colorDisable));
                timelineImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                timelineText.setTextColor(getResources().getColor(R.color.colorDisable));
                groupImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                groupText.setTextColor(getResources().getColor(R.color.colorDisable));
            }
        });

        LinearLayout groupBlock = (LinearLayout)findViewById(R.id.groupBlock);
        groupBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.mainFrame, new Groups()).commit();
                groupImage.setColorFilter(getResources().getColor(R.color.colorBack));
                groupText.setTextColor(getResources().getColor(R.color.colorBack));
                projectImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                projectText.setTextColor(getResources().getColor(R.color.colorDisable));
                timelineImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                timelineText.setTextColor(getResources().getColor(R.color.colorDisable));
                attachmentImage.setColorFilter(getResources().getColor(R.color.colorDisable));
                attachmentText.setTextColor(getResources().getColor(R.color.colorDisable));
            }
        });

    }
}
