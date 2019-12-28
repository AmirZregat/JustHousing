package com.example.newversion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class generalsetting extends AppCompatActivity {
    Button addparentuser,StopRE,turnon,addSuper,addNewSuper,changeforsuper,changeformanager,summer,normal;
    Button deleteallInfo;
    int count= 0,f= 0,count2= 0,count3 = 0,count4 = 0,count5 = 0,count6 = 0,count7 = 0,count8 = 0,count10 = 0,count11 = 0,count15 = 0,count16 = 0,count17 = 0;
    FirebaseDatabase Systemuser;
    DatabaseReference Systemuserref;
    HashMap<String ,String> ShashMap,Nhashmap;
    FirebaseDatabase newdatabase,pricedatabase;
    DatabaseReference newref,priceMyref;
    ArrayList<String> Name,name2,BuildName;
    FirebaseDatabase __databaseAdd;
    DatabaseReference myre2;
    int coounter=0;



    final String Normal_B_Room_Double="620";
    final String Summer_B_Room_Double="310";

    final String Normal_B_Room_single="492";
    final String Summer_B_Room_single="245";

    final String Normal_B_Room_special="660";
    final String Summer_B_Room_special="330";


    //B4
    final String Normal_B4_Room_Double="300";
    final String Summer_B4_Room_Double="150";

    final String Normal_B4_Room_Triple="250";
    final String Summer_B4_Room_Triple="125";

    final String Normal_B4_studio_Double="750";
    final String Summer_B4_studio_Double="375";

    final String Normal_B4_studio_Single="1250";
    final String Summer_B4_studio_Single="625";

    //Basmah
    final String Normal_Basmah_Double_First="600";
    final String Summer_Basmah_Double_First="300";

    final String Normal_Basmah_Double_Second="650";
    final String Summer_Basmah_Double_Second="325";

    final String Normal_Basmah_Double_Second_Kitchen="700";
    final String Summer_Basmah_Double_Second_Kitchen="350";

    final String Normal_Basmah_Single_First="1000";
    final String Summer_Basmah_Single_First="500";

    final String Normal_Basmah_Single_Second="1050";
    final String Summer_Basmah_Single_Second="525";

    final String Normal_Basmah_Single_Second_Kitchen="1100";
    final String Summer_Basmah_Single_Second_Kitchen="550";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalsetting);
        __databaseAdd = FirebaseDatabase.getInstance();
        pricedatabase = FirebaseDatabase.getInstance();

        Name = new ArrayList<String>();
        name2 = new ArrayList<String>();
        myre2 = __databaseAdd.getReference("Supervisor");
        priceMyref = __databaseAdd.getReference("System").child("prices");

        Nhashmap=new HashMap<String, String>();

        Nhashmap.put("Year",
                "9999999");
        Nhashmap.put("Month","1");
        Nhashmap.put("Day","1");

        Nhashmap.put("Hour","1");
        Nhashmap.put("Minute","1");


        newdatabase = FirebaseDatabase.getInstance();
        newref =newdatabase.getReference("System").child("Request_Status");

        addparentuser = (Button) findViewById(R.id.adduserforparents);
        StopRE = (Button) findViewById(R.id.StopRequests);
        deleteallInfo = (Button) findViewById(R.id.DeleteAllInfo);
        turnon = (Button) findViewById(R.id.turnONrequest);
        addSuper= (Button) findViewById(R.id.AddSupervisro);
        addNewSuper= (Button) findViewById(R.id.addsuperandpassword);
        changeforsuper= (Button) findViewById(R.id.changepasswordforSuper);
        changeformanager= (Button) findViewById(R.id.changepasswordformanager);
        summer= (Button) findViewById(R.id.summerprices);
        normal= (Button) findViewById(R.id.normalprices);





        Systemuser = FirebaseDatabase.getInstance();
        Systemuserref = Systemuser.getReference();


        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                priceMyref.child("B").child("Room_Double").setValue(Summer_B_Room_Double);
                priceMyref.child("B").child("Room_single").setValue(Summer_B_Room_single);
                priceMyref.child("B").child("Room_special").setValue(Summer_B_Room_special);

                priceMyref.child("B4").child("Room_Double").setValue(Summer_B4_Room_Double);
                priceMyref.child("B4").child("Room_Triple").setValue(Summer_B4_Room_Triple);
                priceMyref.child("B4").child("studio_Double").setValue(Summer_B4_studio_Double);
                priceMyref.child("B4").child("studio_Single").setValue(Summer_B4_studio_Single);

                priceMyref.child("Basmah").child("Double_First").setValue(Summer_Basmah_Double_First);
                priceMyref.child("Basmah").child("Double_Second").setValue(Summer_Basmah_Double_Second);
                priceMyref.child("Basmah").child("Double_Second_Kitchen").setValue(Normal_Basmah_Double_Second_Kitchen);
                priceMyref.child("Basmah").child("Single_First").setValue(Summer_Basmah_Single_First);
                priceMyref.child("Basmah").child("Single_Second").setValue(Summer_Basmah_Single_Second);
                priceMyref.child("Basmah").child("Single_Second_Kitchen").setValue(Summer_Basmah_Single_Second_Kitchen);


                Toast.makeText(generalsetting.this, "Done !!", Toast.LENGTH_SHORT).show();


            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceMyref.child("B").child("Room_Double").setValue(Normal_B_Room_Double);
                priceMyref.child("B").child("Room_single").setValue(Normal_B_Room_single);
                priceMyref.child("B").child("Room_special").setValue(Normal_B_Room_special);

                priceMyref.child("B4").child("Room_Double").setValue(Normal_B4_Room_Double);
                priceMyref.child("B4").child("Room_Triple").setValue(Normal_B4_Room_Triple);
                priceMyref.child("B4").child("studio_Double").setValue(Normal_B4_studio_Double);
                priceMyref.child("B4").child("studio_Single").setValue(Normal_B4_studio_Single);

                priceMyref.child("Basmah").child("Double_First").setValue(Normal_Basmah_Double_First);
                priceMyref.child("Basmah").child("Double_Second").setValue(Normal_Basmah_Double_Second);
                priceMyref.child("Basmah").child("Double_Second_Kitchen").setValue(Normal_Basmah_Double_Second_Kitchen);
                priceMyref.child("Basmah").child("Single_First").setValue(Normal_Basmah_Single_First);
                priceMyref.child("Basmah").child("Single_Second").setValue(Normal_Basmah_Single_Second);
                priceMyref.child("Basmah").child("Single_Second_Kitchen").setValue(Normal_Basmah_Single_Second_Kitchen);
                Toast.makeText(generalsetting.this, "Done !!", Toast.LENGTH_SHORT).show();

            }
        });

        StopRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generalsetting.this, stop_request.class);
                startActivity(intent);


            }
        });


        changeforsuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generalsetting.this, changepasswordforsuper.class);
                startActivity(intent);


            }
        });
        changeformanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generalsetting.this, changepasswordformanager.class);
                startActivity(intent);


            }
        });


        addNewSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generalsetting.this, addingsuperviso.class);
                startActivity(intent);


            }
        });

        addSuper.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                coounter=0;


                myre2.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(coounter==0){
                            coounter+=1;
                            for (DataSnapshot innert : dataSnapshot.getChildren()) {

                                Name.add(innert.child("Name").getValue(String.class));
                            }

                            Intent intent = new Intent(generalsetting.this, add_Supervisor.class);

                            intent.putStringArrayListExtra("stock_list", Name);
                            startActivity(intent);
                        }




                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });

        turnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newref.setValue(Nhashmap);
                Toast.makeText(generalsetting.this, "Done !!", Toast.LENGTH_SHORT).show();


            }
        });


        addparentuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generalsetting.this, addparentforuserr.class);
                startActivity(intent);


            }
        });

        deleteallInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialogDemo();

            }
        });

    }
    private void confirmDialogDemo() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Records !!");

        builder.setMessage("You are about to delete The Records \nAre You sure ?");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes ,I'm Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                __DeleteAll();
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



    void __DeleteAll() {
        __deletSystemuser();
        __deletOutsleep();
        __deletfather();
        __deletResidenceRequests();
        __deletMaintenance();
        __deletB4_Double_kitchen();
        __deletB4_Double_withoutkitchen();
        __deletB4_Treble();
        __deletB4_Single();
        __deletBasmah_Double_First_wing();
        __deletBasmah_Double_Second_wing_Kitchen();
        __deletBasmah_Double_Second_wing_withoutKitchen();
        __deletBasmah_Single_First_wing();
        __deletBasmah_Single_Second_wing_Kitchen();
        __deletBasmah_Single_Second_wing_withoutKitchen();
        __deletB_Double();
        __deletB_Single();
        __deletB_Special();
        Toast.makeText(generalsetting.this,"Success Delete",Toast.LENGTH_LONG).show();


    }





    void __deletB_Special() {
        count17=0;
        Systemuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count17+=1;
                if(count17==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("B").getChildren()) {

                        for (DataSnapshot t : dataSnapshot.child("Buildings").child("B").child(innert.getKey()).child("Special").getChildren()) {

                            Systemuserref.child("Buildings").child("B").child(innert.getKey()).child("Special").child(t.getKey()).child("Student_Id").setValue("0");
                        }
                    }
                }
                else
                    return;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    void __deletB_Single(){
        count16=0;
        Systemuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count16+=1;
                if(count16==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("B").getChildren()) {

                        for (DataSnapshot t : dataSnapshot.child("Buildings").child("B").child(innert.getKey()).child("Single").getChildren()) {


                            Systemuserref.child("Buildings").child("B").child(innert.getKey()).child("Single").child(t.getKey()).child("Student_Id").setValue("0");

                        }
                    }
                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletB_Double(){
        count15=0;
        Systemuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count15+=1;
                if(count15==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("B").getChildren()) {

                        for (DataSnapshot t : dataSnapshot.child("Buildings").child("B").child(innert.getKey()).child("Double").getChildren()) {


                            Systemuserref.child("Buildings").child("B").child(innert.getKey()).child("Double").child(t.getKey()).child("Student_Id").setValue("0");
                            Systemuserref.child("Buildings").child("B").child(innert.getKey()).child("Double").child(t.getKey()).child("Student_Id2").setValue("0");
                        }
                    }
                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletBasmah_Single_First_wing(){
        count11=0;
        Systemuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count11+=1;
                if(count11==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("Basmah").child("Single").child("First_wing").getChildren()) {

                        Systemuserref.child("Buildings").child("Basmah").child("Single").child("First_wing").child(innert.getKey()).child("Student_Id").setValue("0");



                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletBasmah_Single_Second_wing_Kitchen(){
        count8=0;

        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count8+=1;
                if(count8==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("Basmah").child("Single").child("Second_wing").child("Kitchen").getChildren()) {
                        Systemuserref.child("Buildings").child("Basmah").child("Single").child("Second_wing").child("Kitchen").child(innert.getKey()).child("Student_Id").setValue("0");
                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletBasmah_Single_Second_wing_withoutKitchen(){
        count7=0;

        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count7+=1;
                if(count7==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("Basmah").child("Single").child("Second_wing").child("WithoutKitchen").getChildren()) {
                        Systemuserref.child("Buildings").child("Basmah").child("Single").child("Second_wing").child("WithoutKitchen").child(innert.getKey()).child("Student_Id").setValue("0");

                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletBasmah_Double_Second_wing_withoutKitchen(){
        count6=0;

        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count6+=1;
                if(count6==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("Basmah").child("Double").child("Second_wing").child("WithoutKitchen").getChildren()) {
                        Systemuserref.child("Buildings").child("Basmah").child("Double").child("Second_wing").child("WithoutKitchen").child(innert.getKey()).child("Student_Id").setValue("0");
                        Systemuserref.child("Buildings").child("Basmah").child("Double").child("Second_wing").child("WithoutKitchen").child(innert.getKey()).child("Student_Id2").setValue("0");
                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletBasmah_Double_Second_wing_Kitchen(){
        count5=0;

        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count5+=1;
                if(count5==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("Basmah").child("Double").child("Second_wing").child("Kitchen").getChildren()) {
                        Systemuserref.child("Buildings").child("Basmah").child("Double").child("Second_wing").child("Kitchen").child(innert.getKey()).child("Student_Id").setValue("0");
                        Systemuserref.child("Buildings").child("Basmah").child("Double").child("Second_wing").child("Kitchen").child(innert.getKey()).child("Student_Id2").setValue("0");
                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletBasmah_Double_First_wing(){
        count10=0;
        Systemuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count10+=1;
                if(count10==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("Basmah").child("Double").child("First_wing").getChildren()) {

                        Systemuserref.child("Buildings").child("Basmah").child("Double").child("First_wing").child(innert.getKey()).child("Student_Id").setValue("0");
                        Systemuserref.child("Buildings").child("Basmah").child("Double").child("First_wing").child(innert.getKey()).child("Student_Id2").setValue("0");


                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletB4_Treble(){
        count3=0;


        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count3+=1;
                if(count3==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("B4").child("Treble").getChildren()) {

                        Systemuserref.child("Buildings").child("B4").child("Treble").child(innert.getKey()).child("Student_Id").setValue("0");
                        Systemuserref.child("Buildings").child("B4").child("Treble").child(innert.getKey()).child("Student_Id2").setValue("0");
                        Systemuserref.child("Buildings").child("B4").child("Treble").child(innert.getKey()).child("Student_Id3").setValue("0");

                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletB4_Single(){
        count2=0;


        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count2+=1;
                if(count2==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("B4").child("Single").getChildren()) {

                        Systemuserref.child("Buildings").child("B4").child("Single").child(innert.getKey()).child("Student_Id").setValue("0");

                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletB4_Double_withoutkitchen(){
         f=0;


        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                f+=1;
                if(f==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("B4").child("Double").child("WithoutKitchen").getChildren()) {

                        Systemuserref.child("Buildings").child("B4").child("Double").child("WithoutKitchen").child(innert.getKey()).child("Student_Id").setValue("0");
                        Systemuserref.child("Buildings").child("B4").child("Double").child("WithoutKitchen").child(innert.getKey()).child("Student_Id2").setValue("0");

                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void __deletSystemuser() {
        Systemuserref.child("System_students").setValue(0000);
    }
    void __deletOutsleep() {
        Systemuserref.child("Sleep_outside_requests").setValue(0000);
    }
    void __deletfather() {
        Systemuserref.child("Student_Father").setValue(0000);
    }
    void __deletResidenceRequests() {
        Systemuserref.child("ResidenceRequests").setValue(0000);

    }
    void __deletMaintenance() {
        Systemuserref.child("Maintenance_Requests").setValue(0000);
    }
    void __deletB4_Double_kitchen() {
        count=0;

        Systemuserref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count+=1;
                if(count==1){
                    for (DataSnapshot innert : dataSnapshot.child("Buildings").child("B4").child("Double").child("Kitchen").getChildren()) {

                        Systemuserref.child("Buildings").child("B4").child("Double").child("Kitchen").child(innert.getKey()).child("Student_Id").setValue("0");
                        Systemuserref.child("Buildings").child("B4").child("Double").child("Kitchen").child(innert.getKey()).child("Student_Id2").setValue("0");

                    }

                }
                else
                    return;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


 }


}
