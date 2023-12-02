package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewRSVPEvents extends AppCompatActivity {

    private String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_view_rsvp_events);

        // From StudentOperations get userID(auth)
        studentID = getIntent().getStringExtra("studentID");

        RecyclerView eventlist = new RecyclerView(StudentViewRSVPEvents.this);

        Button btnStudentViewEventBack = findViewById(R.id.btnStudentViewAnnouncementEventBack);
        btnStudentViewEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start StudentSignupRSVPEvent activity when the button is clicked
                Intent intent = new Intent(StudentViewRSVPEvents.this, StudentOperations.class);
                startActivity(intent);
            }
        });
    }
}
