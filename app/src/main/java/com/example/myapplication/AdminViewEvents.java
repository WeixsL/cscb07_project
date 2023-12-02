package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdminViewEvents extends AppCompatActivity {
    private ListView adminEventList;
    private Button btnAdminSelectEventBack;
    private String adminID;
    private String selectedEvent;
    //variable list here is for testing, real list is gained from firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_events);

        adminID = getIntent().getStringExtra("adminID");

        adminEventList = findViewById(R.id.adminEventList);
        Model model = Model.getInstance();
        model.getEvents((HashMap<String, Event> eventsMap) -> {
            String[] eventsTitle = new String[eventsMap.keySet().size()];
            eventsMap.keySet().toArray(eventsTitle);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventsTitle);

            adminEventList.setAdapter(adapter);

            adminEventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    Intent intent = new Intent(AdminViewEvents.this, AdminViewEventDetail.class);
                    intent.putExtra("adminID", adminID);
                    intent.putExtra("Selected event",selectedItem);
                    startActivity(intent);
                }
            });

        });



        btnAdminSelectEventBack = findViewById(R.id.btnAdminSelectEventBack);
        btnAdminSelectEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminViewEvents.this, AdminOperations.class);
                intent.putExtra("userID", adminID);
                startActivity(intent);
            }
        });
    }
}
