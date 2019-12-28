package com.example.newversion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;

public class login extends AppCompatActivity {
    ProgressDialog dialog;
    Button LogInButton,guestlogin;
    EditText Password;
    EditText Idd;
    HashMap p = null;
    boolean Isfemale = false;
    int test = 0;
    String Id, password;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        final String id = prefs.getString("Id", null);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            int i = savedInstanceState.getInt("lang");

        } else {
            String language = prefs.getString("my_lang", "en");
            setLocale(language);
            recreate();


        }

        loadLocale();



        guestlogin=findViewById(R.id.btn_guest);
        guestlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),guest_page.class);
                startActivity(i);
            }
        });
        dialog = new ProgressDialog(this);
        Password = findViewById(R.id.managerpass);
        Idd = findViewById(R.id.managerid);
        LogInButton = findViewById(R.id.emplogin);

        p = new HashMap<String, Object>();
        final String value = getIntent().getExtras().getString("M");
        if (!value.isEmpty())
            Toast.makeText(login.this, value, Toast.LENGTH_SHORT).show();



        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();


        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Id = Idd.getText().toString().trim();
                password = Password.getText().toString().trim();
                if (TextUtils.isEmpty(Id)) {
                    Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();


                    return;
                } else if (TextUtils.isEmpty(password)) {


                    Toast.makeText(login.this, "Enter  password", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.setMessage("Logging in please wait...");
                dialog.setIndeterminate(true);
                dialog.show();

                try {
                    ref=FirebaseDatabase.getInstance().getReference("System_students").child(Id);
                    Long.parseLong(Id);
                    ManagerSign();

                } catch (Exception e) {
                    dialog.dismiss();
                    Toast.makeText(login.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void ManagerSign() {
        Query query = FirebaseDatabase.getInstance().getReference("Manager").child(Id);
        query.addListenerForSingleValueEvent(Manger);

    }
    private void Supervisorsinin(String id) {

        Query query5 = FirebaseDatabase.getInstance().getReference("Supervisor").child(Id);
        query5.addListenerForSingleValueEvent(supervisore);
    }
    private void parentsinin(String id) {
        Query query2 = FirebaseDatabase.getInstance().getReference("Student_Father").child(Id);
        query2.addListenerForSingleValueEvent(Parent);
    }
    private void studentsignin(String id) {
        Query query4 = FirebaseDatabase.getInstance().getReference("Student").child(Id);
        query4.addListenerForSingleValueEvent(Student);
    }


    ValueEventListener Manger = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        String th = dataSnapshot.child("Password").getValue(String.class);

                        if (password.equals(th)) {

                            editor.putString("TableName", "Manager");
                            editor.putString("Id", dataSnapshot.getKey());
                            editor.putString("Password", th);
                            editor.apply();

                            dialog.dismiss();
                            Intent intent = new Intent(login.this, AdminPage.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Idd.setText("");
                            Password.setText("");
                            dialog.dismiss();
                            Toast.makeText(login.this, " The password is not correct ", Toast.LENGTH_SHORT).show();

                        }


                    } else {

                        Supervisorsinin(Id);
                        dialog.dismiss();


                    }



            }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            //  Toast.makeText(supervisor.this,"Error in data base ", Toast.LENGTH_SHORT).show();
        }
    };
    ValueEventListener Parent = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        String th = dataSnapshot.child("Password").getValue(String.class);
                        if (password.equals(th)) {

                            editor.putString("TableName", "Student_Father");
                            editor.putString("Id", dataSnapshot.getKey());
                            editor.putString("Password", th);
                            editor.apply();


                            dialog.dismiss();
                            Intent intent = new Intent(login.this, guardian_page.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Idd.setText("");
                            Password.setText("");
                            dialog.dismiss();
                            Toast.makeText(login.this, " The password is not correct ", Toast.LENGTH_SHORT).show();

                        }


                    } else {
                        studentsignin(Id);


                    }

                }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(login.this, "parent ", Toast.LENGTH_SHORT).show();


        }
    };
    ValueEventListener supervisore = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        String th = dataSnapshot.child("Password").getValue(String.class);


                        if (password.equals(th)) {

                            editor.putString("Id", dataSnapshot.getKey());
                            editor.putString("TableName", "Supervisor");
                            editor.putString("Password", th);
                            editor.apply();
                            dialog.dismiss();
                            Intent intent = new Intent(login.this, supervisor.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Idd.setText("");
                            Password.setText("");
                            dialog.dismiss();
                            Toast.makeText(login.this, " The password is not correct ", Toast.LENGTH_SHORT).show();

                        }


                    } else {


                        parentsinin(Id);


                    }

                }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(login.this, "Error in data base ", Toast.LENGTH_SHORT).show();

        }
    };
    ValueEventListener Student = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                    if (dataSnapshot.exists()) {
                        String th = dataSnapshot.child("Password").getValue(String.class);
                        String gender = dataSnapshot.child("Gender").getValue(String.class);

                        if (gender.equals("Female")) {
                            Isfemale = true;
                            if (password.equals(th)) {
                                editor.putString("Id", dataSnapshot.getKey());
                                editor.putString("Password", th);
                                editor.putString("TableName", "Student");
                                editor.apply();
                                test = 1;
                                cheick(dataSnapshot.getKey());

                            } else {
                                Idd.setText("");
                                Password.setText("");
                                dialog.dismiss();
                                Toast.makeText(login.this, " The password is not correct ", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Idd.setText("");
                            Password.setText("");
                            dialog.dismiss();
                            Toast.makeText(login.this, "this site is for Female only", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Idd.setText("");
                        Password.setText("");
                        dialog.dismiss();

                        Toast.makeText(login.this, " لا يوجد مستخدم لهذا الرقم !!", Toast.LENGTH_SHORT).show();

                    }

                }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            //  Toast.makeText(supervisor.this,"Error in data base ", Toast.LENGTH_SHORT).show();

        }
    };


    void cheick(final String id) {

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dialog.dismiss();
                    Intent intent2 = new Intent(login.this, student_page.class);
                    intent2.putExtra("id", id);
                    startActivity(intent2);
                    finish();
                } else {
                    dialog.dismiss();
                    Intent intent2 = new Intent(login.this, prereserv.class);
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
        // Save instne state

        editor.putString("count", "0");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("lang", 1);

    }


}
