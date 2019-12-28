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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class supervisor extends AppCompatActivity {
    Button click1, click2, click3, click4, click5, click6, absentgo , adss;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences.Editor editor;
    String id;
    SharedPreferences prefs;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ProgressDialog dialog;
    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supervisor);
        dialog = new ProgressDialog(this);

        dialog.setMessage("الرجاء الانتظار ...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        click1 = (Button) findViewById(R.id.btn1);
        click2 = (Button) findViewById(R.id.btn2);
        click3 = (Button) findViewById(R.id.btn3);
        click4 = (Button) findViewById(R.id.btn4);
        click5 = (Button) findViewById(R.id.btn5);
        click6 = (Button) findViewById(R.id.btn6);
        adss = findViewById(R.id.supervisorAds);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Supervisor").child(id).child("Building");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    msg = dataSnapshot.getValue(String.class);
                    dialog.dismiss();
                } else {
                    msg = "0";
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        absentgo = (Button) findViewById(R.id.absenntgotoabsentpage);
        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

         adss.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent iii = new Intent(supervisor.this, show_ads.class);
                 startActivity(iii);
             }
         });
        click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int11 = new Intent(supervisor.this, maintainSuperovisor.class);
                startActivity(int11);

            }
        });
        absentgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msg.equals("0")) {
                    Toast.makeText(supervisor.this, "لم تتم اضافاتك الى اي مبنى !!يرجى زيارة المدير", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(supervisor.this, absents.class);
                    intent.putExtra("Msg", msg);
                    startActivity(intent);
                }


            }
        });

        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int11 = new Intent(supervisor.this, penaltiforsupervisro.class);
                startActivity(int11);

            }
        });
        click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int13 = new Intent(supervisor.this, sleep_per_supervisor.class);
                startActivity(int13);
            }
        });
        click4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(supervisor.this, compiantActivit.class);
                intent.putExtra("M", 2);
                startActivity(intent);
            }
        });
        click5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int12 = new Intent(supervisor.this, showinfoo.class);
                startActivity(int12);

            }

        });
        click6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();

            }
        });


    }

    private void showConfirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("تسجيل الخروج؟")
                .setCancelable(false)
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        editor.putString("TableName", null);
                        editor.putString("Id", null);
                        editor.putString("Password", null);
                        editor.apply();
                        Intent intent = new Intent(supervisor.this, Home.class);
                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


}
