package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);

        // Add login button click listener
        findViewById(R.id.buttonLogin).setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Perform authentication (you may use Firebase, server, etc.)
        if (username.equals("admin") && password.equals("admin")) {
            // Authentication successful
            // Start the main activity or perform other actions
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            // Authentication failed
            Toast.makeText(this, "Informations Incorrectes !", Toast.LENGTH_SHORT).show();
        }
    }
}
