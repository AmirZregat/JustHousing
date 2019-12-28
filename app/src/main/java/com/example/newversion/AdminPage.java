package com.example.newversion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminPage extends AppCompatActivity {

    Button request, generalsetbtn, showinfo, singout, addads, penaltii, complaint,morebtn;

    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences.Editor editor;

    ProgressDialog dialog;

    FirebaseDatabase database;
    DatabaseReference myRef;


    studentinfoo std = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);


        morebtn = (Button) findViewById(R.id.btngotochangeroomforadmin);

        showinfo = (Button) findViewById(R.id.showinfo);
        generalsetbtn = (Button) findViewById(R.id.generalsettingbtn);
        request = (Button) findViewById(R.id.btnResidenceRequests);
        singout = (Button) findViewById(R.id.singout);
        penaltii = (Button) findViewById(R.id.btnPenalties);
        complaint = (Button) findViewById(R.id.btnComplaints);

        addads = (Button) findViewById(R.id.btnHousingAds);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        addads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminPage.this, addAds.class);
                startActivity(intent);
            }
        });
        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminPage.this, changeroomforAdmin.class);
                startActivity(intent);
            }
        });
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminPage.this, compiantActivit.class);
                intent.putExtra("M", 1);
                startActivity(intent);
            }
        });

        penaltii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminPage.this, penalti.class);
                startActivity(intent);

            }
        });


        singout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showConfirmDialog();
            }
        });


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this, ResidenceRequests.class);
                startActivity(intent);
            }
        });


        generalsetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this, generalsetting.class);
                startActivity(intent);
            }
        });


        showinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this, showinfoo.class);
                startActivity(intent);
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
                        Intent intent = new Intent(AdminPage.this, Home.class);
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
