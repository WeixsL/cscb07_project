package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class Model {

    private static Model instance;

    private DatabaseReference studentRef;
    private DatabaseReference adminRef;
    private DatabaseReference eventRef;
    private DatabaseReference announcementRef;
    private DatabaseReference complainRef;

    private FirebaseAuth auth;

    public Model() {
        studentRef = FirebaseDatabase.getInstance().getReference("Students");
        adminRef = FirebaseDatabase.getInstance().getReference("Admins");
        eventRef = FirebaseDatabase.getInstance().getReference("Events");
        announcementRef = FirebaseDatabase.getInstance().getReference("Announcements");
        complainRef = FirebaseDatabase.getInstance().getReference("Complaints");
        auth = FirebaseAuth.getInstance();
    }

    public void authenticate(String email, String password, Consumer<User> callback) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // get current log-in User ID
                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    // get the userType (Doctor or Patient)
                    FirebaseDatabase.getInstance().getReference("UserTypes")
                            .child(userID).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String userType = snapshot.getValue(String.class);

                                    if (userType == null) {
                                        Log.d("Model", "Failed to authenticate, cannot find userType");
                                        callback.accept(null);
                                    }

                                    else if (userType.equals("Student")) {
                                        Student student = new Student();
                                        student.setID(userID);
                                        callback.accept(student);
                                    }

                                    else if (userType.equals("Admin")) {
                                        Admin admin = new Admin();
                                        admin.setID(userID);
                                        callback.accept(admin);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }
                else {
                    Log.d("Model", "Failed to authenticate.");
                    callback.accept(null);
                }
            }
        });
    }

    public static Model getInstance() {
        if (instance == null)
            instance = new Model();
        return instance;
    }

    public void getStudent(String userID, Consumer<Student> callback){
        studentRef.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);
                Log.d("ModelGetStudent", "snapshot is working" + student.getName());
                callback.accept(student);
                Log.d("ModelGetStudent", "Callback is working: " + student.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void getSingleEvent(String eventTitle, Consumer<Event> callback){
        eventRef.child(eventTitle).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Event event = snapshot.getValue(Event.class);
                if (event != null) {
                    Log.d("ModelGetSingleEvent", "snapshot is working" + event.name);
                    callback.accept(event);
                    Log.d("ModelGetSingleEvent", "Callback is working: " + event.name);
                } else {
                    Log.d("ModelGetSingleEvent", "event is null!!!!!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void getSingleComplain(String complainID, Consumer<PublicMessage> callback){
        complainRef.child(complainID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PublicMessage complain = snapshot.getValue(PublicMessage.class);
                if (complain != null) {
                    Log.d("ModelGetSingleComplain", "snapshot is working" + complain.title);
                    callback.accept(complain);
                    Log.d("ModelGetSingleEvent", "Callback is working: " + complain.title);
                } else {
                    Log.d("ModelGetSingleEvent", "event is null!!!!!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }


    public void getSingleAnnouncement(String announcementID, Consumer<PublicMessage> callback){
        announcementRef.child(announcementID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PublicMessage announcement = snapshot.getValue(PublicMessage.class);
                if (announcement != null) {
                    Log.d("ModelGetSingleComplain", "snapshot is working" + announcement.title);
                    callback.accept(announcement);
                    Log.d("ModelGetSingleEvent", "Callback is working: " + announcement.title);
                } else {
                    Log.d("ModelGetSingleEvent", "event is null!!!!!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void getAdmin(String userID, Consumer<Admin> callback){
        adminRef.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Admin admin = snapshot.getValue(Admin.class);
                Log.d("ModelGetAdmin", "snapshot is working" + admin.getName());
                callback.accept(admin);
                Log.d("ModelGetAdmin", "Callback is working: " + admin.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void getEvents(Consumer<HashMap<String, Event>> callback){

        // callback返回一个HashMap，eventTitle/Name是key, event本身是Value

        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Event> events = new HashMap<>();
                for(DataSnapshot eventSnapShot: snapshot.getChildren()){
                    Event event = eventSnapShot.getValue(Event.class);
                    events.put(event.name, event);
                }
                callback.accept(events);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveEvent(Event event, Consumer<Boolean> callback){
        FirebaseDatabase.getInstance().getReference("Events").child(event.name).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callback.accept(task.isSuccessful());
            }
        });
    }



    public void getAnnouncements(Consumer<HashMap<String, PublicMessage>> callback){

        // HashMap Value is PublicMessage，与getEvents同理

        announcementRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, PublicMessage> announcements = new HashMap<>();
                for(DataSnapshot announcementSnapShot: snapshot.getChildren()){
                    PublicMessage announcement = announcementSnapShot.getValue(PublicMessage.class);
                    announcements.put(announcement.title, announcement);
                }
                callback.accept(announcements);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveAnnouncement(PublicMessage announcement, Consumer<Boolean> callback){
        FirebaseDatabase.getInstance().getReference("Announcements").child(announcement.title).setValue(announcement).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callback.accept(task.isSuccessful());
            }
        });
    }

    public void getComplaints(Consumer<HashMap<String, PublicMessage>> callback){

        // HashMap Value is PublicMessage，与getEvents同理

        complainRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, PublicMessage> complaints = new HashMap<>();
                for(DataSnapshot complaintSnapShot: snapshot.getChildren()){
                    PublicMessage complaint = complaintSnapShot.getValue(PublicMessage.class);
                    complaints.put(complaint.title, complaint);
                }
                callback.accept(complaints);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveComplaint(PublicMessage complaint, Consumer<Boolean> callback){
        FirebaseDatabase.getInstance().getReference("Complaints").child(complaint.title).setValue(complaint).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callback.accept(task.isSuccessful());
            }
        });
    }

}

