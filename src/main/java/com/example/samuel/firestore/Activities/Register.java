package com.example.samuel.firestore.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.samuel.firestore.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void RegisterNewUser(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
     public void Login(View view){
         startActivity(new Intent(this,Login.class));
     }
}
