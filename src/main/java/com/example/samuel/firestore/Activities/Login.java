package com.example.samuel.firestore.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.samuel.firestore.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LoginUser(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void Register(View view) {
        startActivity(new Intent(this,Register.class));
    }
}
