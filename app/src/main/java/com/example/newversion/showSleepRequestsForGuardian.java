package com.example.newversion;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showSleepRequestsForGuardian extends AppCompatActivity {

    TextView txtv_personName, txtv_personPhone, txtv_goingDate, txtv_backDate;
    DatabaseReference dr, dr1;

    String id = "0", idforStudent = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sleep_requests_for_guardian);
        txtv_personName = findViewById(R.id.txtv_personName);
        txtv_personPhone = findViewById(R.id.txtv_personPhone);
        txtv_goingDate = findViewById(R.id.txtv_goingDate);
        txtv_backDate = findViewById(R.id.txtv_backDate);


        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);

        dr = FirebaseDatabase.getInstance().getReference("Student_Father").child(id);
        getId();
        dr1 = FirebaseDatabase.getInstance().getReference("Sleep_outside_requests");

    }

    private void getId() {
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    idforStudent = dataSnapshot.child("Student_Id").getValue(String.class);
                    fun();
                } else
                    Toast.makeText(getApplicationContext(), "no such user", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fun() {
        dr1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    txtv_personName.setText(dataSnapshot.child(idforStudent).child("PersonName").getValue(String.class));
                    txtv_personPhone.setText(dataSnapshot.child(idforStudent).child("Person_phone_num").getValue(String.class));
                    txtv_goingDate.setText(dataSnapshot.child(idforStudent).child("Going_date").getValue(String.class));
                    txtv_backDate.setText(dataSnapshot.child(idforStudent).child("Back_date").getValue(String.class));
                } else {
                    Toast.makeText(getApplicationContext(), "لا يوجد طلبات مبيت للطالبة", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
