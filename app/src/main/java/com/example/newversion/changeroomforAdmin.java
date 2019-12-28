package com.example.newversion;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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

public class changeroomforAdmin extends AppCompatActivity {

    FirebaseDatabase database ;
    DatabaseReference myRef,myRef2;
    boolean flag=false;
    ArrayList<classchangeroom>changeroomArrayList;
    ProgressDialog dialog;
    classchangeroom classchangeroomobj;
    LayoutInflater inflater=null;
    ListView listView=null;
    HashMap<String,Object> stringObjectHashMap;
    changeroomadapter chamgeroomadapterobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeroomfor_admin);
        changeroomArrayList = new ArrayList<classchangeroom>();
        classchangeroomobj=new classchangeroom();


        dialog = new ProgressDialog(this);
        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.show();
        inflater = getLayoutInflater();
        listView =(ListView)findViewById(R.id.listforchangeroom);


        stringObjectHashMap=new HashMap<>();


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("System").child("Roomchangerequests");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 flag=false;
                if (dataSnapshot.exists()){

                    for (DataSnapshot databaseReference:dataSnapshot.getChildren()) {


                        classchangeroomobj = new classchangeroom();

                        String firstid = databaseReference.getKey();
                        String SecondStudentId= databaseReference.child("SecondStudentId").getValue(String.class);
                        String SecondStudentStatus = databaseReference.child("SecondStudentStatus").getValue(String.class);
                        String managerstas = databaseReference.child("mangerstatus").getValue(String.class);
                        if (SecondStudentStatus.equals("Yes")){

                            if (managerstas.equals("Yes")||managerstas.equals("No")){
                                continue;
                            }else{

                                flag=true;

                                classchangeroomobj.setFirststudentid(firstid);
                                classchangeroomobj.setSecondStudentId(SecondStudentId);
                                classchangeroomobj.setSecondStudentStatus(SecondStudentStatus);
                                changeroomArrayList.add(classchangeroomobj);
                            }
                        }

                      }
                    if (flag)
                    setadapter(changeroomArrayList);
                    else
                        Toast.makeText(getApplicationContext(),"لا يوجد طلبات ",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),"لا يوجد طلبات ",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
        }

    private void setadapter(ArrayList<classchangeroom> changeroomArrayList) {
        dialog.dismiss();
        chamgeroomadapterobj = new changeroomadapter(inflater, changeroomArrayList);
        listView.setAdapter(chamgeroomadapterobj);
    }

}


