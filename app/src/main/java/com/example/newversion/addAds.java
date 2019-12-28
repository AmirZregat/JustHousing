package com.example.newversion;

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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class addAds extends AppCompatActivity {
    Button send,deletandadd,deletewitout;
    EditText editText;
    String txt;
int count=0;
    Calendar currentcalender,databaseCalender;
    Date date;

    FirebaseDatabase database ;
    DatabaseReference myRef,myre2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ads);
        send = (Button) findViewById(R.id.addnew);
        deletandadd = (Button) findViewById(R.id.deletandadd);
        deletewitout = (Button) findViewById(R.id.deleteallwithoutadd);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
count=0;
                database = FirebaseDatabase.getInstance();
                currentcalender = Calendar.getInstance(TimeZone.getTimeZone("3.00"));

                date = currentcalender.getTime();

                final String []hadfi = currentcalender.getTime().toString().split(" ");
                final String []time =hadfi[3].split(":");

                final int Year = Integer.parseInt(hadfi[5]);
                final int Month = date.getMonth()+1;
                final int Day = Integer.parseInt(hadfi[2]);
                final int Hour = Integer.parseInt(time[0]);
                final int Minute = Integer.parseInt(time[1]);


                final String dateofAd=""+Year+"-"+Month+"-"+Day;
                final String timeofAd=""+Hour+":"+Minute;

                myRef =database.getReference("System").child("Adds");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        count+=1;
        if (count==1){
              if (dataSnapshot.exists()){
        long x= dataSnapshot.getChildrenCount()+1;

        editText=(EditText)findViewById(R.id.adstext);
        txt=editText.getText().toString();
        myRef.child(""+x).child("AD_TEXT").setValue(txt);
        myRef.child(""+x).child("AD_DATE").setValue(dateofAd);
        myRef.child(""+x).child("AD_TIME").setValue(timeofAd);

        Toast.makeText(getApplicationContext(), "تم", Toast.LENGTH_LONG).show();

    }else{

    }
}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

        });
        deletandadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
count=0;
                currentcalender = Calendar.getInstance(TimeZone.getTimeZone("3.00"));

                date = currentcalender.getTime();

                final String []hadfi = currentcalender.getTime().toString().split(" ");
                final String []time =hadfi[3].split(":");

                final int Year = Integer.parseInt(hadfi[5]);
                final int Month = date.getMonth()+1;
                final int Day = Integer.parseInt(hadfi[2]);
                final int Hour = Integer.parseInt(time[0]);
                final int Minute = Integer.parseInt(time[1]);


                final String dateofAd=""+Year+"_"+Month+"_"+Day;
                final String timeofAd=""+Hour+"_"+Minute;

                database = FirebaseDatabase.getInstance();
                myRef =database.getReference("System").child("Adds");
                myRef.setValue(0);


                myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        count+=1;
                        if (count==1){
                            if (dataSnapshot.exists()){
                                long x= dataSnapshot.getChildrenCount()+1;

                                editText=(EditText)findViewById(R.id.adstext);
                                txt=editText.getText().toString();
                                myRef.child(""+x).child("AD_TEXT").setValue(txt);
                                myRef.child(""+x).child("AD_DATE").setValue(dateofAd);
                                myRef.child(""+x).child("AD_TIME").setValue(timeofAd);

                                Toast.makeText(getApplicationContext(), "تم", Toast.LENGTH_LONG).show();

                            }else{

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




                editText=(EditText)findViewById(R.id.adstext);
                txt=editText.getText().toString();
                myRef.setValue(txt);

                myRef.child(dateofAd).child(timeofAd).setValue(txt);
                Toast.makeText(getApplicationContext(), "تم", Toast.LENGTH_LONG).show();

            }
        });
        deletewitout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                myRef =database.getReference("System").child("Adds");

                myRef.setValue(0);
                Toast.makeText(getApplicationContext(), "تم", Toast.LENGTH_LONG).show();

            }
        });

    }
}
