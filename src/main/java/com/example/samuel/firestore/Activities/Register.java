package com.example.samuel.firestore.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.samuel.firestore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private Button register_btn;
    private TextInputLayout email_field, password_field, fullname_field;
    private RelativeLayout layout;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;

    private static final String TAG = "RegisterActivity";
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        setUpwidgets();
        layout = findViewById(R.id.layout);
        progressBar = new ProgressBar(this,null,android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar,params);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void setUpwidgets() {
        register_btn = (Button) findViewById(R.id.login_btn);
        email_field = (TextInputLayout) findViewById(R.id.email_field);
        password_field = (TextInputLayout) findViewById(R.id.Password_field);
        fullname_field = (TextInputLayout) findViewById(R.id.fullname_field);
       // layout = (RelativeLayout) findViewById(R.id.dL);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_btn.setEnabled(false);

                getDetails();
            }
        });
    }
    public void getDetails() {
        String email = email_field.getEditText().getText().toString();
        String password = password_field.getEditText().getText().toString();
        String fullname = fullname_field.getEditText().getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(fullname)) {
            Toast.makeText(Register.this, "Please fill all fields",Toast.LENGTH_LONG).show();

        } else {
//            layout.setVisibility(View.VISIBLE);
           progressBar.setVisibility(View.VISIBLE);
           getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            registerUser(email, password, fullname);

        }


    }

    private void registerUser(String email, String password, String fullname) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               // dialog.dismiss();
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if(task.isSuccessful()){
                    Intent Home = new Intent(Register.this, MainActivity.class);
                    Home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(Home);
                    finish();

                }
                else
                {
                    Toast.makeText(Register.this,"Some error occurred",Toast.LENGTH_LONG).show();
                }



            }
        });
    }


     public void Login(View view){
         startActivity(new Intent(this,Login.class));
     }
}
