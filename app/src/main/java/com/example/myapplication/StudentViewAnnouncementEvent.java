package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class StudentViewAnnouncementEvent extends AppCompatActivity {

    private String studentID;
    private ListView studentSelectAnnouncementViewList,studentSelectEventViewList;
    private Button btnStudentViewAnnouncementEventBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_view_announcement_event);

        // From StudentOperations get userID(auth)
        studentID = getIntent().getStringExtra("studentID");


        studentSelectAnnouncementViewList = findViewById(R.id.studentSelectAnnouncementViewList);
        studentSelectEventViewList = findViewById(R.id.studentSelectEventViewList);

        Model model = Model.getInstance();
        model.getAnnouncements((HashMap<String, PublicMessage> announcementsMap) -> {

            String[] announcementArrayList = new String[announcementsMap.keySet().size()];
//            complainsMap.keySet().toArray(complainsTitle);
            int index = 0;
            for (Map.Entry<String, PublicMessage> entry : announcementsMap.entrySet()) {
                String title = entry.getKey();
                PublicMessage announcement = entry.getValue();
                announcementArrayList[index++] = title + "\n" + announcement.content;
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, announcementArrayList);
            studentSelectAnnouncementViewList.setAdapter(adapter);


        });

        btnStudentViewAnnouncementEventBack = findViewById(R.id.btnStudentViewAnnouncementEventBack);
        btnStudentViewAnnouncementEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start StudentSignupRSVPEvent activity when the button is clicked
                Intent intent = new Intent(StudentViewAnnouncementEvent.this,
                        StudentOperations.class);
                intent.putExtra("userID", studentID);
                startActivity(intent);
            }
        });



        //TODO：add to AndroidManifest.xml
    }

}