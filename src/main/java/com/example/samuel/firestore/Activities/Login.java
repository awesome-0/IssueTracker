package com.example.samuel.firestore.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.samuel.firestore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button loginBtn ;
    private TextInputLayout email_field,password_field;
    private RelativeLayout layout;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        setUpwidgets();
    }

    public void LoginUser(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void Register(View view) {
        startActivity(new Intent(this,Register.class));
    }
    public void setUpwidgets(){
        loginBtn = (Button) findViewById(R.id.login_btn);
        email_field = (TextInputLayout) findViewById(R.id.email_field);
        password_field = (TextInputLayout) findViewById(R.id.Password_field);
       // layout = (RelativeLayout) findViewById(R.id.dL);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setEnabled(false);

                getDetails();
            }
        });
    }

    public void getDetails(){
        String email = email_field.getEditText().getText().toString();
        String password = password_field.getEditText().getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(Login.this,"Please fill all fields",Toast.LENGTH_LONG).show();

        }
        else{
//            layout.setVisibility(View.VISIBLE);
            dialog.setMessage("Logging In");
            dialog.show();
            loginUser(email,password);

        }
}

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                loginBtn.setEnabled(true);
                if(task.isSuccessful()){
                    Intent Home = new Intent(Login.this, MainActivity.class);
                    Home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(Home);
                    finish();

                }
                else
                {
                  Toast.makeText(Login.this,"Some error occurred",Toast.LENGTH_LONG).show();
                }

                }

        });
    }
}
