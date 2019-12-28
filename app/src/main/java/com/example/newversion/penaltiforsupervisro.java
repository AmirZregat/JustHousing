package com.example.newversion;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class penaltiforsupervisro extends AppCompatActivity {
int count=0,count2=0;
    FirebaseDatabase database ;
    DatabaseReference myRef,myRef2;
    EditText Idd;
    Button btn , btn2;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penaltiforsupervisro);

        database = FirebaseDatabase.getInstance();
        dialog = new ProgressDialog(this);
        Idd=(EditText)findViewById(R.id.panaltiedittext);



        myRef2 = database.getReference("System_students");

        btn=(Button)findViewById(R.id.penaltiforsuperbtn);
        btn2=(Button)findViewById(R.id.penaltiforsuperbtn20);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("الرجاء الانتظار..");
                dialog.setIndeterminate(true);
                dialog.show();
                count = 0;
               final String Id = Idd.getText().toString().trim();

                if (TextUtils.isEmpty(Id)) {
                    Toast.makeText(penaltiforsupervisro.this, "ادخل رقم الطالب", Toast.LENGTH_SHORT).show();


                    return;
                } else {
                    try {
                        Long.parseLong(Id);

                        myRef = database.getReference("System_students").child(Id);

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                count+=1;
                                if (count==1){
                                    if (dataSnapshot.exists()){

                                        go(Id,0);
                                    }else{
                                        dialog.dismiss();
                                        Toast.makeText(getApplicationContext(),"لا يوجد مستخدم بهذا الرقم",Toast.LENGTH_LONG).show();
                                    }
                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"الرجاء ادخال ارقام فقط :*",Toast.LENGTH_LONG).show();
                    }

            }


                }
            });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("الرجاء الانتظار..");
                dialog.setIndeterminate(true);
                dialog.show();
                count = 0;
                final String Id = Idd.getText().toString().trim();

                if (TextUtils.isEmpty(Id)) {
                    Toast.makeText(penaltiforsupervisro.this, "ادخل رقم الطالب", Toast.LENGTH_SHORT).show();


                    return;
                } else {
                    try {
                        Long.parseLong(Id);

                        myRef = database.getReference("System_students").child(Id);

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                count+=1;
                                if (count==1){
                                    if (dataSnapshot.exists()){

                                        go2(Id,0);
                                    }else{
                                        dialog.dismiss();
                                        Toast.makeText(getApplicationContext(),"لا يوجد مستخدم بهذا الرقم",Toast.LENGTH_LONG).show();
                                    }
                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"الرجاء ادخال ارقام فقط :*",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });
        }



    void go(String id,int i) {
count2=i;
        myRef = database.getReference("Student").child(id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count2+=1;
                if (count2==1){
                    String str = dataSnapshot.child("Student_Housing_Bill").getValue(String.class);
                    double bill = 0;
                    bill = Double.parseDouble(str);
                    bill = bill + 10;


                    dialog.dismiss();
                    Task task =  myRef.child("Student_Housing_Bill").setValue(String.valueOf(bill));
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "تم اضافة الغرامة ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "لم تتم اضافة الغرامة", Toast.LENGTH_LONG).show();
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    void go2(String id,int i) {
        count2=i;
        myRef = database.getReference("Student").child(id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count2+=1;
                if (count2==1){
                    String str = dataSnapshot.child("Student_Housing_Bill").getValue(String.class);
                    double bill = 0;
                    bill = Double.parseDouble(str);
                    bill = bill + 20;


                    dialog.dismiss();
                    Task task =  myRef.child("Student_Housing_Bill").setValue(String.valueOf(bill));
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "تم اضافة الغرامة ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "لم تتم اضافة الغرامة", Toast.LENGTH_LONG).show();
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

        }
