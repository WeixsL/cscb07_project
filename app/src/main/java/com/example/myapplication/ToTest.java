package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ToTest extends AppCompatActivity {

    private String studentID;
    private String studentName;
    private Model model;
    private TextView tvToTest;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_test);

        studentID = getIntent().getStringExtra("studentID");


        tvToTest = (TextView) findViewById(R.id.textView2);

//        model = new Model();
//        model.getStudentName(studentID, (String studentName) -> {
//            this.studentName = studentName;
//            Log.d("ToTestGetStudentName", "In model: " + studentName + " and this. " + this.studentName);
////            tvToTest.setText(String.format("Welcome, %s!", studentName));
//        });

//        model = new Model();
//        model.getStudent(studentID, (Student student) -> {
//            this.student = student;
//            Log.d("ToTestGetStudentName", "In model: " + student.getName() + " and this. " + this.student.getName());
//        });
//
//        Log.d("ToTestGetStudentName", "after model: this." + this.student.getName());
//        tvToTest.setText(String.format("Welcome, %s!", studentName));



//        FirebaseDatabase.getInstance().getReference("Students").child(studentID).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String name = snapshot.getValue(String.class);
//                tvToTest.setText(name);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
}