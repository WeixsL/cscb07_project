package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentGenerateComplaint extends AppCompatActivity {

    private String studentID,studentName;
    private EditText studentGenerateComplain;

    private Button btnStudentGenerateComplaint,btnStudentGenerateComplaintBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_generate_complaint);

        // From StudentOperations get userID(auth)
        studentID = getIntent().getStringExtra("studentID");
        Model model = new Model();
        model.getStudent(studentID, (Student student) -> {
            this.studentName = student.getName();
        });

        studentGenerateComplain = findViewById(R.id.studentGenerateComplain);

        btnStudentGenerateComplaint = findViewById(R.id.btnStudentGenerateComplaint);
        btnStudentGenerateComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Complaint by " + studentName;
                String content = studentGenerateComplain.getText().toString();
                if (title.isEmpty() && content.isEmpty()) {
                    Toast.makeText(StudentGenerateComplaint.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(title.isEmpty()){
                    Toast.makeText(StudentGenerateComplaint.this, "Please enter the title of the announcement", Toast.LENGTH_SHORT).show();
                }
                else if(content.isEmpty()){
                    Toast.makeText(StudentGenerateComplaint.this, "Please enter the content of the announcement", Toast.LENGTH_SHORT).show();
                }
                else{
                    //code to create complains and upload to firebase
                    PublicMessage newMessage = new PublicMessage(title,content);
                    Model model = Model.getInstance();
                    model.saveComplaint(newMessage, (Boolean succeed) ->{
                        Toast.makeText(StudentGenerateComplaint.this, succeed ? "Complaint created" : "Failed to save announcement", Toast.LENGTH_SHORT).show();
                    });

                    //jumps back to StudentOperations
                    Intent intent = new Intent(StudentGenerateComplaint.this, StudentOperations.class);
                    intent.putExtra("userID", studentID);
                    startActivity(intent);
                }
            }
        });


        btnStudentGenerateComplaintBack = findViewById(R.id.btnStudentGenerateComplaintBack);
        btnStudentGenerateComplaintBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start StudentSignupRSVPEvent activity when the button is clicked
                Intent intent = new Intent(StudentGenerateComplaint.this, StudentOperations.class);
                intent.putExtra("userID", studentID);
                startActivity(intent);
            }
        });
    }
}
