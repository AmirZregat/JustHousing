package com.example.newversion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class showinfoo extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef, myre2;


    Button sh;
    EditText idedit;
    String id = "0000";
    int count = 0;

    TextView name, roomNumber, buildnum, studentphone, fatherphone, motherphone, brotherphone, workfahterphone, homephone, city, street, state, email, client, onedaytrip, multitrips, sleepoutsidewhilefixing, sleepoutside, person1name, person2name, person3name, person4name, person5name;

    HashMap hashMap, perso1, person2, perosn3, person4, person5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfoo);

        sh = (Button) findViewById(R.id.searchbtn);
        idedit = (EditText) findViewById(R.id.idsearchedit);

        database = FirebaseDatabase.getInstance();
        hashMap = new HashMap<String, Object>();
        perso1 = new HashMap<String, Object>();
        person2 = new HashMap<String, Object>();
        perosn3 = new HashMap<String, Object>();
        person4 = new HashMap<String, Object>();
        person5 = new HashMap<String, Object>();
        myRef = database.getReference("System_students");
        //**********init

        name = (TextView) findViewById(R.id.infoNametxt);
        roomNumber = (TextView) findViewById(R.id.RoomNumber);
        buildnum = (TextView) findViewById(R.id.infoBuildNumber);
        studentphone = (TextView) findViewById(R.id.infoStudentphone);
        fatherphone = (TextView) findViewById(R.id.infoparenphne);
        motherphone = (TextView) findViewById(R.id.infomotherphone);
        brotherphone = (TextView) findViewById(R.id.infobrotherphone);
        workfahterphone = (TextView) findViewById(R.id.infoWorknumber);
        homephone = (TextView) findViewById(R.id.infohomephone);
        city = (TextView) findViewById(R.id.infocity);
        street = (TextView) findViewById(R.id.infoStreet);
        state = (TextView) findViewById(R.id.infostate);
        email = (TextView) findViewById(R.id.infoEmail);
        client = (TextView) findViewById(R.id.infoagent);
        onedaytrip = (TextView) findViewById(R.id.infoOne);
        multitrips = (TextView) findViewById(R.id.infoMulti);
        person1name = (TextView) findViewById(R.id.infoperson1);
        person2name = (TextView) findViewById(R.id.infoperson2);
        person3name = (TextView) findViewById(R.id.infoperson3);
        person4name = (TextView) findViewById(R.id.infoperson4);
        person5name = (TextView) findViewById(R.id.infoperson5);
        sleepoutside = (TextView) findViewById(R.id.infoOutside);
        sleepoutsidewhilefixing = (TextView) findViewById(R.id.infoPrepareoutsie);

        //**********endOfini


        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    id = idedit.getText().toString();
                    Long.parseLong(id);

                    __getinfo(id);
                } catch (Exception e) {
                    Toast.makeText(showinfoo.this, "الرجاء التأكد من الرقم", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    void __getinfo(final String id) {
        count = 0;

        myRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count += 1;
                if (count == 1) {
                    if (dataSnapshot.exists()) {

                        hashMap = (HashMap) dataSnapshot.getValue();
                        perso1 = (HashMap) hashMap.get("Person1");
                        person2 = (HashMap) hashMap.get("Person1");
                        perosn3 = (HashMap) hashMap.get("Person3");
                        person4 = (HashMap) hashMap.get("Person4");
                        person5 = (HashMap) hashMap.get("Person5");


                        sleepoutsidewhilefixing.setText((String) hashMap.get("Sleep_in_holidays"));
                        sleepoutside.setText((String) hashMap.get("Sleep_any_time"));
                        person1name.setText((String) perso1.get("Name"));
                        person2name.setText((String) person2.get("Name"));
                        person3name.setText((String) perosn3.get("Name"));
                        person4name.setText((String) person4.get("Name"));
                        person5name.setText((String) person5.get("Name"));
                        workfahterphone.setText((String) hashMap.get("Father_phone_num"));
                        homephone.setText((String) hashMap.get("Home_phone_num"));
                        city.setText((String) hashMap.get("City"));
                        street.setText((String) hashMap.get("Street"));
                        state.setText("Not provided");
                        email.setText((String) hashMap.get("Email"));
                        client.setText((String) hashMap.get("Name_of_the_client"));
                        onedaytrip.setText((String) hashMap.get("One_day_trip"));
                        multitrips.setText((String) hashMap.get("More_than_one_day_trip"));
                        name.setText((String) hashMap.get("Name"));
                        roomNumber.setText((String) hashMap.get("Room_num"));
                        buildnum.setText((String) hashMap.get("Building"));
                        studentphone.setText((String) hashMap.get("Student_phone_num"));
                        fatherphone.setText((String) hashMap.get("Father_phone_num"));
                        motherphone.setText((String) hashMap.get("mother_phone_num"));
                        brotherphone.setText("Not provided");


                    } else
                        Toast.makeText(showinfoo.this, "does not exit", Toast.LENGTH_SHORT).show();


                } else
                    return;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
