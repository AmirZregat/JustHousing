package com.example.newversion;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Requestpage1 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,myre2;
    String id2;
    Long id;
    Button btn;
    ProgressDialog dialog;
    EditText studentname,studenthoue,nationlity,studentnumber,Studentmajor,studentmobilenumber,Studentemail;

HashMap<String,Object>hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestpage1);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setIndeterminate(true);
        dialog.show();


        final String value = getIntent().getExtras().getString("id");


      database = FirebaseDatabase.getInstance();
        myRef =database.getReference("ResidenceRequests");
        myre2 =database.getReference("Student").child(value);


        btn = (Button) findViewById(R.id.btn_request);

        btn.setEnabled(false);



        studentname = (EditText) findViewById(R.id.FullStudentName);
        studenthoue = (EditText) findViewById(R.id.whereStudentLives);
        nationlity = (EditText) findViewById(R.id.nation);
        studentnumber = (EditText) findViewById(R.id.StudentIdNum);
        Studentmajor = (EditText) findViewById(R.id.StudentMajor);
        studentmobilenumber = (EditText) findViewById(R.id.StudentMobileNum);
        Studentemail = (EditText) findViewById(R.id.StudentEmail);


        studentmobilenumber.setEnabled(false);
        studenthoue.setEnabled(false);
        studentname.setEnabled(false);
        studentnumber.setEnabled(false);
        nationlity.setEnabled(false);
        Studentmajor.setEnabled(false);
        Studentemail.setEnabled(false);



        getdata();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.child(value).child("Status").setValue("Waiting");
                myRef.child(value).child("SRoom_Num").setValue("1526");
                myRef.child(value).child("Student_Num").setValue(value);

                Toast.makeText(getApplicationContext(), "تم تقديم طلب الالتحاق بالسكن", Toast.LENGTH_LONG).show();

            }
        });





    }
    void getdata(){

        myre2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hashMap= (HashMap<String, Object>) dataSnapshot.getValue();

                btn.setEnabled(true);
                btn.setBackgroundResource(R.drawable.btn_bg2);
                studentmobilenumber.setText(""+String.valueOf( hashMap.get("Student_phone_num")));
                studenthoue.setText(""+String.valueOf( hashMap.get("Birthplace")));
                studentname.setText(""+String.valueOf( hashMap.get("Name")));
                studentnumber.setText(""+String.valueOf( hashMap.get("Student_phone_num")));
                nationlity.setText(""+String.valueOf( hashMap.get("Nationality")));
                Studentmajor.setText(""+String.valueOf( hashMap.get("Major")));
                Studentemail.setText(""+String.valueOf( hashMap.get("Email")));

                dialog.setCancelable(false);
                dialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
