package com.example.maxmamuta.proguli.Main.Timeline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.maxmamuta.proguli.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.SecureRandom;
import java.sql.SQLException;

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

    ProgressDialog mProgressDialog;

    @SuppressWarnings("VisibleForTests")
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

 //       mProgressDialog = new ProgressDialog(this);
  //      mProgressDialog.setMessage("Uploading...");
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "/photos/" + getToken(12) + ".jpg";
                StorageReference ref = FirebaseStorage.getInstance().getReference(path);

//                mProgressDialog.show();
                UploadTask upload = ref.putBytes(CachePhoto.getInstance().getPhotoCache());
                upload.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
             //           mProgressDialog.dismiss();
                        photo_url = String.valueOf(taskSnapshot.getDownloadUrl());
                        Log.e("Download Url", photo_url);
                        RequestBody formBody = new FormBody.Builder()
                                .add("title", title.getText().toString())
                                .add("description", description.getText().toString())
                                .add("photo_url", photo_url)
                                .build();
                        connection.postData("http://178.62.238.65:8841/api/tasks/add/", formBody, getApplicationContext());
                        finish();
                    }
                });
            }
        });

    }

    public static String getToken(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
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
