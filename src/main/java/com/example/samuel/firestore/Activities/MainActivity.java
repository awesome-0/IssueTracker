package com.example.samuel.firestore.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.samuel.firestore.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // startActivity(new Intent(this,IssueActivity.class));
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle("Tracker");
        setSupportActionBar(toolbar);

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
           startActivity(new Intent(this,Login.class));
       }
        return super.onOptionsItemSelected(item);
    }
}
