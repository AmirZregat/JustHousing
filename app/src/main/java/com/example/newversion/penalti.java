package com.example.newversion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class penalti extends AppCompatActivity {
    int count=0;

    Button a,allow;
    EditText getid;
    String g;
    ProgressDialog dialog;

    FirebaseDatabase database,d2,database2,d3 ;
    DatabaseReference myRef,myRef2,r2,r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalti);
        dialog = new ProgressDialog(this);

        a= (Button) findViewById(R.id.btnhereee);
        allow= (Button) findViewById(R.id.allow);
        getid= (EditText) findViewById(R.id.idhereeeee);



        database = FirebaseDatabase.getInstance();
        myRef=database.getReference("System_students");

        d3 = FirebaseDatabase.getInstance();
        r3=d3.getReference("System").child("Blocked_Student");

        database2 = FirebaseDatabase.getInstance();
        myRef2=database2.getReference("ResidenceRequests");

        d2 = FirebaseDatabase.getInstance();
        r2=d2.getReference("Buildings");

        allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g=getid.getText().toString();
                r3.child(g).removeValue();
                Toast.makeText(penalti.this,"Done..!",Toast.LENGTH_SHORT).show();

            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g=getid.getText().toString();

                try {
                    Long.parseLong(g);

                    confirmDialogDemo();
                    count=0;


                }catch (Exception e){

                    Toast.makeText(penalti.this,"Please Enter Correct ID",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    private void confirmDialogDemo() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Expulsion From The Housing");

        builder.setMessage("You are about to delete The Record of The Student with ID : "+g+"\n. Are You sure ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes ,I'm Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {




                dothis();


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });

        builder.show();
    }
    void dothis(){

        dialog.setMessage("Please wait...");
        dialog.setIndeterminate(true);
        dialog.show();

        myRef.child(g).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    count+=1;
                    if(count==1) {

                        String nameofBuild = dataSnapshot.child("Building").getValue(String.class);
                        String NumofRoom = dataSnapshot.child("Room_num").getValue(String.class);
                        String roomType = dataSnapshot.child("Room_Type").getValue(String.class);
                        String studentidinRoom = dataSnapshot.child("StudentidinHouse").getValue(String.class);


                        b(nameofBuild, NumofRoom, roomType, studentidinRoom);
                    }else return;
                }else{
                    dialog.dismiss();
                    Toast.makeText(penalti.this,"This Id Is Not Exits",Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
    void b(String name,String Numofroom,String roomtype,String studentidinroom){
        if (name.equals("B4")){

            Toast.makeText(penalti.this,name,Toast.LENGTH_SHORT).show();
            Toast.makeText(penalti.this,Numofroom,Toast.LENGTH_SHORT).show();
            Toast.makeText(penalti.this,roomtype,Toast.LENGTH_SHORT).show();


            if (roomtype.equals("Single")){
                r2.child(name).child(roomtype).child(Numofroom).child(studentidinroom).setValue("0");
            }else if (roomtype.equals("Treble")){
                r2.child(name).child(roomtype).child(Numofroom).child(studentidinroom).setValue("0");
            }else{
                if (roomtype.equals("DoublewithKitchen")){
                    r2.child(name).child("Double").child("Kitchen").child(Numofroom).child(studentidinroom).setValue("0");
                }else{
                    r2.child(name).child("Double").child("WithoutKitchen").child(Numofroom).child(studentidinroom).setValue("0");
                }

            }



        }else if (name.equals("Basmah")){

            if (roomtype.equals("Double_First_wing")){
                r2.child(name).child("Double").child("First_wing").child(Numofroom).child(studentidinroom).setValue("0");
            }else if (roomtype.equals("Double_Second_wing_Kitchen")){
                r2.child(name).child("Double").child("Second_wing").child("Kitchen").child(Numofroom).child(studentidinroom).setValue("0");
            }else if (roomtype.equals("Double_Second_wing_WithoutKitchen")){
                r2.child(name).child("Double").child("Second_wing").child("WithoutKitchen").child(Numofroom).child(studentidinroom).setValue("0");
            }else if (roomtype.equals("Single_First_wing")){
                r2.child(name).child("Single").child("First_wing").child(Numofroom).child(studentidinroom).setValue("0");
            }else if (roomtype.equals("Single_Second_wing_Kitchen")){
                r2.child(name).child("Single").child("Second_wing").child("Kitchen").child(Numofroom).child(studentidinroom).setValue("0");
            } else if (roomtype.equals("Single_Second_wing_WithoutKitchen")){
                r2.child(name).child("Single").child("Second_wing").child("WithoutKitchen").child(Numofroom).child(studentidinroom).setValue("0");
            }

        }else {
                r2.child("B").child(name).child(roomtype).child(Numofroom).child(studentidinroom).setValue("0");
        }


        Task<Void> i= myRef.child(g).removeValue();

        r3.child(g).child("value").setValue("1");

        Task<Void> ii= myRef2.child(g).removeValue();
        dialog.dismiss();
        if (!ii.isSuccessful()){
            Toast.makeText(penalti.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(penalti.this,"Nooo",Toast.LENGTH_SHORT).show();



    }
}
