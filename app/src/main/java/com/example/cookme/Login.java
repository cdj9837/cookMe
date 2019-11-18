package com.example.cookme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    EditText etEmail, etPassword;
    TextView tvregisterLink, tvForgotPwLink;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        loginBtn = (Button) findViewById(R.id.LoginBtn);
        tvregisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        tvForgotPwLink = (TextView) findViewById(R.id.tvForgotPwLink);

        loginBtn.setOnClickListener(this);
        tvregisterLink.setOnClickListener(this);
        tvForgotPwLink.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void loginUser(){
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        final Intent home_activity = new Intent(this, MainActivity.class);

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(home_activity);
                    Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT ).show();
                }
                else{
                    Toast.makeText(Login.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LoginBtn:
                loginUser();
                break;
            case R.id.tvRegisterLink:
                Intent i = new Intent(this, Register.class);
                startActivity(i);
                break;
            case R.id.tvForgotPwLink:
                Intent g = new Intent(this, ForgotPassword.class);
                startActivity(g);
                break;
        }
    }



}
