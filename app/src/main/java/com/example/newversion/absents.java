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

public class absents extends AppCompatActivity {

    ArrayList<AbsentsClass> absentsClassArrayList;


    boolean flag2=false;
    ProgressDialog dialog;
    absentadabter abset;
    LayoutInflater inflater=null;
    ListView listView=null;
    AbsentsClass absentsClass=null;
    HashMap<String,Object>stringObjectHashMap;
    String size="0";
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    final String MYREFERNCES="usernameANDpassword";
    SharedPreferences.Editor editor;
    String msg;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absents);
       absentsClassArrayList = new ArrayList<AbsentsClass>();
       absentsClass=new AbsentsClass();
        final String value = getIntent().getExtras().getString("Msg");

        inflater = getLayoutInflater();
        dialog = new ProgressDialog(this);
        listView =(ListView)findViewById(R.id.absentlist);

        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.show();
stringObjectHashMap=new HashMap<>();
        final String build=value;

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("System_students");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot databaseReference:dataSnapshot.getChildren()) {

                        absentsClass=new AbsentsClass();

                        String building = databaseReference.child("Building").getValue(String.class);
                       String name = databaseReference.child("Name").getValue(String.class);

                        if (build.equals(building)){
                            absentsClass.setId(databaseReference.getKey());
                            absentsClass.setName(name);

                            flag2=true;
                            try {
                                stringObjectHashMap= (HashMap<String, Object>) databaseReference.child("Absents").getValue();
                                size= String.valueOf(stringObjectHashMap.size());
                                absentsClass.setnumber(size);
                                absentsClass.setStringObjectHashMap(stringObjectHashMap);
                                absentsClassArrayList.add(absentsClass);

                            }catch (Exception e){
                                size= "0";
                                absentsClass.setnumber(size);
                                absentsClassArrayList.add(absentsClass);
                            }


                        }

                    }
                    if (flag2){
                        dialog.dismiss();
                        go(absentsClassArrayList);
                    }else{
                        dialog.dismiss();
                        Toast.makeText(absents.this,"لا يوجد طلاب في المبنى",Toast.LENGTH_LONG).show();

                    }




                }else{
                    dialog.dismiss();
                    Toast.makeText(absents.this,"لا يوجد طلاب ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void go(ArrayList<AbsentsClass> absentsClassArrayList) {
        abset = new absentadabter(inflater, absentsClassArrayList);
        listView.setAdapter(abset);
    }
}
