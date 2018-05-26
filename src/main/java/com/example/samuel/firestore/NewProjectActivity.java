package com.example.samuel.firestore;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samuel.firestore.Activities.IssueActivity;

public class NewProjectActivity extends AppCompatActivity {
private TextInputLayout getProjectDesc,getProjectTitle;
private String project_title,project_Desc;
private TextView create;
    private static final String TAG = "NewProjectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        getProjectDesc = findViewById(R.id.project_desc);
        getProjectTitle = findViewById(R.id.project_name);
        create = findViewById(R.id.create);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Consts.ADDED_PROJECT_SUCCESS);
                finish();
//                project_title = getProjectTitle.getEditText().getText().toString();
//                project_Desc = getProjectDesc.getEditText().getText().toString();
//                Intent intent = new Intent(NewProjectActivity.this, IssueActivity.class);
//                intent.putExtra("stuff",project_title);
//                Log.d(TAG, "onClick: word is " + project_title);
//                startActivity(intent);

            }
        });
    }
}
