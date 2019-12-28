package com.example.newversion;

import android.app.ProgressDialog;
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

public class compiantActivit extends AppCompatActivity {
    ArrayList<complaintinfooClass>complaintinfooClasses;
    ProgressDialog dialog;
    complaintinfooClass complaintinfooClass;
    LayoutInflater inflater=null;
    ListView listView=null;
    FirebaseDatabase database;
    DatabaseReference myRef,myRef2;
    ord_can ordMyadapter;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiant);
         value = getIntent().getExtras().getInt("M");
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Complaints").child("OnSupervisor");
        myRef2=database.getReference("Complaints").child("OnStudent");
        complaintinfooClasses=new ArrayList<>();

        inflater = getLayoutInflater();
        listView =(ListView)findViewById(R.id.complaintlistview);
        dialog = new ProgressDialog(this);

        dialog.setMessage("please wait...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

if (value==1){
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            complaintinfooClasses=new ArrayList<>();
            if (dataSnapshot.exists()){
                for (DataSnapshot t:dataSnapshot.getChildren()) {
                    HashMap<String,Object> stringObjectHashMap = (HashMap<String, Object>) dataSnapshot.child(t.getKey()).getValue();
                    complaintinfooClass = new complaintinfooClass();

                    complaintinfooClass.setComplaintText(String.valueOf(stringObjectHashMap.get("ComplaintText")));
                    complaintinfooClass.setIdOfpersonWhoMadeComplaint(t.getKey());
                    complaintinfooClass.setName(String.valueOf(stringObjectHashMap.get("Name")));


                    complaintinfooClasses.add(complaintinfooClass);
                }
                dialog.dismiss();
                go(complaintinfooClasses);

            }else
            {dialog.dismiss();
                Toast.makeText(compiantActivit.this,"لا يوجد شكاوى على المشرفات ",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}else if (value==2){
    myRef2.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            complaintinfooClasses=new ArrayList<>();
            if (dataSnapshot.exists()){
                for (DataSnapshot t:dataSnapshot.getChildren()) {
                    HashMap<String,Object> stringObjectHashMap = (HashMap<String, Object>) dataSnapshot.child(t.getKey()).getValue();
                    complaintinfooClass = new complaintinfooClass();

                    complaintinfooClass.setComplaintText(String.valueOf(stringObjectHashMap.get("ComplaintText")));
                    complaintinfooClass.setIdOfpersonWhoMadeComplaint(t.getKey());
                    complaintinfooClass.setName(String.valueOf(stringObjectHashMap.get("Name")));


                    complaintinfooClasses.add(complaintinfooClass);
                }
                dialog.dismiss();
                go(complaintinfooClasses);

            }else
            {dialog.dismiss();
                Toast.makeText(compiantActivit.this,"لا يوجد شكاوى على الطالبات ",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}








    }
    public void go(ArrayList<complaintinfooClass>complaintinfooClasses){
        ordMyadapter = new ord_can(inflater, complaintinfooClasses,value);
            listView.setAdapter(ordMyadapter);


    }
}
