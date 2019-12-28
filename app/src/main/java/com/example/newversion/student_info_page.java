package com.example.newversion;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_info_page extends AppCompatActivity {
    DatabaseReference infoRef;
    EditText brotherPhone, studentPhone, motherPhone, fatherPhone, homePhone;
    Button save_student_info;

    String id="0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES="usernameANDpassword";
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info_page);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);

        brotherPhone = findViewById(R.id.edt_Brother_phone_num);
        studentPhone = findViewById(R.id.edt_Student_phone_num);
        motherPhone = findViewById(R.id.edt_Mother_phone_num);
        fatherPhone = findViewById(R.id.edt_Father_phone_num);
        homePhone = findViewById(R.id.edt_Home_phone_num);
        save_student_info = findViewById(R.id.btn_save_student_info);
        infoRef=FirebaseDatabase.getInstance().getReference().child("System_students").child(id);
        save_student_info.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                saveInfo();
            }
        });
    }

    private void saveInfo() {
        infoRef.child("Student_phone_num").setValue(studentPhone.getText().toString());
        infoRef.child("mother_phone_num").setValue(motherPhone.getText().toString());
        infoRef.child("Father_phone_num").setValue(fatherPhone.getText().toString());
        infoRef.child("Brother_phone_num").setValue(brotherPhone.getText().toString());
        infoRef.child("Home_phone_num").setValue(homePhone.getText().toString());
        Toast.makeText(getApplicationContext(), "info saved", Toast.LENGTH_LONG).show();
    }

}

