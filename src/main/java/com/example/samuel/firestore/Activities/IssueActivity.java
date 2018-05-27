package com.example.samuel.firestore.Activities;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.samuel.firestore.PagerAdapter;
import com.example.samuel.firestore.R;
import com.example.samuel.firestore.issuesInterface;

public class IssueActivity extends AppCompatActivity implements issuesInterface {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private static final String TAG = "IssueActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("ISSUES");
        tabLayout.getTabAt(1).setText("PROJECT");
//        if(getIntent() != null) {
//            String word = getIntent().getStringExtra("stuff");
//            Log.d(TAG, "onCreate: word is " +word);
//
//            viewPager.setCurrentItem(1);
//            Toast.makeText(this,word,Toast.LENGTH_LONG).show();
//        }



    }


    @Override
    public void buildSnackBarMessage(String message) {
        Log.d(TAG, "buildSnackBarMessage: message received " + message);
        Snackbar bar = Snackbar.make(findViewById(R.id.cord_layout),message,Snackbar.LENGTH_LONG);
        bar.show();
    }
}
