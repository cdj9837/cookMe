package com.example.cookme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail;
    Button submitBtn;
    TextView cancelLink;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = (EditText) findViewById(R.id.resetPwEmail);
        submitBtn = (Button) findViewById(R.id.resetButton);
        cancelLink = (TextView) findViewById(R.id.tvCancelLink);

        submitBtn.setOnClickListener(this);
        cancelLink.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.resetButton:
                sendResetPwEmail();
                break;

            case R.id.tvCancelLink:
                cancelResetPW();
                break;
        }
    }

    private void sendResetPwEmail(){
        String email = etEmail.getText().toString().trim();

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(ForgotPassword.this, Login.class);
                            startActivity(i);
                            Toast.makeText(ForgotPassword.this, "Send link to reset password successfully. " +
                                    "Check your email.", Toast.LENGTH_SHORT ).show();
                        }
                        else{
                            Toast.makeText(ForgotPassword.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void cancelResetPW(){
        Intent i = new Intent(ForgotPassword.this, Login.class);
        startActivity(i);
    }
}
