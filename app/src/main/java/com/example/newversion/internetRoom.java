package com.example.newversion;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class internetRoom extends AppCompatActivity {
    TextView internetRoomstatus;
    Button BtnRes, BtnEndRes;
    DatabaseReference refffOnInternet;
    DatabaseReference InternetRoom_studentId_Reff, myRef;
    String selectId;
    private Boolean f1 = false;
    private Boolean x1 = false;
    String string, language;
    Boolean a = false;
    ImageView img;
    String id = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs, p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_room);
        p = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        language = p.getString("my_lang", "");

        img = findViewById(R.id.img_internet);
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);
        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        //
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

        BtnRes = findViewById(R.id.btnInternetRoomReservation);
        BtnEndRes = findViewById(R.id.btnInternetRoomEndReservation);


        BtnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusRef(string);

            }
        });
        BtnEndRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endRes(string);
            }
        });


    }

    private void testBuil(String string) {

        if (string.equals("Basmah")) {
            refffOnInternet = FirebaseDatabase.getInstance().getReference("Buildings").child(string).child("InternetRoom");
            internetRoomstatus = findViewById(R.id.InternetRoomStatus);
            refffOnInternet.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot t : dataSnapshot.getChildren()) {
                            String test = t.child("Student_Id").getValue(String.class);
                            if (test.equals(id)) {
                                a = true;
                                BtnRes.setEnabled(false);
                                BtnRes.setBackgroundResource(R.drawable.hassantest);
                                selectId = t.getKey();
                                BtnEndRes.setEnabled(true);
                                BtnEndRes.setBackgroundResource(R.drawable.btn_bg3);
                                if (language.equals("ar"))
                                    internetRoomstatus.setText("الرجاء الضغط على انهاء الحجز عند الانتهاء");
                                else
                                    internetRoomstatus.setText("Please press end reservation when you finish!");
                                img.setImageResource(R.drawable.wifi2);
                            }
                        }
                        if (!a) {
                            res();
                        }
                    } else return;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else if (string.equals("B4")) {
            refffOnInternet = FirebaseDatabase.getInstance().getReference("Buildings").child(string).child("InternetRoom");
            internetRoomstatus = findViewById(R.id.InternetRoomStatus);
            refffOnInternet.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot t : dataSnapshot.getChildren()) {
                            String test = t.child("Student_Id").getValue(String.class);
                            if (test.equals(id)) {
                                a = true;
                                BtnRes.setEnabled(false);
                                BtnRes.setBackgroundResource(R.drawable.hassantest);
                                selectId = t.getKey();
                                BtnEndRes.setEnabled(true);
                                BtnEndRes.setBackgroundResource(R.drawable.btn_bg3);
                                img.setImageResource(R.drawable.wifi2);
                                if (language.equals("ar"))
                                    internetRoomstatus.setText("الرجاء الضغط على انهاء الحجز عند الانتهاء");
                                else
                                    internetRoomstatus.setText("Please press end reservation when you finish!");

                            }
                        }
                        if (!a) {
                            res();
                        }
                    } else return;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            refffOnInternet = FirebaseDatabase.getInstance().getReference("Buildings").child("B").child(string).child("InternetRoom");
            internetRoomstatus = findViewById(R.id.InternetRoomStatus);
            refffOnInternet.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot t : dataSnapshot.getChildren()) {
                            String test = t.child("Student_Id").getValue(String.class);
                            if (test.equals(id)) {
                                a = true;
                                BtnRes.setEnabled(false);
                                BtnRes.setBackgroundResource(R.drawable.hassantest);
                                selectId = t.getKey();
                                BtnEndRes.setEnabled(true);
                                BtnEndRes.setBackgroundResource(R.drawable.btn_bg3);

                                img.setImageResource(R.drawable.wifi2);
                                if (language.equals("ar"))
                                    internetRoomstatus.setText("الرجاء الضغط على انهاء الحجز عند الانتهاء");
                                else
                                    internetRoomstatus.setText("Please press end reservation when you finish!");

                            }
                        }
                        if (!a) {
                            res();
                        }
                    } else return;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }

    private void res() {
        refffOnInternet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        if (x1) break;
                        String test = t.child("Student_Id").getValue(String.class);
                        if (test.equals("0")) {
                            img.setImageResource(R.drawable.wifi1);
                            if (language.equals("ar"))
                                internetRoomstatus.setText("يمكنك الحجز !");
                            else
                                internetRoomstatus.setText("You can reserve!");

                            BtnRes.setEnabled(true);
                            BtnRes.setBackgroundResource(R.drawable.btn_bg2);
                            BtnEndRes.setEnabled(false);
                            BtnEndRes.setBackgroundResource(R.drawable.hassantest);
                            x1 = true;
                        }


                    }
                    if (!x1) {
                        if (language.equals("ar"))
                            internetRoomstatus.setText("الغرفة ممتلئه !");
                        else
                            internetRoomstatus.setText("Room is Full!");
                        img.setImageResource(R.drawable.wifi2);
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
            InternetRoom_studentId_Reff = FirebaseDatabase.getInstance().getReference().child("Buildings").child(string).child("InternetRoom").child(String.valueOf(selectId));
            InternetRoom_studentId_Reff.child("Student_Id").setValue("0");
            BtnEndRes.setEnabled(false);
            BtnEndRes.setBackgroundResource(R.drawable.hassantest);
            if (language.equals("ar"))
                internetRoomstatus.setText("نتمنى لكم يوماً جميلاً");
            else
                internetRoomstatus.setText("Have a nice day!");
            img.setImageResource(R.drawable.wifi2);
        } else if (string.equals("B4")) {
            InternetRoom_studentId_Reff = FirebaseDatabase.getInstance().getReference().child("Buildings").child(string).child("InternetRoom").child(String.valueOf(selectId));
            InternetRoom_studentId_Reff.child("Student_Id").setValue("0");
            if (language.equals("ar"))
                internetRoomstatus.setText("نتمنى لكم يوماً جميلاً");
            else
                internetRoomstatus.setText("Have a nice day!");

            img.setImageResource(R.drawable.wifi2);

            BtnEndRes.setBackgroundResource(R.drawable.btn_bg2);
        } else {
            InternetRoom_studentId_Reff = FirebaseDatabase.getInstance().getReference().child("Buildings").child("B").child(string).child("InternetRoom").child(String.valueOf(selectId));
            InternetRoom_studentId_Reff.child("Student_Id").setValue("0");
            if (language.equals("ar"))
                internetRoomstatus.setText("نتمنى لكم يوماً جميلاً");
            else
                internetRoomstatus.setText("Have a nice day!");
            img.setImageResource(R.drawable.wifi2);
            BtnEndRes.setEnabled(false);
            BtnEndRes.setBackgroundResource(R.drawable.hassantest);

        }

    }


    private void statusRef(String string) {
        refffOnInternet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {

                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        if (f1) break;
                        String test = t.child("Student_Id").getValue(String.class);
                        if (test.equals("0")) {
                            f1 = true;
                            refffOnInternet.child(t.getKey()).child("Student_Id").setValue(id);
                            if (language.equals("ar"))
                                Toast.makeText(getApplicationContext(), "الرجاء الضغط على انهاء الحجز عند الانتهاء!", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(), "Press end reservation when you finish!", Toast.LENGTH_LONG).show();
                            BtnRes.setEnabled(false);
                            BtnRes.setBackgroundResource(R.drawable.hassantest);
                            BtnEndRes.setEnabled(true);
                            BtnEndRes.setBackgroundResource(R.drawable.btn_bg3);

                            selectId = t.getKey();
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