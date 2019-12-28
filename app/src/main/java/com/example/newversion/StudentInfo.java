package com.example.newversion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentInfo extends AppCompatActivity {

    TextView info_Name, info_Room_num, info_Building, info_Student_phone_num, info_Father_phone_num, info_mother_phone_num, info_Brother_phone_num, info_Home_phone_num, info_City, info_Street, info_Neighborhood, info_Email, info_Name_of_the_client, info_One_day_trip, info_More_than_one_day_trip, info_Sleep_in_holidays, info_Sleep_any_time, info_Person1_Name, info_Person2_Name, info_Person3_Name, info_Person4_Name, info_Person5_Name;
    DatabaseReference info_ref;

    String id="0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES="usernameANDpassword";
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);
        info_Name = findViewById(R.id.info_Name);
        info_Room_num = findViewById(R.id.info_Room_num);
        info_Building = findViewById(R.id.info_Building);
        info_Student_phone_num = findViewById(R.id.info_Student_phone_num);
        info_Father_phone_num = findViewById(R.id.info_Father_phone_num);
        info_mother_phone_num = findViewById(R.id.info_mother_phone_num);
        info_One_day_trip = findViewById(R.id.info_One_day_trip);
        info_More_than_one_day_trip = findViewById(R.id.info_More_than_one_day_trip);
        info_Sleep_in_holidays = findViewById(R.id.info_Sleep_in_holidays);
        info_Sleep_any_time = findViewById(R.id.info_Sleep_any_time);
        info_Person1_Name = findViewById(R.id.info_Person1_Name);
        info_Person2_Name = findViewById(R.id.info_Person2_Name);
        info_Brother_phone_num = findViewById(R.id.info_Brother_phone_num);
        info_Home_phone_num = findViewById(R.id.info_Home_phone_num);
        info_Person3_Name = findViewById(R.id.info_Person3_Name);
        info_City = findViewById(R.id.info_City);
        info_Street = findViewById(R.id.info_Street);
        info_Neighborhood = findViewById(R.id.info_Neighborhood);
        info_Email = findViewById(R.id.info_Email);
        info_Name_of_the_client = findViewById(R.id.info_Name_of_the_client);
        info_Person4_Name = findViewById(R.id.info_Person4_Name);
        info_Person5_Name = findViewById(R.id.info_Person5_Name);
        info_ref = FirebaseDatabase.getInstance().getReference("System_students").child(id);
        info_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                info_Name.setText("  " + dataSnapshot.child("Name").getValue(String.class));
                info_Room_num.setText("  " + dataSnapshot.child("Room_num").getValue(String.class));
                info_Building.setText("  " + dataSnapshot.child("Building").getValue(String.class));
                info_Student_phone_num.setText("  " + dataSnapshot.child("Student_phone_num").getValue(String.class));
                info_Father_phone_num.setText("  " + dataSnapshot.child("Father_phone_num").getValue(String.class));
                info_mother_phone_num.setText("  " + dataSnapshot.child("mother_phone_num").getValue(String.class));
                info_One_day_trip.setText("  " + dataSnapshot.child("One_day_trip").getValue(String.class));
                info_More_than_one_day_trip.setText("  " + dataSnapshot.child("More_than_one_day_trip").getValue(String.class));
                info_Sleep_in_holidays.setText("  " + dataSnapshot.child("Sleep_in_holidays").getValue(String.class));
                info_Sleep_any_time.setText("  " + dataSnapshot.child("Sleep_any_time").getValue(String.class));
                info_Email.setText("  " + dataSnapshot.child("Email").getValue(String.class));
                info_Brother_phone_num.setText("  " + dataSnapshot.child("Brother_phone_num").getValue(String.class));
                info_Home_phone_num.setText("  " + dataSnapshot.child("Home_phone_num").getValue(String.class));
                info_City.setText("  " + dataSnapshot.child("City").getValue(String.class));
                info_Street.setText("  " + dataSnapshot.child("Street").getValue(String.class));
                info_Neighborhood.setText("  " + dataSnapshot.child("Neighborhood").getValue(String.class));
                info_Name_of_the_client.setText("  " + dataSnapshot.child("Name_of_the_client").getValue(String.class));
                info_Person1_Name.setText("  " + dataSnapshot.child("Person1").child("Name").getValue(String.class));
                info_Person2_Name.setText("  " + dataSnapshot.child("Person2").child("Name").getValue(String.class));
                info_Person3_Name.setText("  " + dataSnapshot.child("Person3").child("Name").getValue(String.class));
                info_Person4_Name.setText("  " + dataSnapshot.child("Person4").child("Name").getValue(String.class));
                info_Person5_Name.setText("  " + dataSnapshot.child("Person5").child("Name").getValue(String.class));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void movetoEditClick(View view) {
        Intent i=new Intent(getApplicationContext(),student_info_page.class);
        startActivity(i);
    }
    }

