package com.example.cookme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    EditText etUsername, etPassword;
    TextView tvregisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        loginBtn = (Button) findViewById(R.id.LoginBtn);
        tvregisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        loginBtn.setOnClickListener(this);
        tvregisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
//                startActivity(new Intent(this, Register.class));
                 break;
            case R.id.tvRegisterLink:
                Intent i = new Intent(this, Register.class);
                startActivity(i);
                break;
        }
    }
}
