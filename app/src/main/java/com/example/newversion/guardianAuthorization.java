package com.example.newversion;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class guardianAuthorization extends AppCompatActivity {
    EditText tawkel;
    CheckBox oneDayTrip, moreThanOneDayTrip, sleepDueUni, sleepDueHolidays;
    DatabaseReference databaseAddGuardianAuth;
    static String sNum;
    DatabaseReference DREF;

    String id = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_authorization);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);

        tawkel = findViewById(R.id.edt_tawkel);
        oneDayTrip = findViewById(R.id.checkBox_oneDayTrip);
        moreThanOneDayTrip = findViewById(R.id.checkBox_moreThanOneDayTrip);
        sleepDueUni = findViewById(R.id.checkBox_sleepDueUniDays);
        sleepDueHolidays = findViewById(R.id.checkBox_sleepDueHolidays);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseAddGuardianAuth = FirebaseDatabase.getInstance().getReference("Student_Father").child(id);
        databaseAddGuardianAuth.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sNum = dataSnapshot.child("Student_Id").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void GuardianAuthDone(View view) {

        DREF = FirebaseDatabase.getInstance().getReference().child("System_students").child(sNum);
        Toast.makeText(this, "تم حفظ البيانات", Toast.LENGTH_LONG).show();
        String t = tawkel.getText().toString();
        if (!TextUtils.isEmpty(t)) {
            DREF.child("Name_of_the_client").setValue(t);
        } else {
            DREF.child("Name_of_the_client").setValue("لا يوجد");
        }
        if (oneDayTrip.isChecked()) {
            DREF.child("One_day_trip").setValue("مسموح");
        } else {
            DREF.child("One_day_trip").setValue("غير مسموح");
        }
        if (moreThanOneDayTrip.isChecked()) {
            DREF.child("More_than_one_day_trip").setValue("مسموح");
        } else {
            DREF.child("More_than_one_day_trip").setValue("غير مسموح");
        }
        if (sleepDueHolidays.isChecked()) {
            DREF.child("Sleep_in_holidays").setValue("مسموح");
        } else {
            DREF.child("Sleep_in_holidays").setValue("غير مسموح");
        }
        if (sleepDueUni.isChecked()) {
            DREF.child("Sleep_any_time").setValue("مسموح");
        } else {
            DREF.child("Sleep_any_time").setValue("غير مسموح");
        }
        tawkel.setText("");

    }

}

