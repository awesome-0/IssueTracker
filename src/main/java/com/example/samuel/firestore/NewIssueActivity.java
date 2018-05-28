package com.example.samuel.firestore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.samuel.firestore.models.IssuesSpinnerItems;

public class NewIssueActivity extends AppCompatActivity {
    Spinner taskSpinner;
    TaskAdapter taskAdapter;
    TaskAdapter priorityAdapter;
    Spinner prioritySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_issue);


        setUpWidgets();
        taskAdapter = new TaskAdapter(NewIssueActivity.this, R.layout.spinner_layout, IssuesSpinnerItems.taskImages,IssuesSpinnerItems.taskNames);
        priorityAdapter = new TaskAdapter(NewIssueActivity.this,R.layout.spinner_layout,IssuesSpinnerItems.priorityImages,IssuesSpinnerItems.priorityNames);


        // taskAdapter.setDropDownViewResource( R.layout.spinner_layout);
        taskSpinner.setAdapter(taskAdapter);
        prioritySpinner.setAdapter(priorityAdapter);



        taskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(NewIssueActivity.this,IssuesSpinnerItems.priorityNames[i],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void setUpWidgets() {

        taskSpinner = findViewById(R.id.task_spinner);
        prioritySpinner = findViewById(R.id.priority_spinner);
    }
}
