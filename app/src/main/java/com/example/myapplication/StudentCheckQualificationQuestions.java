package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentCheckQualificationQuestions extends AppCompatActivity {
    private TextView studentCheckQualiQ1,studentCheckQualiQ2,studentCheckQualiQ3;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3;
    private String studentID;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private Button btnStudentCheckQuali,btnStudentCheckQualiBack;

    private String studentType;
    private Boolean qualified = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_check_qualification_questions);

        studentID = getIntent().getStringExtra("studentID");

        studentType = getIntent().getStringExtra("QualificationType");

        studentCheckQualiQ1 = findViewById(R.id.studentCheckQualiQ1);
        studentCheckQualiQ2 = findViewById(R.id.studentCheckQualiQ2);
        studentCheckQualiQ3 = findViewById(R.id.studentCheckQualiQ3);
        QualificationQuestions qq = new QualificationQuestions(studentType);
        //setValue of q1-q3
        studentCheckQualiQ1.setText("Q1: " + qq.question1 );
        studentCheckQualiQ2.setText("Q2: " + qq.question2);
        studentCheckQualiQ3.setText("Q3: " + qq.question3);

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);


        btnStudentCheckQuali = findViewById(R.id.btnStudentCheckQuali);
        btnStudentCheckQuali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(qualified){
                  Toast.makeText(StudentCheckQualificationQuestions.this, "You are qualified for the POSt", Toast.LENGTH_SHORT).show();
              }
              else{
                  Toast.makeText(StudentCheckQualificationQuestions.this, "You are not qualified for the POSt", Toast.LENGTH_SHORT).show();
              }
            }
        });



        btnStudentCheckQualiBack = findViewById(R.id.btnStudentCheckQualiBack);
        btnStudentCheckQualiBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(StudentCheckQualificationQuestions.this, StudentCheckQualification.class);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

    }

    public void checkButton(View v){//used to handle onclick of the radioButtons
        radioButton1 = findViewById(radioGroup1.getCheckedRadioButtonId());
        radioButton2 = findViewById(radioGroup2.getCheckedRadioButtonId());
        radioButton3 = findViewById(radioGroup3.getCheckedRadioButtonId());

        qualified = (radioButton1.getText().equals("True") &&
                radioButton2.getText().equals("True") &&
                radioButton3.getText().equals("True"));


    }



}
