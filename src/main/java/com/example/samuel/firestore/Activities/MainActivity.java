package com.example.samuel.firestore.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.samuel.firestore.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // startActivity(new Intent(this,IssueActivity.class));
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle("Tracker");
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        setUpAuth();

    }

    private void setUpAuth() {
      authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    //user is logged in
                }
                else{
                    startActivity(new Intent(MainActivity.this,Login.class));
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       int id =  item.getItemId();
       if(id == R.id.issuse_Activity){
           startActivity(new Intent(this,IssueActivity.class));
       }
       else {
           mAuth.signOut();
       }
        return super.onOptionsItemSelected(item);
    }
}
