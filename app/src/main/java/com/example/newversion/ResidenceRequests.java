package com.example.newversion;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResidenceRequests extends AppCompatActivity {


    ArrayList<studentinfoo> h,No,yes;
    ProgressDialog dialog;
     myadapter ordMyadapter;
    LayoutInflater inflater=null;
     ListView listView=null;
     studentinfoo std=null;
    studentinfoo Norequest=null;
    studentinfoo Yesrequest=null;
    Spinner testsp;

    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.residencerequests);


        inflater = getLayoutInflater();

        dialog = new ProgressDialog(this);

        testsp = (Spinner)findViewById(R.id.testspinner);

        ArrayList<String> info = new ArrayList<String>();
        info.add("اختر ...");
        info.add("الطلبات الحالية ");
        info.add("الطلبات التي تم رفضها ");
        info.add("الطلبات التي تم قبولها ");

        h = new ArrayList<studentinfoo>();
        No = new ArrayList<studentinfoo>();
        yes = new ArrayList<studentinfoo>();

                int count=0;

                 dialog.setMessage("please wait...");
                dialog.setIndeterminate(false);
                dialog.show();


         query = FirebaseDatabase.getInstance().getReference();


        query.addValueEventListener(test);




        listView =(ListView)findViewById(R.id.list);



        ArrayAdapter<String> buildadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,info);
        testsp.setAdapter(buildadapter);




        testsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             int BuildSelected = position;
             dialog.show();
                // Toast.makeText(supervisor.this,"+"+x,Toast.LENGTH_LONG).show();
                if(position ==0){
                    position=0;
                    dialog.dismiss();

                }
                 else  if(position==1)
                {
                    boolean flag = true;
                    ordMyadapter = new myadapter(inflater, h,1);
                    if(!ordMyadapter.isEmpty()){
                        listView.setAdapter(ordMyadapter);
                        dialog.dismiss();

                        flag=false;

                    }else
                        Toast.makeText(ResidenceRequests.this,"empty",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();


                }
                else if (position ==2)
                {
                    boolean flag = true;
                    ordMyadapter = new myadapter(inflater, No,2);
                    if(!ordMyadapter.isEmpty()){
                        listView.setAdapter(ordMyadapter);
                        dialog.dismiss();

                        flag=false;

                    }else
                        Toast.makeText(ResidenceRequests.this,"empty",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();


                }
                else  if (position ==3)
                {
                    boolean flag = true;
                    ordMyadapter = new myadapter(inflater, yes,3);
                    if(!ordMyadapter.isEmpty()){
                        listView.setAdapter(ordMyadapter);
                        dialog.dismiss();

                        flag=false;

                    }else
                        Toast.makeText(ResidenceRequests.this,"empty",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }


    ValueEventListener test = new ValueEventListener() {


        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        h = new ArrayList<studentinfoo>();
                        No = new ArrayList<studentinfoo>();
                        yes = new ArrayList<studentinfoo>();

                    for (DataSnapshot t: dataSnapshot.child("ResidenceRequests").getChildren()) {


                        String status = t.child("Status").getValue(String.class);
                        if(status.equals("Waiting")) {
                            std = new studentinfoo();
                            std.setStatus(status);
                            String Studentnum = t.child("Student_Num").getValue(String.class);
                            std.setStudent_Num(Studentnum);
                            String SRoom_Num = t.child("SRoom_Num").getValue(String.class);
                            std.setSRoom_Num(SRoom_Num);
                            String name = dataSnapshot.child("Student").child(Studentnum).child("Name").getValue(String.class);
                            std.setName(name);


                            String birthdayplace = dataSnapshot.child("Student").child(Studentnum).child("Birthplace").getValue(String.class);
                            std.setThisbirthdayplace(birthdayplace);

                            String email = dataSnapshot.child("Student").child(Studentnum).child("Email").getValue(String.class);
                            std.setThisemail(email);

                            String Nationality = dataSnapshot.child("Student").child(Studentnum).child("Nationality").getValue(String.class);
                            std.setThisnationality(Nationality);

                           // long Penalties = dataSnapshot.child("Student").child(Studentnum).child("Penalties").getValue(Long.class);
                            //std.setThispenality(""+Penalties);




                           //for index
                            std.setParent(t.getKey().toString());
                            h.add(std);
                        }
                       else if(status.equals("No")) {
                            Norequest = new studentinfoo();
                            Norequest.setStatus(status);
                            String Studentnum = t.child("Student_Num").getValue(String.class);
                            Norequest.setStudent_Num(Studentnum);
                            String SRoom_Num = t.child("SRoom_Num").getValue(String.class);
                            Norequest.setSRoom_Num(SRoom_Num);
                            String name = dataSnapshot.child("Student").child(Studentnum).child("Name").getValue(String.class);
                            Norequest.setName(name);


                            String birthdayplace = dataSnapshot.child("Student").child(Studentnum).child("Birthplace").getValue(String.class);
                            Norequest.setThisbirthdayplace(birthdayplace);

                            String email = dataSnapshot.child("Student").child(Studentnum).child("Email").getValue(String.class);
                            Norequest.setThisemail(email);

                            String Nationality = dataSnapshot.child("Student").child(Studentnum).child("Nationality").getValue(String.class);
                            Norequest.setThisnationality(Nationality);

                            // long Penalties = dataSnapshot.child("Student").child(Studentnum).child("Penalties").getValue(Long.class);
                            //std.setThispenality(""+Penalties);

                            //for index
                            Norequest.setParent(t.getKey().toString());
                            No.add(Norequest);
                        }
                         else if(status.equals("Yes")) {
                            Yesrequest = new studentinfoo();
                            Yesrequest.setStatus(status);
                            String Studentnum = t.child("Student_Num").getValue(String.class);
                            Yesrequest.setStudent_Num(Studentnum);
                            String SRoom_Num = t.child("SRoom_Num").getValue(String.class);
                            Yesrequest.setSRoom_Num(SRoom_Num);
                            String name = dataSnapshot.child("Student").child(Studentnum).child("Name").getValue(String.class);
                            Yesrequest.setName(name);


                            String birthdayplace = dataSnapshot.child("Student").child(Studentnum).child("Birthplace").getValue(String.class);
                            Yesrequest.setThisbirthdayplace(birthdayplace);

                            String email = dataSnapshot.child("Student").child(Studentnum).child("Email").getValue(String.class);
                            Yesrequest.setThisemail(email);

                            String Nationality = dataSnapshot.child("Student").child(Studentnum).child("Nationality").getValue(String.class);
                            Yesrequest.setThisnationality(Nationality);

                            // long Penalties = dataSnapshot.child("Student").child(Studentnum).child("Penalties").getValue(Long.class);
                            //std.setThispenality(""+Penalties);

                            //for index
                            Yesrequest.setParent(t.getKey().toString());
                            yes.add(Yesrequest);
                        }

                    }

                    dialog.dismiss();
   } else {




                }
            }




        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {



        }
    };

}
