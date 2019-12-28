package com.example.newversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.newversion.R.layout.support_simple_spinner_dropdown_item;

public class add_Supervisor extends AppCompatActivity {

    int counter=0;
    Spinner SuperVisor_Name,Building_Name,Floor ;

    ArrayList<String> name2;
    ArrayList<String> BuildName;
    ArrayList<String> floor;
    FirebaseDatabase database;
    DatabaseReference myRef;

    String Nameselected="";
    String BuildSel="";
    String floorstr="";
    String [] b;

    int count=0;
    Button btn ;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__supervisor);
        BuildName = new ArrayList<String>();
        name2 = new ArrayList<String>();
        floor = new ArrayList<String>();

        Intent i = getIntent();
        name2 = i.getStringArrayListExtra("stock_list");



        int c = name2.size();
        counter = 0;
        database = FirebaseDatabase.getInstance();
        myRef=database.getReference("Supervisor");


        SuperVisor_Name = (Spinner) findViewById(R.id.Namespinner);
        Building_Name = (Spinner) findViewById(R.id.BuildSpinner);
        Floor = (Spinner) findViewById(R.id.floorSpinner);

        btn = (Button) findViewById(R.id.addbtn);



        BuildName.add("B1 مبنى ");
        BuildName.add("B2 مبنى ");
        BuildName.add("B3 مبنى ");
        BuildName.add("B4 مبنى ");
        BuildName.add("مبنى الاميرة بسمة ");


        floor.add("طابق 1");
        floor.add("طابق 2");
        floor.add("طابق 3");
        floor.add("طابق 4");
        floor.add("طابق 5");
        floor.add("طابق 6");
        floor.add("طابق 7");
        floor.add("طابق 8");



        ArrayAdapter<String> buildadapter = new ArrayAdapter<String>(this, support_simple_spinner_dropdown_item, name2);
        SuperVisor_Name.setAdapter(buildadapter);

        ArrayAdapter<String> buildname = new ArrayAdapter<String>(this, support_simple_spinner_dropdown_item, BuildName);
        Building_Name.setAdapter(buildname);

        ArrayAdapter<String> flooradabter = new ArrayAdapter<String>(this, support_simple_spinner_dropdown_item, floor);
        Floor.setAdapter(flooradabter);


        SuperVisor_Name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Nameselected=(String) SuperVisor_Name.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Building_Name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BuildSel="";
                BuildSel=(String) Building_Name.getItemAtPosition(position).toString();
                if (position==Building_Name.getCount()-1){
                    BuildSel=BuildSel;
                }else
                {
                    b=BuildSel.split(" ");
                    BuildSel=b[0];
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorstr="";
                position+=1;
                floorstr=String.valueOf(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;

                count=0;

                myRef.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        count+=1;
                        if (count==1){
                            for (DataSnapshot innert : dataSnapshot.getChildren()) {

                                if (flag==true)
                                    break;
                                final String str = innert.child("Name").getValue(String.class);
                                if (str.equals(Nameselected)){

                                    myRef.child(innert.getKey()).child("Building").setValue(BuildSel);
                                    myRef.child(innert.getKey()).child("Floor").setValue(""+floorstr);

                                    flag=true;
                                }

                            }
                        }else
                            return;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




    }}
