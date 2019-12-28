package com.example.newversion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class student_page extends AppCompatActivity {

    List<String> myList, personsList, PersonsListNumbers;
    DatabaseReference maintenanceRef, onSupervisorRef, refPersons;
    HashMap<String, Object> hPerson1, hPerson2, hPerson3, hPerson4, hPerson5;
    Button btn, changeLang, changeroom;
    Button adss;
    int count = 0;
    FirebaseDatabase database;
    DatabaseReference databaseReference, mystatusref;
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs, p;
    String str = "", language1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        changeLang = findViewById(R.id.changeLang);
        adss = findViewById(R.id.studentAds);
        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        final String id = prefs.getString("Id", null);

        changeroom = (Button) findViewById(R.id.gotochangeroom);
        p = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        language1 = p.getString("my_lang", "");

        database = FirebaseDatabase.getInstance();
        try {

            databaseReference = database.getReference("System_students").child(id).child("Absents");
        } catch (Exception e) {

        }

        adss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii = new Intent(getApplicationContext(), show_ads.class);
                startActivity(ii);

            }
        });

        mystatusref = database.getReference("System").child("Roomchangerequests");
        mystatusref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    CardView card = findViewById(R.id.mycard);
                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        String str = t.child("SecondStudentId").getValue(String.class);
                        if (str.equals(id)) {
                            String str2 = t.child("SecondStudentStatus").getValue(String.class);
                            if (str2.equals("Waiting")) {
                                card.setCardBackgroundColor(Color.GREEN);
                            } else
                                card.setCardBackgroundColor(Color.WHITE);

                        }
                    }
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
        } else {
            String language = prefs.getString("my_lang", "en");
            setLocale(language);
            recreate();


        }

        loadLocale();
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });


        changeroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), changeRoom.class);
                startActivity(i);
            }
        });

        Button btngetabsents = (Button) findViewById(R.id.showabsentsstudent);
        // SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);


        btngetabsents.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count = 0;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        count += 1;
                        str = "";
                        if (count == 1) {

                            if (dataSnapshot.exists()) {
                                for (DataSnapshot t : dataSnapshot.getChildren()) {
                                    str += t.getKey();
                                    str += "\n";

                                }
                                AlertDialog alertDialog = new AlertDialog.Builder(student_page.this).create();
                                alertDialog.setTitle(id);
                                alertDialog.setMessage(str);
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();


                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(student_page.this).create();
                                alertDialog.setTitle(id);
                                if (language1.equals("ar"))
                                    alertDialog.setMessage("لا يوجد غيابات");
                                else
                                    alertDialog.setMessage("No absence!");

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


        maintenanceRef = FirebaseDatabase.getInstance().getReference().child("Maintenance_Requests");
        myList = new ArrayList<>();
        personsList = new ArrayList<>();
        PersonsListNumbers = new ArrayList<>();
        onSupervisorRef = FirebaseDatabase.getInstance().getReference("Supervisor");
        refPersons = FirebaseDatabase.getInstance().getReference("System_students").child(id);


        fun1();
        fun2();

        Button myInfoClick = (Button) findViewById(R.id.btnStudentInfo);
        myInfoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StudentInfo.class);
                startActivity(i);
            }
        });
        Button requestMaintenancbtn = (Button) findViewById(R.id.btnRequestMaintenance);
        requestMaintenancbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), req_maintenance.class);
                startActivity(i);
            }
        });


        Button internetroomOnclick = (Button) findViewById(R.id.btnIternetRoom);
        internetroomOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), internetRoom.class);
                startActivity(i);
            }
        });
        Button laundryRoomonClick = (Button) findViewById(R.id.btnLaundryRoom);
        laundryRoomonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), laundryRoom.class);
                startActivity(i);
            }
        });
        Button requestforSleepClick = (Button) findViewById(R.id.btnRequestPermission);
        requestforSleepClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), req_permission_for_sleep.class);
                i.putStringArrayListExtra("personsListArray", (ArrayList<String>) personsList);
                i.putStringArrayListExtra("prsonsListNumbersArray", (ArrayList<String>) PersonsListNumbers);
                startActivity(i);
            }
        });
        Button makecompOnclick = (Button) findViewById(R.id.btnMakeComplaint);
        makecompOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), make_complaints.class);
                i.putStringArrayListExtra("supervisor_list", (ArrayList<String>) myList);
                startActivity(i);
            }
        });


        btn = findViewById(R.id.singoutr);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    private void fun2() {
        onSupervisorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        if (t.child("Building").getValue(String.class).equals("B1"))
                            myList.add(t.child("Name").getValue(String.class));
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "no such supervisor", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void fun1() {
        refPersons.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    hPerson1 = (HashMap<String, Object>) dataSnapshot.child("Person1").getValue();
                    hPerson2 = (HashMap<String, Object>) dataSnapshot.child("Person2").getValue();
                    hPerson3 = (HashMap<String, Object>) dataSnapshot.child("Person3").getValue();
                    hPerson4 = (HashMap<String, Object>) dataSnapshot.child("Person4").getValue();
                    hPerson5 = (HashMap<String, Object>) dataSnapshot.child("Person5").getValue();
                    personsList.add(String.valueOf(hPerson1.get("Name")));
                    personsList.add(String.valueOf(hPerson2.get("Name")));
                    personsList.add(String.valueOf(hPerson3.get("Name")));
                    personsList.add(String.valueOf(hPerson4.get("Name")));
                    personsList.add(String.valueOf(hPerson5.get("Name")));
                    PersonsListNumbers.add(String.valueOf(hPerson1.get("Phone")));
                    PersonsListNumbers.add(String.valueOf(hPerson2.get("Phone")));
                    PersonsListNumbers.add(String.valueOf(hPerson3.get("Phone")));
                    PersonsListNumbers.add(String.valueOf(hPerson4.get("Phone")));
                    PersonsListNumbers.add(String.valueOf(hPerson5.get("Phone")));

                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"عربي", "English"};
        android.support.v7.app.AlertDialog.Builder mbuilder = new android.support.v7.app.AlertDialog.Builder(student_page.this);
        mbuilder.setTitle("Choose Language...");
        mbuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    editor.putString("my_lang", "ar");
                    editor.apply();
                    setLocale("ar");
                    recreate();


                } else if (i == 1) {
                    editor.putString("my_lang", "en");
                    editor.apply();
                    setLocale("en");
                    recreate();

                }

                dialogInterface.dismiss();
            }
        });
        android.support.v7.app.AlertDialog mDialog = mbuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());


    }

    public void loadLocale() {


        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();//
        String language = prefs.getString("my_lang", "en");
        setLocale(language);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        editor.putString("count", "0");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("lang", 1);

    }


    private void showDialog() {

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
                        Intent intent = new Intent(student_page.this, Home.class);
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
}



