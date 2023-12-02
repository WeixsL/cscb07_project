package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminViewComplains extends AppCompatActivity {
    private ListView adminViewComplainsList;
    private Button btnAdminViewComplainBack;

    private String adminID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_complains);

        adminID = getIntent().getStringExtra("adminID");

        adminViewComplainsList = findViewById(R.id.adminViewComplainsList);

        Model model = Model.getInstance();
        model.getComplaints((HashMap<String, PublicMessage> complainsMap) -> {

            String[] complainsArrayList = new String[complainsMap.keySet().size()];
//            complainsMap.keySet().toArray(complainsTitle);
            int index = 0;
            for (Map.Entry<String, PublicMessage> entry : complainsMap.entrySet()) {
                String title = entry.getKey();
                PublicMessage complain = entry.getValue();
                complainsArrayList[index++] = title + ":\n" + complain.content;
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, complainsArrayList);
            adminViewComplainsList.setAdapter(adapter);


        });


        btnAdminViewComplainBack = findViewById(R.id.btnAdminViewComplainBack);
        btnAdminViewComplainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start StudentSignupRSVPEvent activity when the button is clicked
                Intent intent = new Intent(AdminViewComplains.this, AdminOperations.class);
                intent.putExtra("userID", adminID);
                startActivity(intent);
            }
        });
    }
}
