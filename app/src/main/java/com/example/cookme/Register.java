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

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button registerBtn;
    EditText etName_R, etEmail_R, etPassword_R;
    TextView signInLink;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName_R = findViewById(R.id.etName_R);
        etEmail_R = findViewById(R.id.etEmail_R);
        etPassword_R = findViewById(R.id.etPassword_R);
        registerBtn = findViewById(R.id.RegisterBtn);
        signInLink = findViewById(R.id.signInLink);

        firebaseAuth =FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(this);
        signInLink.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    private void registerUser(){
        String name = etName_R.getText().toString().trim();
        String email = etEmail_R.getText().toString().trim();
        String password = etPassword_R.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your email. This will be use for your login",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        final Intent home_activity = new Intent(this, MainActivity.class);

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "Register Sucessfully",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(home_activity);
                        }
                        else{
                            Toast.makeText(Register.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.RegisterBtn:
                registerUser();
                break;
            case R.id.signInLink:
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                break;
        }
    }
}
