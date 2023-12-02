package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdminViewEventDetail extends AppCompatActivity {
    private ListView studentCommentForEvent;
    private Button btnAdminViewDetailedEventBack;
    private TextView detailedEventTitle,detailedEventContent, detailedEventOccupancy,
            detailedEventCount, detailedEventAvgRate;

    private String selectedEvent,adminID;

    List<String> list = Arrays.asList("comment 1", "comment 2", "comment 3"," comment4 " , "comment 5" , "comment 6");
    //variable list here is for testing, real list is gained from firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_event_detail);

        adminID = getIntent().getStringExtra("adminID");
        selectedEvent = getIntent().getStringExtra("Selected event");
        //Toast.makeText(AdminViewEventDetail.this, "the selected event is: " + selectedEvent, Toast.LENGTH_SHORT).show();

        //show information about the event
        detailedEventTitle = findViewById(R.id.detailedEventTitle);
        detailedEventContent = findViewById(R.id.detailedEventContent);
        detailedEventOccupancy = findViewById(R.id.detailedEventOccupancy);
        detailedEventCount = findViewById(R.id.detailedEventCount);
        detailedEventAvgRate = findViewById(R.id.detailedEventAvgRate);

        //firebase
        Model model = Model.getInstance();
        model.getSingleEvent(selectedEvent, event -> {
            if (event != null) {
                // Successfully retrieved the event, now fetch specific event
                detailedEventTitle.setText(event.name);
                detailedEventContent.setText(event.content);
                detailedEventOccupancy.setText(String.valueOf(event.occupancy));
                detailedEventCount.setText(String.valueOf(event.count));
                detailedEventAvgRate.setText(String.format("%.1f", event.avgRate));
            }
        });


//        detailedEventContent.setText("Event's contents are: ...");
//        detailedEventCount.setText("12");
//        detailedEventAvgRate.setText("3.7");




        btnAdminViewDetailedEventBack = findViewById(R.id.btnAdminViewDetailedEventBack);
        btnAdminViewDetailedEventBack .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start StudentSignupRSVPEvent activity when the button is clicked
                Intent intent = new Intent(AdminViewEventDetail.this, AdminViewEvents.class);
                intent.putExtra("adminID", adminID);
                startActivity(intent);
            }
        });





    }
}
