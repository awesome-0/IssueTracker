package com.example.samuel.firestore;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.firestore.models.Issue;
import com.example.samuel.firestore.models.IssuesSpinnerItems;
import com.example.samuel.firestore.models.Project;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NewIssueActivity extends AppCompatActivity {
    Spinner taskSpinner;
    TaskAdapter taskAdapter;
    TaskAdapter priorityAdapter;
    Spinner prioritySpinner;
    TextView create;
    private AutoCompleteTextView mAssignToProject;
    private TextInputLayout mSummary;

    private TextInputLayout mDescription;
    private RelativeLayout layout;
    private ProgressBar progressBar;
    FirebaseFirestore db;
    String selectedTask = "";
    String selectedPriority = "";
    //TextView create;
    private static final String TAG = "NewIssueActivity";
    ArrayList<Project> projects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_issue);


        setUpWidgets();
        //  setUpAdapterForAutoCompleteTextView();
        layout = findViewById(R.id.layout);
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        // progressBar.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar, params);
       // progressBar.setVisibility(View.VISIBLE);
        showProgressBar();
        db = FirebaseFirestore.getInstance();
        taskAdapter = new TaskAdapter(NewIssueActivity.this, R.layout.spinner_layout, IssuesSpinnerItems.taskImages, IssuesSpinnerItems.taskNames);
        priorityAdapter = new TaskAdapter(NewIssueActivity.this, R.layout.spinner_layout, IssuesSpinnerItems.priorityImages, IssuesSpinnerItems.priorityNames);

        CollectionReference projectReference = db.collection(getString(R.string.collection_project));

        projectReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
              //  progressBar.setVisibility(View.INVISIBLE);

                if (task.isSuccessful()) {
                    if (progressBar != null) {
                        hideProgressBar();
                    }

                    create.setEnabled(true);
                    int i = 0;
                    String[] projectNames = new String[task.getResult().size()];
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        Project project = snapshot.toObject(Project.class);
                        projectNames[i] = project.getName();
                        projects.add(project);
                        i++;
                        Log.d(TAG, "onComplete: " + project.getName());
                    }

                    setUpAdapterForAutoCompleteTextView(projectNames);
                } else {
                    if (progressBar != null) {
                        hideProgressBar();
                    }
                    Toast.makeText(NewIssueActivity.this, "Cannot Load Projects", Toast.LENGTH_LONG).show();
                }


            }
        });
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
                //  Toast.makeText(NewIssueActivity.this, selectedPriority, Toast.LENGTH_LONG).show();
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

            private void showProgressBar() {
                progressBar.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            private void hideProgressBar() {
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            private void hideKeyboard() {
                if (getCurrentFocus() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                }
            }

            private void setUpAdapterForAutoCompleteTextView(String[] items) {
                //  String[] items = {"hello","samuel","today"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewIssueActivity.this, android.R.layout.simple_list_item_1, items);
                mAssignToProject.setAdapter(adapter);
                mAssignToProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSoftKeyBoard(view);
                        mAssignToProject.showDropDown();
                    }
                });
//                mAssignToProject.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//
//
//                        return true;
//                    }
//                });

                mAssignToProject.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (editable.toString().equals("")) {
                            mAssignToProject.setError("Select a Project");
                        } else {
                            mAssignToProject.setError(null);
                        }

                    }
                });


            }

            private void showSoftKeyBoard(View view) {
                if (getCurrentFocus() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, 0);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


                }
            }


            private void getIssueDetails() {
                hideKeyboard();
                String assign_to_project = mAssignToProject.getText().toString();
                final String summary = mSummary.getEditText().getText().toString();
                final String desc = mDescription.getEditText().getText().toString();

                if (TextUtils.isEmpty(assign_to_project) || TextUtils.isEmpty(summary) || TextUtils.isEmpty(desc)) {
                    Toast.makeText(NewIssueActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                } else {
                    hideKeyboard();
                   // hideProgressBar();

                    create.setEnabled(false);


                    String projectId = "";
                    for (Project proj : projects) {
                        if (proj.getName().equals(mAssignToProject.getText().toString())) {
                            //  temp = proj.getName();
                            projectId = proj.getProjectId();
                        }
                    }
                    if (projectId.equals("")) {
                        Toast.makeText(NewIssueActivity.this, "Select a valid project", Toast.LENGTH_LONG).show();
                        create.setEnabled(true);
                        mAssignToProject.setText("");
                        mAssignToProject.setError("Select Valid project");
                    } else {
                        if (progressBar != null) {
                            showProgressBar();
                        }
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
                                hideProgressBar();
                                create.setEnabled(true);
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent();
                                    intent.putExtra(getString(R.string.snackbar_message), getString(R.string.created_issue));
                                    setResult(Consts.ADDED_ISSUE_SUCCESS, intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent();
                                    intent.putExtra(getString(R.string.snackbar_message), getString(R.string.issue_failed));
                                    setResult(Consts.ADDED_ISSUE_FAILED, intent);
                                    finish();
                                }
                            }
                        });
                    }
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


