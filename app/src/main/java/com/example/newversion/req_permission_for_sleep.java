package com.example.newversion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class req_permission_for_sleep extends AppCompatActivity {

    DatabaseReference drefOnReqTable, refonStudent;
    EditText goingDate, backDate;
    List<String> myList, numList;
    ArrayAdapter<String> adapter;
    Spinner s;
    String str, approved, language;
    String id = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs, p;
    Button req;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_permission_for_sleep);

        p = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        language = p.getString("my_lang", "");


        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);

        goingDate = findViewById(R.id.edt_goingDate);
        backDate = findViewById(R.id.edt_backDate);
        s = findViewById(R.id.personsSpinner);
        req = findViewById(R.id.req_sleep_button);

        drefOnReqTable = FirebaseDatabase.getInstance().getReference("Sleep_outside_requests");
        refonStudent = FirebaseDatabase.getInstance().getReference("System_students").child(id);


        myList = new ArrayList<>();
        numList = new ArrayList<>();
        Intent i = getIntent();
        myList = i.getStringArrayListExtra("personsListArray");
        numList = i.getStringArrayListExtra("prsonsListNumbersArray");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        approvedOrNot();

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendreqForSleepingOutside();
            }
        });


        goingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun1();
            }
        });

        backDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun2();
            }
        });
    }

    private void approvedOrNot() {
        refonStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    approved = dataSnapshot.child("Sleep_any_time").getValue(String.class);
                } else return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fun2() {
        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        backDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }

                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void fun1() {

        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        goingDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }

                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


    public void sendreqForSleepingOutside() {
        if (approved.equals("مسموح")) {
            str = s.getSelectedItem().toString();
            if (!str.equals("Not Provided !!")) {
                int i = (int) s.getSelectedItemId();
                String n = numList.get(i);
                String g = goingDate.getText().toString();
                String b = backDate.getText().toString();
                if (g.equals("") || b.equals("")) {
                    if (language.equals("ar"))
                        Toast.makeText(getApplicationContext(), "لا يمكن ترك التاريخ فارغاً", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "The data can not be empty!", Toast.LENGTH_LONG).show();

                } else {
                    drefOnReqTable.child(id).child("PersonName").setValue(str);
                    drefOnReqTable.child(id).child("Going_date").setValue(g);
                    drefOnReqTable.child(id).child("Back_date").setValue(b);
                    drefOnReqTable.child(id).child("Person_phone_num").setValue(n);
                    goingDate.setText("");
                    backDate.setText("");
                    if (language.equals("ar"))
                        Toast.makeText(getApplicationContext(), "تم ارسال الطلب", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "The request has been sent!", Toast.LENGTH_LONG).show();

                }
            } else {
                if (language.equals("ar"))
                    Toast.makeText(getApplicationContext(), "لا يوجد أسماء اشخاص يسمح المبيت عندهم!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "There is no persons names you can sleep at their home! ", Toast.LENGTH_LONG).show();

                goingDate.setText("");
                backDate.setText("");
            }
        } else {
            if (language.equals("ar"))
                Toast.makeText(getApplicationContext(), "غير مسموح المبيت خارج السكن من قبل ولي الأمر", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Sleep outside is not allowed for you!", Toast.LENGTH_LONG).show();

            goingDate.setText("");
            backDate.setText("");
        }
    }
}

