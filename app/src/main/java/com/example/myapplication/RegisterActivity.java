package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName, editTextEmail, editTextPassword;
    private CheckBox checkBoxAdmin;
    private Button btnRegister, btnBacktoMain;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        btnBacktoMain = (Button) findViewById(R.id.btnBacktoMain);
        btnBacktoMain.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.etName);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        checkBoxAdmin = (CheckBox) findViewById(R.id.checkBoxAdmin);
        checkBoxAdmin.setChecked(false);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnRegister) {
            register();
        } else if (view.getId() == R.id.btnBacktoMain) {
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    private void register() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Name is required!");
            editTextName.requestFocus();
            return;
        }

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

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // get the current registered user id
                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


                            FirebaseDatabase.getInstance()
                                    .getReference("UserTypes").child(userID)
                                    .child("userType")
                                    .setValue(checkBoxAdmin.isChecked() ? "Admin" : "Student")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (!task.isSuccessful()) {
                                                Toast.makeText(RegisterActivity.this, "Failed: set userType", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                            User user;
                            String ref;

                            if (checkBoxAdmin.isChecked()) {
                                user = new Admin(email, name);
                                ref = "Admins";
                            } else {
                                user = new Student(email, name);
                                ref = "Students";
                                //TODO: delete Toast
                                Toast.makeText(RegisterActivity.this, user.getName(), Toast.LENGTH_LONG).show();
                                Log.d("StudentClassGetName",user.getName());
                            }

                            DatabaseReference reference = FirebaseDatabase.getInstance()
                                    .getReference(ref).child(userID);

                            reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        // redirect to login layout
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegisterActivity.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}