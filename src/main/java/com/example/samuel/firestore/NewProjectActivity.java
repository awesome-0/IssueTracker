package com.example.samuel.firestore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samuel.firestore.Activities.IssueActivity;
import com.example.samuel.firestore.Activities.Login;
import com.example.samuel.firestore.models.Project;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewProjectActivity extends AppCompatActivity {
private TextInputLayout getProjectDesc,getProjectTitle;
private String project_title,project_Desc;
private TextView create;
    private static final String TAG = "NewProjectActivity";
    private FirebaseFirestore db;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        getProjectDesc = findViewById(R.id.project_desc);
        getProjectTitle = findViewById(R.id.project_name);
        create = findViewById(R.id.create);
        dialog = new ProgressDialog(this);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setResult(Consts.ADDED_PROJECT_SUCCESS);
//                finish();
                project_title = getProjectTitle.getEditText().getText().toString();
                project_Desc = getProjectDesc.getEditText().getText().toString();
                if(TextUtils.isEmpty(project_title) || TextUtils.isEmpty(project_Desc)){
                    Toast.makeText(NewProjectActivity.this,"Please fill all fields",Toast.LENGTH_LONG).show();

                }
                else{
                    dialog.setMessage("Adding Project...");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
//
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    db = FirebaseFirestore.getInstance();
                    DocumentReference newProjectReference  = db.collection("Project").document();


                    Project project = new Project(
                            project_title,
                            project_Desc,
                            userId,
                            null,
                            "",
                            newProjectReference.getId()
                    );
                   //  db.collection("Project").add(project);


                    newProjectReference.set(project).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();

                            if(task.isSuccessful()){
                                Intent intent = new Intent();
                                intent.putExtra(getString(R.string.snackbar_message),getString(R.string.project_created));
                                setResult(Consts.ADDED_PROJECT_SUCCESS,intent);
                                finish();
                            }else {
                                Intent intent = new Intent();
                                intent.putExtra(getString(R.string.snackbar_message),getString(R.string.project_failed));
                                setResult(Consts.ADDED_PROJECT_FAILED,intent);
                                finish();
                            }
                        }
                    });

                }


            }
        });
    }
}
