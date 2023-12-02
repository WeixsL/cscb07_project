package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StudentOperations extends AppCompatActivity implements View.OnClickListener{

    private Button btnViewAnnouncementEvent, btnViewRSVPEvent, btnGenerateComplaint, btnCommentRateEvent, btnCheckQualification, btnStudentLogOut;

    private String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_operations);

        studentID = getIntent().getStringExtra("userID");


        btnViewAnnouncementEvent = (Button) findViewById(R.id.btnViewAnnouncementEvent);
        btnViewAnnouncementEvent.setOnClickListener(this);

        btnCheckQualification = (Button) findViewById(R.id.btnCheckQualification);
        btnCheckQualification.setOnClickListener(this);

        btnGenerateComplaint = (Button) findViewById(R.id.btnGenerateComplaint);
        btnGenerateComplaint.setOnClickListener(this);

        btnViewRSVPEvent = (Button) findViewById(R.id.btnRSVPforEvent);
        btnViewRSVPEvent.setOnClickListener(this);

        btnCommentRateEvent = (Button) findViewById(R.id.btnCommentRateEvent);
        btnCommentRateEvent.setOnClickListener(this);

        btnStudentLogOut = (Button) findViewById(R.id.btnStudentLogOut);
        btnStudentLogOut.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnStudentLogOut) {

            startActivity(new Intent(StudentOperations.this, MainActivity.class));

        } else if (id == R.id.btnCheckQualification) {
            Intent intent = new Intent(StudentOperations.this, StudentCheckQualification.class);
            intent.putExtra("studentID", studentID);
            startActivity(intent);

        } else if (id == R.id.btnViewAnnouncementEvent) {

            //TODO
            Intent intent = new Intent(StudentOperations.this, StudentViewAnnouncementEvent.class);
            intent.putExtra("studentID", studentID);
            startActivity(intent);

        } else if (id == R.id.btnGenerateComplaint) {

            Intent intent = new Intent(StudentOperations.this, StudentGenerateComplaint.class);
            intent.putExtra("studentID", studentID);
            startActivity(intent);

        } else if (id == R.id.btnRSVPforEvent) {

            Intent intent = new Intent(StudentOperations.this, StudentViewRSVPEvents.class);
            intent.putExtra("studentID", studentID);
            startActivity(intent);

        } else if (id == R.id.btnCommentRateEvent) {

            Intent intent = new Intent(StudentOperations.this, StudentCommentEvent.class);
            intent.putExtra("studentID", studentID);
            startActivity(intent);

        }

    }

}