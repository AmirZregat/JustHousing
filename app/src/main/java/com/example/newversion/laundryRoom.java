package com.example.newversion;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class laundryRoom extends AppCompatActivity {
    TextView status;
    Button res, endRes;
    DatabaseReference refffOnlaundry;
    DatabaseReference laundryRoom_studentId_Reff, myRef;
    ;
    String select;
    Boolean f = false;
    Boolean x = false;
    String string;
    ImageView img;
    Boolean a = false;


    String id = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs, p;
    String s, language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_room);

        p = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        language = p.getString("my_lang", "");


        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);
        status = findViewById(R.id.LaundryRoomStatus);
        res = findViewById(R.id.btnLaundryRoomReservation);
        endRes = findViewById(R.id.btnLaundryRoomEndReservation);
        img = findViewById(R.id.img_laundry);
        myRef = FirebaseDatabase.getInstance().getReference("System_students").child(id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    string = dataSnapshot.child("Building").getValue(String.class);
                    testBuil(string);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusRef();
            }
        });
        endRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endRes(string);
            }
        });
    }


    private void testBuil(String string) {

        if (string.equals("Basmah")) {
            refffOnlaundry = FirebaseDatabase.getInstance().getReference("Buildings").child(string).child("LaundryRoom");
            refffOnlaundry.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot t : dataSnapshot.getChildren()) {
                            String test = t.child("Student_Id").getValue(String.class);
                            if (test.equals(id)) {
                                a = true;
                                res.setEnabled(false);

                                res.setBackgroundResource(R.drawable.hassantest);

                                res.setBackgroundColor(getResources().getColor(Color.blue(Color.BLUE)));
                                select = t.getKey();
                                endRes.setEnabled(true);
                                endRes.setBackgroundResource(R.drawable.btn_bg3);

                                if (language.equals("ar"))
                                    status.setText("الرجاء الضغط على انهاء الحجز عند الانتهاء");
                                else
                                    status.setText("Please press end reservation when you finish!");

                                img.setImageResource(R.drawable.washing2);

                            }
                        }
                        if (!a) {
                            res();
                        }

                    } else {
                        return;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else if (string.equals("B4")) {
            refffOnlaundry = FirebaseDatabase.getInstance().getReference("Buildings").child(string).child("LaundryRoom");
            refffOnlaundry.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot t : dataSnapshot.getChildren()) {
                            String test = t.child("Student_Id").getValue(String.class);
                            if (test.equals(id)) {
                                a = true;
                                res.setEnabled(false);
                                res.setBackgroundResource(R.drawable.hassantest);
                                select = t.getKey();
                                endRes.setEnabled(true);
                                endRes.setBackgroundResource(R.drawable.btn_bg3);

                                if (language.equals("ar"))
                                    status.setText("الرجاء الضغط على انهاء الحجز عند الانتهاء");
                                else
                                    status.setText("Please press end reservation when you finish!");
                                img.setImageResource(R.drawable.washing2);

                            }
                        }
                        if (!a) {
                            res();
                        }

                    } else {
                        return;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {


            refffOnlaundry = FirebaseDatabase.getInstance().getReference("Buildings").child("B").child(string).child("LaundryRoom");
            refffOnlaundry.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot t : dataSnapshot.getChildren()) {
                            String test = t.child("Student_Id").getValue(String.class);
                            if (test.equals(id)) {
                                a = true;
                                res.setEnabled(false);
                                res.setBackgroundResource(R.drawable.hassantest);

                                select = t.getKey();
                                endRes.setEnabled(true);
                                endRes.setBackgroundResource(R.drawable.btn_bg3);

                                if (language.equals("ar"))
                                    status.setText("الرجاء الضغط على انهاء الحجز عند الانتهاء");
                                else
                                    status.setText("Please press end reservation when you finish!");

                                img.setImageResource(R.drawable.washing2);

                            }
                        }
                        if (!a) {
                            res();
                        }

                    } else {
                        return;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }

    private void res() {
        refffOnlaundry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        if (x) break;
                        String test = t.child("Student_Id").getValue(String.class);
                        if (test.equals("0")) {
                            if (language.equals("ar"))
                                status.setText("يمكنك الحجز !");
                            else
                                status.setText("you can reserve!");

                            img.setImageResource(R.drawable.washin1);
                            res.setEnabled(true);
                            res.setBackgroundResource(R.drawable.btn_bg2);
                            x = true;
                            endRes.setEnabled(false);
                            endRes.setBackgroundResource(R.drawable.hassantest);
                        }


                    }
                    if (!x) {
                        if (language.equals("ar"))
                            status.setText("الغرفة ممتلئه !");
                        else
                            status.setText("Room is Full!");

                        img.setImageResource(R.drawable.washing2);
                    }


                } else
                    return;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void endRes(String string) {

        if (string.equals("Basmah")) {

            laundryRoom_studentId_Reff = FirebaseDatabase.getInstance().getReference("Buildings").child(string).child("LaundryRoom").child(select);
            laundryRoom_studentId_Reff.child("Student_Id").setValue("0");
            endRes.setEnabled(false);
            endRes.setBackgroundResource(R.drawable.hassantest);
            img.setImageResource(R.drawable.washing2);
            if (language.equals("ar"))
                status.setText("نتمنى لكم يوماً جميلاً");
            else
                status.setText("Have a nice day!");
        } else if (string.equals("B4")) {
            laundryRoom_studentId_Reff = FirebaseDatabase.getInstance().getReference("Buildings").child(string).child("LaundryRoom").child(select);
            laundryRoom_studentId_Reff.child("Student_Id").setValue("0");
            endRes.setEnabled(false);
            endRes.setBackgroundResource(R.drawable.hassantest);
            if (language.equals("ar"))
                status.setText("نتمنى لكم يوماً جميلاً");
            else
                status.setText("Have a nice day!");
            img.setImageResource(R.drawable.washing2);

        } else {

            laundryRoom_studentId_Reff = FirebaseDatabase.getInstance().getReference("Buildings").child("B").child(string).child("LaundryRoom").child(select);
            laundryRoom_studentId_Reff.child("Student_Id").setValue("0");
            endRes.setEnabled(false);
            endRes.setBackgroundResource(R.drawable.hassantest);
            if (language.equals("ar"))
                status.setText("نتمنى لكم يوماً جميلاً");
            else
                status.setText("Have a nice day!");

            img.setImageResource(R.drawable.washing2);
        }


    }


    private void statusRef() {
        refffOnlaundry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {

                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        if (f) break;
                        String test = t.child("Student_Id").getValue(String.class);
                        if (test.equals("0")) {
                            f = true;
                            refffOnlaundry.child(t.getKey()).child("Student_Id").setValue(id);
                            res.setEnabled(false);
                            res.setBackgroundResource(R.drawable.hassantest);
                            endRes.setEnabled(true);
                            endRes.setBackgroundResource(R.drawable.btn_bg3);
                            if (language.equals("ar"))
                                Toast.makeText(getApplicationContext(), "الرجاء الضغط على انهاء الحجز عند الانتهاء!", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(), "Press end reservation when you finish!", Toast.LENGTH_LONG).show();

                            select = t.getKey();
                        }


                    }


                } else
                    return;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}



