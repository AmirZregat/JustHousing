package com.example.newversion;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;


public class sleep_per_supervisor extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef, myRef2;
    ArrayList<sleep> sleepArrayList;
    ProgressDialog dialog;
    sleep sleepobject;
    LayoutInflater inflater = null;
    ListView listView = null;

    sleepadapter slepadapter;

    String pdate = "";
    String gdate = "";
    String pname = "";
    String pphone = "";
    String stu_id = "";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_per_supervisor);
        dialog = new ProgressDialog(this);
        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mMonth += 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);

        String id = prefs.getString("Id", null);

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("Supervisor").child(id);
        myRef2 = database.getReference("Sleep_outside_requests");

        sleepArrayList = new ArrayList<>();

        inflater = getLayoutInflater();
        listView = (ListView) findViewById(R.id.listforsleep);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pdate = dataSnapshot.child("Back_date").getValue(String.class);
                gdate = dataSnapshot.child("Going_Date").getValue(String.class);
                pname = dataSnapshot.child("PersonName").getValue(String.class);
                pphone = dataSnapshot.child("Person_phone_num").getValue(String.class);
                stu_id = dataSnapshot.getKey();
                go1(pdate, gdate, pname, pphone, stu_id);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void go1(String pdate, String gdate, String pname, String pphone, final String stu_id) {

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sleepArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        //  String pppphone=t.getKey();

                        //  if (pppphone.equals(stu_id)){

                        HashMap<String, Object> stringObjectHashMap = (HashMap<String, Object>) dataSnapshot.child(t.getKey()).getValue();
                        sleepobject = new sleep();
                        sleepobject.setStudent_Id(t.getKey());
                        sleepobject.setBack_date(String.valueOf(stringObjectHashMap.get("Back_date")));
                        sleepobject.setGoing_date(String.valueOf(stringObjectHashMap.get("Going_date")));
                        sleepobject.setPersonName(String.valueOf(stringObjectHashMap.get("PersonName")));
                        sleepobject.setPerson_phone_num(String.valueOf(stringObjectHashMap.get("Person_phone_num")));
                        String str = String.valueOf(stringObjectHashMap.get("Back_date"));
                        String[] arr = str.split("-");
                        int databaseday = Integer.parseInt(arr[0]);
                        int databasemonth = Integer.parseInt(arr[1]);
                        int databaseyear = Integer.parseInt(arr[2]);
                        if (databaseday == mDay && databasemonth == mMonth && databaseyear == mYear) {
                            myRef2.child(t.getKey()).removeValue();
                            continue;
                        }

                        sleepArrayList.add(sleepobject);
                        // }else
                        //  continue;

                    }

                    go(sleepArrayList);
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void go(ArrayList<sleep> sleepArrayList) {
        if (sleepArrayList.isEmpty()) {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "لا يوجد طبات مبيت ", Toast.LENGTH_LONG).show();

        } else {
            slepadapter = new sleepadapter(inflater, sleepArrayList, 0);
            dialog.dismiss();
            listView.setAdapter(slepadapter);
        }

    }
}