package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminGenerateAnnouncement extends AppCompatActivity {

    private Button btnAdminGenerateAnnoBack, btnAdminGenerateAnno;
    private EditText AnnouncementTitle,AnnouncementContent;
    private String adminID,adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_generate_announcement);
        adminID = getIntent().getStringExtra("adminID");
        Model model = new Model();
        model.getAdmin(adminID, (Admin admin) -> {
            this.adminName = admin.getName();
        });

        AnnouncementTitle = findViewById(R.id.AnnouncementTitle);
        AnnouncementContent = findViewById(R.id.AnnouncementContent);



        btnAdminGenerateAnno = findViewById(R.id.btnAdminGenerateAnno);
        btnAdminGenerateAnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = AnnouncementTitle.getText().toString();
                String announcementTitle = title +" created by " + adminName;
                String content = AnnouncementContent.getText().toString();
                if (title.isEmpty() && content.isEmpty()) {
                    Toast.makeText(AdminGenerateAnnouncement.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(title.isEmpty()){
                    Toast.makeText(AdminGenerateAnnouncement.this, "Please enter the title of the announcement", Toast.LENGTH_SHORT).show();
                }
                else if(content.isEmpty()){
                    Toast.makeText(AdminGenerateAnnouncement.this, "Please enter the content of the announcement", Toast.LENGTH_SHORT).show();
                }
                else{
                    //code to create announcement and upload to firebase
                    PublicMessage newMessage = new PublicMessage(announcementTitle,content);
                    Model model = Model.getInstance();
                    model.saveAnnouncement(newMessage, (Boolean succeed) ->{
                        Toast.makeText(AdminGenerateAnnouncement.this, succeed ? "Announcement " + announcementTitle + " created" : "Failed to save announcement", Toast.LENGTH_SHORT).show();
                    });

                    //jumps back to AdminOperations
                    Intent intent = new Intent(AdminGenerateAnnouncement.this, AdminOperations.class);
                    intent.putExtra("userID", adminID);
                    startActivity(intent);
                }

            }
        });


        btnAdminGenerateAnnoBack = findViewById(R.id.btnAdminGenerateAnnoBack);
        btnAdminGenerateAnnoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminGenerateAnnouncement.this, AdminOperations.class);
                intent.putExtra("userID", adminID);
                startActivity(intent);
            }
        });



    }
}
