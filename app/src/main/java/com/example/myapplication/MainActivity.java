package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword;
    private Button btnLogIn, btnGoRegister;
    private FirebaseAuth mAuth;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        btnLogIn = (Button) findViewById(R.id.btnLogin);
        btnGoRegister = (Button) findViewById(R.id.btnGoRegister);

        mAuth = FirebaseAuth.getInstance();

        presenter = new Presenter(new Model(), this);

        btnLogIn.setOnClickListener(this);
        btnGoRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGoRegister) {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        } else if (v.getId() == R.id.btnLogin) {
            logIn();
        }
    }

    private void logIn() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // validate
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        presenter.login(email, password);
    }

    public void redirectToStudentOperations(String userID) {
        Intent intent = new Intent(MainActivity.this, StudentOperations.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }

    public void redirectToAdminOperations(String userID) {
        Toast.makeText(MainActivity.this, "redirect To Admin Operations", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, AdminOperations.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }

    public void failedToLogin() {
        Toast.makeText(this, "Failed to login.", Toast.LENGTH_LONG).show();
    }
}