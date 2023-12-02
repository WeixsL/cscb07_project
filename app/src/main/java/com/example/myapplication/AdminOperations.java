package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminOperations extends AppCompatActivity {

    private Button btnPostAnnouncement, btnPostEvent, btnViewEvents, btnCheckQualification, btnAdminLogOut;
    private String adminID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_operations);

        adminID = getIntent().getStringExtra("userID");

        // Assuming there's a button in the layout with the ID btnToSignUpRSVP
        Button btnPostAnnouncement = findViewById(R.id.btnPostAnnouncement);
        btnPostAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start StudentSignupRSVPEvent activity when the button is clicked
                Intent intent = new Intent(AdminOperations.this, AdminGenerateAnnouncement.class);
                intent.putExtra("adminID",adminID);
                startActivity(intent);
            }
        });

        Button btnPostEvent = findViewById(R.id.btnPostEvent);
        btnPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminOperations.this, AdminGenerateEvents.class);
                intent.putExtra("adminID",adminID);
                startActivity(intent);
            }
        });

        Button btnViewEvents = findViewById(R.id.btnViewEvents);
        btnViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminOperations.this, AdminViewEvents.class);
                intent.putExtra("adminID", adminID);
                startActivity(intent);
            }
        });

        Button btnCheckQualification = findViewById(R.id.btnViewComplaints);
        btnCheckQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminOperations.this, AdminViewComplains.class);
                intent.putExtra("adminID", adminID);
                startActivity(intent);
            }
        });

        Button btnAdminLogOut = findViewById(R.id.btnAdminLogOut);
        btnAdminLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminOperations.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}