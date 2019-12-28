package com.example.newversion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class guardian_page extends AppCompatActivity {

    Button button;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ProgressDialog dialog;
    int flag = 0;
    String str = "";
    FirebaseDatabase database2;
    DatabaseReference databaseReference2;
    String id = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;
    String Studentid = "9999999";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_page);
        dialog = new ProgressDialog(this);

        dialog.setMessage("الرجاء الانتظار ...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Student_Father").child(id).child("Student_Id");

        database2 = FirebaseDatabase.getInstance();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Studentid = dataSnapshot.getValue(String.class);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Button showabsent = (Button) findViewById(R.id.showabsentsforfather);

        showabsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = "";
                flag = 0;

                databaseReference2 = database2.getReference("System_students").child(Studentid);

                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        DataSnapshot dataSnapshot1 = dataSnapshot.child("Absents");
                        DataSnapshot dataSnapshot2 = dataSnapshot.child("Name");
                        flag += 1;
                        if (flag == 1) {
                            if (dataSnapshot1.exists()) {
                                for (DataSnapshot t : dataSnapshot1.getChildren()) {
                                    str += t.getKey();
                                    str += "\n";

                                }
                                AlertDialog alertDialog = new AlertDialog.Builder(guardian_page.this).create();
                                alertDialog.setTitle(dataSnapshot2.getValue(String.class));
                                alertDialog.setMessage(str);
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();


                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(guardian_page.this).create();
                                alertDialog.setTitle(dataSnapshot2.getValue(String.class));
                                alertDialog.setMessage("لا يوجد غيابات");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                        } else {
                            return;
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        button = (Button) findViewById(R.id.singoout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showalertdialog();

            }
        });
    }

    public void altawkelWalMwafqat(View view) {
        Intent intent = new Intent(this, guardianAuthorization.class);
        startActivity(intent);
    }


    public void addPersonsName(View view) {
        Intent intent = new Intent(this, addPersonsName.class);
        startActivity(intent);
    }

    private void showalertdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        editor.putString("TableName", null);
                        editor.putString("Id", null);
                        editor.putString("Password", null);
                        editor.apply();
                        Intent intent = new Intent(guardian_page.this, Home.class);
                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    public void moveToShowSleepForFather(View view) {
        Intent i = new Intent(getApplicationContext(), showSleepRequestsForGuardian.class);
        startActivity(i);
    }
}
