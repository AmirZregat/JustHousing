package com.example.newversion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class


Home extends AppCompatActivity {

    ProgressDialog dialog;

    HashMap hashMap;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String name = "";

    String str = "No value";
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        hashMap = new HashMap<String, Object>();


        dialog = new ProgressDialog(this);

        dialog.setMessage("Loading");
        dialog.setIndeterminate(false);
        dialog.show();


        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        String restoredText = prefs.getString("Id", null);
        if (restoredText != null) {

            Query query4 = FirebaseDatabase.getInstance().getReference(prefs.getString("TableName", "0")).child(prefs.getString("Id", "0"));

            final String idName = prefs.getString("Password", "0");
            query4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String th = dataSnapshot.child("Password").getValue(String.class);
                        if (th.equals(idName)) {
                            __Test(prefs.getString("TableName", "0"), prefs.getString("Id", "0"));


                            str = "true";
                            dialog.dismiss();
                            // Toast.makeText(Home.this,"True",Toast.LENGTH_LONG).show();
                        } else {
                            str = "Your passsword has been changed ";
                            dialog.dismiss();
                            Intent intent = new Intent(Home.this, login.class);
                            intent.putExtra("M", str);
                            startActivity(intent);
                            finish();
                        }

                    } else {

                        dialog.dismiss();
                        Intent intent = new Intent(Home.this, login.class);
                        startActivity(intent);
                        finish();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            // String name = prefs.getString("Id", "0");//"No name defined" is the default value.
            // String idName = prefs.getString("Password", "0"); //0 is the default value.

        } else {
            Intent intent = new Intent(Home.this, login.class);
            dialog.dismiss();
            intent.putExtra("M", "");
            startActivity(intent);
            finish();
        }
    }

    void __Test(String layoutname, String id) {
        if (layoutname.equals("Manager")) {
            Intent intent = new Intent(Home.this, AdminPage.class);
            startActivity(intent);
            finish();


        } else if (layoutname.equals("Student_Father")) {
            Intent intent = new Intent(Home.this, guardian_page.class);
            startActivity(intent);
            finish();

        } else if (layoutname.equals("Supervisor")) {
            Intent intent = new Intent(Home.this, supervisor.class);
            startActivity(intent);
            finish();


        } else if (layoutname.equals("Student")) {
            cheick(id);
            finish();
        }


    }

    void cheick(final String id) {

        Query ch = FirebaseDatabase.getInstance().getReference("System_students").child(id);
        ch.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dialog.dismiss();
                    Intent intent2 = new Intent(Home.this, student_page.class);
                    intent2.putExtra("id", id);
                    startActivity(intent2);
                    finish();
                } else {
                    dialog.dismiss();
                    Intent intent2 = new Intent(Home.this, prereserv.class);
                    intent2.putExtra("id", id);
                    startActivity(intent2);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
