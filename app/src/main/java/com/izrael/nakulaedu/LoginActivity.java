package com.izrael.nakulaedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText inputNis, inputPassword;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = findViewById(R.id.buttonlogin);
        inputNis = findViewById(R.id.inputNis);
        inputPassword = findViewById(R.id.inputPassword);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nis;
                String password;
                nis = inputNis.getText().toString();
                password = inputPassword.getText().toString();
                if (!password.equals("Testing")){
                    inputPassword.setError("Password Salah");
                }else if (!nis.equals("666666")){
                    inputNis.setError("NIS Salah");
                }else {
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(intent);
                }
            }
        });
    }
}
