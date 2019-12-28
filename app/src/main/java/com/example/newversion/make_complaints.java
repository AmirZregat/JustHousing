package com.example.newversion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class make_complaints extends AppCompatActivity {

    DatabaseReference complaintsRef;
    Spinner s, s1;
    EditText multilineComplaint, personTomakeCmpName;
    List<String> suplist;
    ArrayAdapter<String> adapter;
    String str;

    RadioButton rStudent, rSupervisor;
    Button m;


    String id = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs, p;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_complaints);

        p = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        language = p.getString("my_lang", "");


        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);
        complaintsRef = FirebaseDatabase.getInstance().getReference().child("Complaints");
        s1 = findViewById(R.id.spinnerChooseNameOfSupervisor);
        multilineComplaint = findViewById(R.id.textOfComplaints);
        personTomakeCmpName = findViewById(R.id.nameForComplaints);
        suplist = new ArrayList<>();
        Intent i = getIntent();
        suplist = i.getStringArrayListExtra("supervisor_list");
        rStudent = findViewById(R.id.radio_student);
        rSupervisor = findViewById(R.id.radio_supervisor);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, suplist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        m = findViewById(R.id.BtnmakeComp);
        rStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personTomakeCmpName.setEnabled(true);

            }
        });

        rSupervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personTomakeCmpName.setEnabled(false);

            }
        });


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str = String.valueOf(s1.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeComplaintsButton();
            }
        });
    }

    public void makeComplaintsButton() {


        if (rSupervisor.isChecked()) {

            complaintsRef = FirebaseDatabase.getInstance().getReference("Complaints").child("OnSupervisor");
            complaintsRef.child(id).child("ComplaintText").setValue(multilineComplaint.getText().toString());
            complaintsRef.child(id).child("Name").setValue(str);
            if (language.equals("ar"))
                Toast.makeText(getApplicationContext(), "تم قديم الشكوى", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();


        } else if (rStudent.isChecked()) {
            complaintsRef = FirebaseDatabase.getInstance().getReference("Complaints").child("OnStudent");
            complaintsRef.child(id).child("ComplaintText").setValue(multilineComplaint.getText().toString());
            complaintsRef.child(id).child("Name").setValue(personTomakeCmpName.getText().toString());
            if (language.equals("ar"))
                Toast.makeText(getApplicationContext(), "تم قديم الشكوى", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();

        } else if (language.equals("ar"))
            Toast.makeText(getApplicationContext(), "اختر مشرفة او طالبة", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Choose a supervisor or student!", Toast.LENGTH_LONG).show();

    }
}

