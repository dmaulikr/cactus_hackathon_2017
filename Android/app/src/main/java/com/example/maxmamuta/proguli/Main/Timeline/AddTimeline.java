package com.example.maxmamuta.proguli.Main.Timeline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxmamuta.proguli.R;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Maxim on 5/13/2017.
 */

public class AddTimeline extends AppCompatActivity {

    EditText title, description;

    Button create, photoset;

    String photo_url;

    PGTimeline connection = new PGTimeline();

    TextView OK, Exit;

    ImageView photo;

    boolean isTaken = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_timeline);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        create = (Button) findViewById(R.id.create);
        OK = (TextView) findViewById(R.id.OK);
        Exit = (TextView) findViewById(R.id.Exit);
        photo = (ImageView) findViewById(R.id.photo);
        photoset = (Button) findViewById(R.id.photoset);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MakePhoto.class));
                isTaken = true;
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody formBody = new FormBody.Builder()
                        .add("title", title.getText().toString())
                        .add("description", description.getText().toString())
                        .add("photo_url", photo_url)
                        .build();
                connection.postData("http://178.62.238.65:8841/addPost/", formBody, getApplicationContext());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isTaken) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inDither = true;
            byte [] picture = CachePhoto.getInstance().getPhotoCache();
            Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length, options);
            photo.setImageBitmap(bitmap);
        }
    }
}
