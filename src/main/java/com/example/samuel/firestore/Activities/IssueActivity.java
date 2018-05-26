package com.example.samuel.firestore.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.samuel.firestore.PagerAdapter;
import com.example.samuel.firestore.R;

public class IssueActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

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



    }
}
