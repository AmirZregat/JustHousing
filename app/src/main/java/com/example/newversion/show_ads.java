package com.example.newversion;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class show_ads extends AppCompatActivity
{
    FirebaseDatabase database;
    DatabaseReference myRef, myRef2;
    ArrayList<ads> adsArrayList;
    ProgressDialog dialog;
    ads adsobject;
    LayoutInflater inflater = null;
    ListView listView = null;

    adsadapter adadapter;

    String adsn = "";
    String adst = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ads);
        dialog = new ProgressDialog(this);
        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        database = FirebaseDatabase.getInstance();
        myRef2 = database.getReference("System").child("Adds");
        adsArrayList = new ArrayList<>();

        inflater = getLayoutInflater();
        listView = (ListView) findViewById(R.id.listforads);

        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adsn = dataSnapshot.getKey();
                adst = dataSnapshot.child("AD_TEXT").getValue(String.class);
                go1(adsn,adst);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void go1(String adsn, String adst) {

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adsArrayList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        HashMap<String, Object> stringObjectHashMap = (HashMap<String, Object>) dataSnapshot.child(t.getKey()).getValue();
                        adsobject = new ads();
                        adsobject.setAds_num(t.getKey());
                        adsobject.setAds_text(String.valueOf(stringObjectHashMap.get("AD_TEXT")));

                        adsArrayList.add(adsobject);

                    }

                    go(adsArrayList);
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void go(ArrayList<ads> adsArrayList) {
        if (adsArrayList.isEmpty()) {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "لا يوجد اعلانات", Toast.LENGTH_LONG).show();

        } else {
            adadapter = new adsadapter(inflater, adsArrayList, 0);
            dialog.dismiss();
            listView.setAdapter(adadapter);
        }

    }







}
