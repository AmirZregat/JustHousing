package com.example.newversion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class prereserv extends AppCompatActivity {
    Button addRequest, reg, go;
    Button showprices;

    FirebaseDatabase database, database2;
    DatabaseReference myRef, chieck, chieckforbloked, getprices;
    String message = "";
    HashMap<String, String> restatus;
    Calendar currentcalender, databaseCalender;
    String value;
    ProgressDialog dialog;
    Date date;
    TextView textView;
    ArrayList<HashMap<String, String>> hashMapswitharray;
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prereserv);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        database = FirebaseDatabase.getInstance();

        value = getIntent().getExtras().getString("id");

        addRequest = findViewById(R.id.addRe);
        reg = findViewById(R.id.regeeee);
        textView = findViewById(R.id.txtttttttt);
        textView.setText(".");


        showprices = (Button) findViewById(R.id.showpriceebtn);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Loging in please wait...");
        dialog.setIndeterminate(true);
        dialog.show();


        addRequest.setEnabled(false);
        addRequest.setBackgroundResource(R.drawable.hassantest);

        reg.setEnabled(false);
        reg.setBackgroundResource(R.drawable.hassantest);
        showprices.setEnabled(true);



        database2 = FirebaseDatabase.getInstance();
        chieckforbloked = database2.getReference("System").child("Blocked_Student").child(value);


        chieckforbloked.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    textView.setText("We Are Sorry,You were Banned from JUST Housing");
                    reg.setEnabled(false);
                    reg.setBackgroundResource(R.drawable.hassantest);

                    addRequest.setEnabled(false);
                    addRequest.setBackgroundResource(R.drawable.hassantest);

                    dialog.dismiss();

                } else
                    f();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        showprices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(prereserv.this, ReadONLY_Room_Price.class);
                startActivity(intent);

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(prereserv.this, regesterpage.class);
                intent.putExtra("id", value);
                startActivity(intent);
                finish();

            }
        });


        addRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(prereserv.this, Requestpage1.class);
                intent.putExtra("id", value);
                startActivity(intent);

            }
        });


    }

    void f() {

        showprices.setEnabled(true);

        chieck = database.getReference("ResidenceRequests").child(value);


        //.child();
        chieck.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dialog.dismiss();
                    final String v = dataSnapshot.child("Status").getValue(String.class);
                    if (v.equals("Yes")) {
                        reg.setEnabled(true);
                        reg.setBackgroundResource(R.drawable.btn_bg2);
                        addRequest.setEnabled(false);
                        addRequest.setBackgroundResource(R.drawable.hassantest);
                        message = "Your request has been approved, You Can choose Your Room Now !!";
                        textView.setText("Your request has been approved, You Can choose Your Room Now !!");
                    } else if (v.equals("Waiting")) {
                        addRequest.setEnabled(false);
                        addRequest.setBackgroundResource(R.drawable.hassantest);
                        reg.setEnabled(false);
                        reg.setBackgroundResource(R.drawable.hassantest);

                        message = "Your request still in progress by Admin";
                        textView.setText("Your request still in progress by Admin");
                    } else {
                        addRequest.setEnabled(false);
                        addRequest.setBackgroundResource(R.drawable.hassantest);
                        reg.setEnabled(false);
                        reg.setBackgroundResource(R.drawable.hassantest);
                        textView.setText("Your request has been Rejected , Please Visit The Housing Administration for More Information");
                        message = "Your request has been Rejected , Please Visit The Housing Administration for More Information";
                    }


                } else {
                    addRequest.setEnabled(true);
                    addRequest.setBackgroundResource(R.drawable.btn_bg2);
                    reg.setEnabled(false);
                    reg.setBackgroundResource(R.drawable.hassantest);
                    g();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    void g() {

        dialog.dismiss();
        restatus = new HashMap<String, String>();
        currentcalender = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
        databaseCalender = Calendar.getInstance(TimeZone.getTimeZone("3.00"));


        final String[] hadfi = currentcalender.getTime().toString().split(" ");
        final String[] time = hadfi[3].split(":");
        date = currentcalender.getTime();

        final int Year = Integer.parseInt(hadfi[5]);
        final int Month = date.getMonth() + 1;
        final int Day = Integer.parseInt(hadfi[2]);
        final int Hour = Integer.parseInt(time[0]);
        final int Minute = Integer.parseInt(time[1]);
        final int Second = Integer.parseInt(time[2]);

        currentcalender.set(Year, Month, Day, Hour, Minute, 00);

        //Toast.makeText(prereserv.this, currentcalender.getTime().toString(), Toast.LENGTH_SHORT).show();


        myRef = database.getReference("System").child("Request_Status");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                restatus = (HashMap<String, String>) dataSnapshot.getValue();

                int year = Integer.parseInt(restatus.get("Year"));
                int month = Integer.parseInt(restatus.get("Month"));
                int day = Integer.parseInt(restatus.get("Day"));
                int hour = Integer.parseInt(restatus.get("Hour"));
                int minut = Integer.parseInt(restatus.get("Minute"));

                databaseCalender.set(
                        year
                        , month
                        , day
                        , hour
                        , minut
                        , 00);


                boolean f2 = databaseCalender.after(currentcalender);
                if (f2) {

                    addRequest.setEnabled(true);
                    addRequest.setBackgroundResource(R.drawable.btn_bg2);

                    textView.setText("يمكنك تقديم طلب الان");
                    dialog.dismiss();
                } else {
                    addRequest.setEnabled(false);
                    addRequest.setBackgroundResource(R.drawable.hassantest);

                    reg.setEnabled(false);
                    reg.setBackgroundResource(R.drawable.hassantest);

                    textView.setText("تم اغلاق الطلبات من قبل المدير");
                    dialog.dismiss();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }

    public void logoutFromPreReservation(View view) {
        editor.putString("TableName", null);
        editor.putString("Id", null);
        editor.putString("Password", null);
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), Home.class);


        startActivity(intent);
        finish();
    }

    public void movetoregulations(View view) {
        Intent i = new Intent(getApplicationContext(), regulationsAndInstructions.class);
        startActivity(i);
    }
}
