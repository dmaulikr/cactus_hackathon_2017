package com.example.maxmamuta.proguli.Auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maxmamuta.proguli.R;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends AppCompatActivity {

    EditText name, pass, email;
    Button register, gotologin;

    PGAuth connection = new PGAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);

        register = (Button) findViewById(R.id.register);
        gotologin = (Button) findViewById(R.id.gotologin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody formBody = new FormBody.Builder()
                        .add("name", name.getText().toString())
                        .add("email", email.getText().toString())
                        .add("password", pass.getText().toString())
                        .build();
                connection.postData("http://178.62.238.65:8841/register/", formBody, getApplicationContext());
            }
        });

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
