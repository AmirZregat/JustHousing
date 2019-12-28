package com.example.newversion;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class addPersonsName extends AppCompatActivity {


    Button addPersonName;
    DatabaseReference personRef, dr;
    EditText edtPersonName, edtPersonNumber, edtPersonAdress;
    HashMap<String, Object> hperson1, hperson2, hperson3, hperson4, hperson5;

    String id = "0", idforStudent = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persons_name);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);

        addPersonName = findViewById(R.id.btn_addPersonName);
        edtPersonName = findViewById(R.id.edt_personName);
        edtPersonNumber = findViewById(R.id.edt_personNumber);
        edtPersonAdress = findViewById(R.id.edt_personAdress);
        dr = FirebaseDatabase.getInstance().getReference("Student_Father").child(id);
        getId();
        personRef = FirebaseDatabase.getInstance().getReference("System_students");

        addPersonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPersonNameFun();
                secondCheck();
            }
        });
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
        personRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    hperson1 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person1").getValue();
                    hperson2 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person2").getValue();
                    hperson3 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person3").getValue();
                    hperson4 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person4").getValue();
                    hperson5 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person5").getValue();
                } else return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void secondCheck() {
        personRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    hperson1 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person1").getValue();
                    hperson2 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person2").getValue();
                    hperson3 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person3").getValue();
                    hperson4 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person4").getValue();
                    hperson5 = (HashMap<String, Object>) dataSnapshot.child(idforStudent).child("Person5").getValue();
                } else
                    return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addPersonNameFun() {

        if (String.valueOf(hperson1.get("Name")).equals("Not Provided !!")) {
            personRef.child(idforStudent).child("Person1").child("Name").setValue(edtPersonName.getText().toString());
            personRef.child(idforStudent).child("Person1").child("Phone").setValue(edtPersonNumber.getText().toString());
            personRef.child(idforStudent).child("Person1").child("Address").setValue(edtPersonAdress.getText().toString());
            Toast.makeText(getApplicationContext(), "تم اضافة الاسم الاول", Toast.LENGTH_LONG).show();
            edtPersonAdress.setText("");
            edtPersonNumber.setText("");
            edtPersonName.setText("");

        } else if (String.valueOf(hperson2.get("Name")).equals("Not Provided !!")) {
            personRef.child(idforStudent).child("Person2").child("Name").setValue(edtPersonName.getText().toString());
            personRef.child(idforStudent).child("Person2").child("Phone").setValue(edtPersonNumber.getText().toString());
            personRef.child(idforStudent).child("Person2").child("Address").setValue(edtPersonAdress.getText().toString());
            Toast.makeText(getApplicationContext(), "تم اضافة الاسم الثاني", Toast.LENGTH_LONG).show();
            edtPersonAdress.setText("");
            edtPersonNumber.setText("");
            edtPersonName.setText("");

        } else if (String.valueOf(hperson3.get("Name")).equals("Not Provided !!")) {
            personRef.child(idforStudent).child("Person3").child("Name").setValue(edtPersonName.getText().toString());
            personRef.child(idforStudent).child("Person3").child("Phone").setValue(edtPersonNumber.getText().toString());
            personRef.child(idforStudent).child("Person3").child("Address").setValue(edtPersonAdress.getText().toString());
            Toast.makeText(getApplicationContext(), "تم اضافة الاسم الثالث", Toast.LENGTH_LONG).show();
            edtPersonAdress.setText("");
            edtPersonNumber.setText("");
            edtPersonName.setText("");
        } else if (String.valueOf(hperson4.get("Name")).equals("Not Provided !!")) {
            personRef.child(idforStudent).child("Person4").child("Name").setValue(edtPersonName.getText().toString());
            personRef.child(idforStudent).child("Person4").child("Phone").setValue(edtPersonNumber.getText().toString());
            personRef.child(idforStudent).child("Person4").child("Address").setValue(edtPersonAdress.getText().toString());
            Toast.makeText(getApplicationContext(), "تم اضافة الاسم الرابع", Toast.LENGTH_LONG).show();
            edtPersonAdress.setText("");
            edtPersonNumber.setText("");
            edtPersonName.setText("");

        } else if (String.valueOf(hperson5.get("Name")).equals("Not Provided !!")) {
            personRef.child(idforStudent).child("Person5").child("Name").setValue(edtPersonName.getText().toString());
            personRef.child(idforStudent).child("Person5").child("Phone").setValue(edtPersonNumber.getText().toString());
            personRef.child(idforStudent).child("Person5").child("Address").setValue(edtPersonAdress.getText().toString());
            Toast.makeText(getApplicationContext(), "تم اضافة الاسم الخامس", Toast.LENGTH_LONG).show();
            edtPersonAdress.setText("");
            edtPersonNumber.setText("");
            edtPersonName.setText("");
        }
    }
}

