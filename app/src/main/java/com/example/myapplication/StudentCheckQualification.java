package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentCheckQualification extends AppCompatActivity {
    private ListView qualificationList;
    private Button btnQualiSituationBack;

    private String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_check_qualification);

        studentID = getIntent().getStringExtra("studentID");


        // TODO
        String[] data = {"For students applying for a CS minor", "For students in the CS admission category applying for CS Major/Specialist", "For students in other admission categories applying for CS Major/Specialist", "For students who began at UTSC prior to 2021 applying for CS Major/Specialist"};

        // 创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        // 获取ListView
        qualificationList = findViewById(R.id.qualificationList);
        qualificationList.setAdapter(adapter);

        qualificationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里处理点击事件
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(StudentCheckQualification.this, StudentCheckQualificationQuestions.class);
                intent.putExtra("studentID", studentID);
                intent.putExtra("QualificationType",selectedItem);
                startActivity(intent);
            }
        });

        btnQualiSituationBack = findViewById(R.id.btnQualiSituationBack);
        btnQualiSituationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentCheckQualification.this, StudentOperations.class);
                intent.putExtra("userID", studentID);
                startActivity(intent);
            }
        });


    }
}