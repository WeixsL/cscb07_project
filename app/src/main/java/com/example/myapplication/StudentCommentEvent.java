package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StudentCommentEvent extends AppCompatActivity implements View.OnClickListener{

    private Button btnStudentCommentEventBack;
    private String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_comment_event);

        // From StudentOperations get userID(auth)
        studentID = getIntent().getStringExtra("studentID");

        btnStudentCommentEventBack = (Button) findViewById(R.id.btnStudentCommentEventBack);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnStudentCommentEventBack) {
            Intent intent = new Intent(StudentCommentEvent.this, StudentOperations.class);
            startActivity(intent);
        }
    }
}