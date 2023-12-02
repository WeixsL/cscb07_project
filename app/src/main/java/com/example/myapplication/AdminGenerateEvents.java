package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminGenerateEvents extends AppCompatActivity {
    private Button btnAdminGenerateEvent, btnAdminGenerateEventBack;
    private EditText EventName,EventContent,EventOccupancy;
    private String adminID, adminName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_generate_event);
        adminID = getIntent().getStringExtra("adminID");
        Model model = new Model();
        model.getAdmin(adminID, (Admin admin) -> {
            this.adminName = admin.getName();
        });


        EventName = findViewById(R.id.EventName);
        EventContent = findViewById(R.id.EventContent);
        EventOccupancy = findViewById(R.id.EventOccupancy);

        btnAdminGenerateEvent = findViewById(R.id.btnAdminGenerateEvent);
        btnAdminGenerateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EventName.getText().toString();
                String eventTitle = name + " created by " + adminName;
                String content = EventContent.getText().toString();
                String occupancy = EventOccupancy.getText().toString();

                if (name.isEmpty() && content.isEmpty() && occupancy.isEmpty()) {
                    Toast.makeText(AdminGenerateEvents.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(name.isEmpty()){
                    Toast.makeText(AdminGenerateEvents.this, "Please enter the name of the event", Toast.LENGTH_SHORT).show();
                }
                else if(content.isEmpty()){
                    Toast.makeText(AdminGenerateEvents.this, "Please the content of the event '" + name + "'", Toast.LENGTH_SHORT).show();
                }
                else if(occupancy.isEmpty()){
                    Toast.makeText(AdminGenerateEvents.this, "Please the occupancy of the event '" + name + "'", Toast.LENGTH_SHORT).show();
                }
                else{
                    //code to create Event and upload to firebase

                    Event newEvent = new Event(eventTitle,content,Integer.parseInt(occupancy));
                    Model model = Model.getInstance();
                    model.saveEvent(newEvent, (Boolean succeed) ->{
                        Toast.makeText(AdminGenerateEvents.this, succeed ? "event " + eventTitle + " created" : "Failed to save event", Toast.LENGTH_SHORT).show();
                    });

                    Intent intent = new Intent(AdminGenerateEvents.this, AdminOperations.class);
                    intent.putExtra("userID", adminID);
                    startActivity(intent);
                }

            }
        });




        btnAdminGenerateEventBack = findViewById(R.id.btnAdminGenerateEventBack);
        btnAdminGenerateEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminGenerateEvents.this, AdminOperations.class);
                intent.putExtra("userID", adminID);
                startActivity(intent);
            }
        });
    }
}
