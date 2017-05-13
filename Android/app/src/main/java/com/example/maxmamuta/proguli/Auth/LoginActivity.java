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

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    Button login, gotoregister;

    PGAuth connection = new PGAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);

        login = (Button) findViewById(R.id.login);
        gotoregister = (Button) findViewById(R.id.gotoregister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody formBody = new FormBody.Builder()
                        .add("email", email.getText().toString())
                        .add("password", pass.getText().toString())
                        .build();
                connection.postData("http://178.62.238.65:8841/login/", formBody, getApplicationContext());
            }
        });

        gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}
