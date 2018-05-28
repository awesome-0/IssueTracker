package com.example.samuel.firestore;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.firestore.models.Issue;
import com.example.samuel.firestore.models.IssuesSpinnerItems;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewIssueActivity extends AppCompatActivity {
    Spinner taskSpinner;
    TaskAdapter taskAdapter;
    TaskAdapter priorityAdapter;
    Spinner prioritySpinner;
    TextView create;
    private TextInputLayout mAssignToProject;
    private TextInputLayout mSummary;

    private TextInputLayout mDescription;
    private RelativeLayout layout;
    private ProgressBar progressBar;
    FirebaseFirestore db;
    String selectedTask = "";
    String selectedPriority = "";
    //TextView create;
    private static final String TAG = "NewIssueActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_issue);



        setUpWidgets();
        layout = findViewById(R.id.layout);
        progressBar = new ProgressBar(this,null,android.R.attr.progressBarStyleLarge);
        // progressBar.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar,params);
        progressBar.setVisibility(View.INVISIBLE);
        db = FirebaseFirestore.getInstance();
        taskAdapter = new TaskAdapter(NewIssueActivity.this, R.layout.spinner_layout, IssuesSpinnerItems.taskImages,IssuesSpinnerItems.taskNames);
        priorityAdapter = new TaskAdapter(NewIssueActivity.this,R.layout.spinner_layout,IssuesSpinnerItems.priorityImages,IssuesSpinnerItems.priorityNames);


        // taskAdapter.setDropDownViewResource( R.layout.spinner_layout);
        taskSpinner.setAdapter(taskAdapter);
        prioritySpinner.setAdapter(priorityAdapter);



        taskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedTask = IssuesSpinnerItems.taskNames[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPriority = IssuesSpinnerItems.priorityNames[i];
                Toast.makeText(NewIssueActivity.this,selectedPriority,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIssueDetails();
            }
        });


    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    private void hideKeyboard(){
        if(getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
    }

    private void getIssueDetails() {
        hideKeyboard();
        String assign_to_project = mAssignToProject.getEditText().getText().toString();
        String summary = mSummary.getEditText().getText().toString();
        String desc = mDescription.getEditText().getText().toString();

        if(TextUtils.isEmpty(assign_to_project) || TextUtils.isEmpty(summary) || TextUtils.isEmpty(desc)){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show();
        }else{
            hideKeyboard();
            if(progressBar != null){
                showProgressBar();
            }
            create.setEnabled(false);
            String projectId = "8AKOwikDalFzm3LoP1ir";
            DocumentReference newIssue = db.collection(getString(R.string.collection_project))
                    .document(projectId).collection(getString(R.string.issues_document)).document();

            Issue issue = new Issue();
            issue.setDescription(desc);
            issue.setAssignee("none");
            issue.setIssue_id(newIssue.getId());
            issue.setIssue_type(selectedTask);
            issue.setPriority(Issue.getPriorityInteger(selectedPriority));
            issue.setProject_id(projectId);
            issue.setSummary(summary);
            issue.setReporter(FirebaseAuth.getInstance().getCurrentUser().getUid());
            issue.setStatus(Issue.IDLE);

            newIssue.set(issue).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(progressBar != null){
                        hideProgressBar();
                    }

                    create.setEnabled(true);
                    //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    if(task.isSuccessful()){
                        Intent intent = new Intent();
                        intent.putExtra(getString(R.string.snackbar_message),getString(R.string.created_issue));
                        setResult(Consts.ADDED_ISSUE_SUCCESS,intent);
                        finish();
                    }else {
                        Intent intent = new Intent();
                        intent.putExtra(getString(R.string.snackbar_message),getString(R.string.issue_failed));
                        setResult(Consts.ADDED_ISSUE_FAILED,intent);
                        finish();
                    }
                }
            });
          //  Toast.makeText(this,issue.toString(),Toast.LENGTH_LONG).show();
         //   Log.d(TAG, "getIssueDetails:  " + issue.toString() );



        }

    }

    private void setUpWidgets() {

        taskSpinner = findViewById(R.id.task_spinner);
        prioritySpinner = findViewById(R.id.priority_spinner);
        create = findViewById(R.id.create);
        mAssignToProject = findViewById(R.id.project_name);
        mDescription = findViewById(R.id.description);
        mSummary = findViewById(R.id.summary);
    }
}
