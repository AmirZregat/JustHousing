package com.example.newversion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class maintainSuperovisor extends AppCompatActivity {
    FirebaseDatabase database ;
    DatabaseReference myRef,myRef2;

    ArrayList<maintaincce> maintaincceArrayList;
    ProgressDialog dialog;
    maintaincce mainntncobject;
    LayoutInflater inflater=null;
    ListView listView=null;

    maintainceforadapter mainadapter;
    int value;
    String build="";
    String floor="";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_superovisor); dialog = new ProgressDialog(this);

        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();
        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        String id = prefs.getString("Id", null);

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("Supervisor").child(id);
        myRef2 = database.getReference("Maintenance_Requests");



        maintaincceArrayList=new ArrayList<>();
        inflater = getLayoutInflater();
        listView =(ListView)findViewById(R.id.listformaintiance);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 build=dataSnapshot.child("Building").getValue(String.class);
                 floor=dataSnapshot.child("Floor").getValue(String.class);

                 go1(build,floor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void go1(final String build, String floor) {


        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maintaincceArrayList=new ArrayList<>();
                if (dataSnapshot.exists()){
                    for (DataSnapshot t: dataSnapshot.getChildren()) {
                        String localbuil=t.child("Building").getValue(String.class);

                        if (localbuil.equals(build)){

                            HashMap<String,Object> stringObjectHashMap = (HashMap<String, Object>) dataSnapshot.child(t.getKey()).getValue();
                            mainntncobject = new maintaincce();

                            mainntncobject.setText(String.valueOf(stringObjectHashMap.get("text")));
                            mainntncobject.setStudent_Id(t.getKey());
                            mainntncobject.setRoom_num(String.valueOf(stringObjectHashMap.get("Room_num")));


                            maintaincceArrayList.add(mainntncobject);
                        }else
                        continue;

                    }

                    go(maintaincceArrayList);
                }else{
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"لا يوجد طبات صاينة ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void go(ArrayList<maintaincce> maintaincceArrayList) {
if (maintaincceArrayList.isEmpty()){
    dialog.dismiss();
    Toast.makeText(getApplicationContext(),"لا يوجد طبات صاينة ",Toast.LENGTH_LONG).show();

}else{
    mainadapter = new maintainceforadapter(inflater, maintaincceArrayList,0);
    dialog.dismiss();
    listView.setAdapter(mainadapter);
}

    }
}
