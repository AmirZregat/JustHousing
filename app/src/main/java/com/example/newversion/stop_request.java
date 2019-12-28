package com.example.newversion;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class stop_request extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker,Submet,nowbtnn;
    TextView txtDate, txtTime;
    HashMap<String ,String> ShashMap,Nhashmap;
    FirebaseDatabase database;
    DatabaseReference myRef;


    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_request);



        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("System").child("Request_Status");

        ShashMap=new HashMap<String,String>();
        Nhashmap=new HashMap<String,String>();


        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);


        Submet = (Button) findViewById(R.id.submet);
        nowbtnn = (Button) findViewById(R.id.nowbtn);

        txtDate = (TextView) findViewById(R.id.in_date);
        txtTime = (TextView) findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
       btnTimePicker.setOnClickListener(this);


       nowbtnn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Nhashmap.put("Year",
                       "2000");
               Nhashmap.put("Month","1");
               Nhashmap.put("Day","1");

               Nhashmap.put("Hour","1");
               Nhashmap.put("Minute","1");

               myRef.setValue(Nhashmap);
               Toast.makeText(stop_request.this, "Done !!", Toast.LENGTH_SHORT).show();

           }
       });




        Submet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date1=txtDate.getText().toString();
                String time1=txtTime.getText().toString();

                if (TextUtils.isEmpty(date1)) {
                    Toast.makeText(stop_request.this, "Enter Date ,please", Toast.LENGTH_SHORT).show();


                    return;
                } else if (TextUtils.isEmpty(time1)) {


                    Toast.makeText(stop_request.this, "Enter  Time ,please", Toast.LENGTH_SHORT).show();
                    return;
                }else
                {
                    myRef.setValue(ShashMap);
                    Toast.makeText(stop_request.this, "Done !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {
          //  Toast.makeText(stop_request.this, "btn date", Toast.LENGTH_LONG).show();


            // Get Current Date

            final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);



            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {


                            ShashMap.put("Year",String.valueOf(year));
                            ShashMap.put("Month",String.valueOf(monthOfYear+1));
                            ShashMap.put("Day",String.valueOf(dayOfMonth));

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (v == btnTimePicker) {

          //  Toast.makeText(stop_request.this, "btn time", Toast.LENGTH_LONG).show();

            // Get Current Time
            final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            ShashMap.put("Hour",String.valueOf(hourOfDay));
                            ShashMap.put("Minute",String.valueOf(minute));

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }


        }


    }

