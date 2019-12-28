package com.example.newversion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.net.IDN;
import java.util.HashMap;

public class changeRoom extends AppCompatActivity {
    Button submit2;
    FirebaseDatabase __databaseAdd;
    DatabaseReference myre2, firststudentmyref, systemref, mystatusref, reqqquestrevi;
    ProgressDialog dialog;
    EditText firstIdedit, seondIdedit;
    SharedPreferences p;
    SharedPreferences.Editor editor;
    HashMap<String, String> infohashmap;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;
    HashMap<String, Object> firststudenthashmapHashMap;
    HashMap<String, Object> secondstudenthashmapHashMap;
    String language;
    TextView myrequest, requestrecieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_room);


        p = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        language = p.getString("my_lang", "");


        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        final String firsstid = prefs.getString("Id", null);

        __databaseAdd = FirebaseDatabase.getInstance();
        systemref = __databaseAdd.getReference("System");
        firststudenthashmapHashMap = new HashMap<String, Object>();
        secondstudenthashmapHashMap = new HashMap<String, Object>();
        infohashmap = new HashMap<String, String>();

        mystatusref = __databaseAdd.getReference("System").child("Roomchangerequests").child(firsstid);

        firstIdedit = findViewById(R.id.firstStudentidforChangeroom);
        seondIdedit = findViewById(R.id.secondStudentidforChangeroom);
        /*

         */
        myrequest = findViewById(R.id.myrequestchangeroomstaus);
        requestrecieve = findViewById(R.id.requestchangeroomstaus);
        requestrecieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqqquestrevi = __databaseAdd.getReference("System").child("Roomchangerequests");
                reqqquestrevi.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            boolean flag = false;
                            for (final DataSnapshot t : dataSnapshot.getChildren()) {

                                if (t.child("SecondStudentId").getValue(String.class).equals(firsstid)) {
                                    flag = true;
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(changeRoom.this);
                                    if (language.equals("ar"))
                                        builder.setTitle("هناك طلب لتغيير الغرفة لك ");
                                    else
                                        builder.setTitle("There is a request to change the room with you");

                                    if (language.equals("ar"))
                                        builder.setMessage("تريد طالبة مبادلة غرفتها مع غرفتك هل انتي موافقة؟ " + "\n" + "رقمها الجامعي :" + t.getKey());
                                    else
                                        builder.setMessage("There is a request to change the room with you .. do you accept?" + "\n" + "her Id " + t.getKey());
                                    builder.setCancelable(true);


                                    builder.setPositiveButton("yes!", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            reqqquestrevi.child(t.getKey()).child("SecondStudentStatus").setValue("Yes");
                                            dialog.cancel();

                                        }
                                    });
                                    builder.setNegativeButton("no!", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            reqqquestrevi.child(t.getKey()).child("SecondStudentStatus").setValue("No");
                                            dialog.cancel();
                                        }
                                    });

                                    builder.show();
                                    break;
                                } else
                                    continue;

                            }
                            if (!flag) {

                                AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                                alertDialog.setTitle("" + firsstid);
                                if (language.equals("ar"))
                                    alertDialog.setMessage("لا يوجد طلبات لك");
                                else
                                    alertDialog.setMessage("There is no requests for you!");

                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }


                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                            alertDialog.setTitle("" + firsstid);
                            if (language.equals("ar"))
                                alertDialog.setMessage("لا يوجد طلبات لك");
                            else
                                alertDialog.setMessage("There is no requests for you!");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        myrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mystatusref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            HashMap<String, String> stringStringHashMap = (HashMap<String, String>) dataSnapshot.getValue();
                            String str = stringStringHashMap.get("SecondStudentStatus");
                            String strmanager = stringStringHashMap.get("mangerstatus");

                            if (str.equals("Waiting")) {

                                AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                                alertDialog.setTitle("" + firsstid);
                                if (language.equals("ar"))
                                    alertDialog.setMessage("لم يتم رؤية طلبك من قبل الطالبة الاخرى ");
                                else
                                    alertDialog.setMessage("Your request was not seen by the other student!");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            } else if (str.equals("No")) {
                                AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                                alertDialog.setTitle("" + firsstid);
                                if (language.equals("ar"))
                                    alertDialog.setMessage("تم رفض طلبك من قبل الطالبة الاخرى ");
                                else
                                    alertDialog.setMessage("Your request was rejected by the other student!");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            } else if (str.equals("Yes")) {

                                if (strmanager.equals("Waiting")) {

                                    AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                                    alertDialog.setTitle("" + firsstid);
                                    if (language.equals("ar"))
                                        alertDialog.setMessage("لم يتم رؤية طلبك من قبل المدير");
                                    else
                                        alertDialog.setMessage("Your request was not seen by the Admin");

                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                } else if (strmanager.equals("No")) {
                                    AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                                    alertDialog.setTitle("" + firsstid);
                                    if (language.equals("ar"))
                                        alertDialog.setMessage("تم رفض طلبك من قبل المدير ,يرجى مراجعة الادارة لمعرفة السبب. ");
                                    else
                                        alertDialog.setMessage("Your request has been rejected by the Admin, please see the administration to find out why.");

                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                } else if (strmanager.equals("Yes")) {
                                    AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                                    alertDialog.setTitle("" + firsstid);
                                    if (language.equals("ar"))
                                        alertDialog.setMessage("تم تغيير غرفتك يمكن الذهاب اليها الان  ");
                                    else
                                        alertDialog.setMessage("Your room has been changed!");

                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                }
                            }


                        } else {


                            AlertDialog alertDialog = new AlertDialog.Builder(changeRoom.this).create();
                            alertDialog.setTitle("" + firsstid);
                            if (language.equals("ar"))
                                alertDialog.setMessage("لم تقم بتقديم طلبات !!");
                            else
                                alertDialog.setMessage("You have not applied !!");

                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "done!",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        firstIdedit.setText(firsstid);
        firstIdedit.setEnabled(false);



        submit2 = (Button) findViewById(R.id.submitbunforchangeroom);
        submit2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String secondstudent = seondIdedit.getText().toString().trim();

                if (TextUtils.isEmpty(secondstudent)) {
                    if (language.equals("ar"))
                        Toast.makeText(getApplicationContext(), "ادخل الرقم الثاني ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Enter your friend ID ", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        Long.parseLong(secondstudent);

                        if (firsstid.equals(secondstudent)) {
                            if (language.equals("ar"))
                                Toast.makeText(getApplicationContext(), "اختر رقما غير رقمك  ", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(), "Choose a number other than your number", Toast.LENGTH_SHORT).show();

                        } else {
                            myre2 = __databaseAdd.getReference("System_students").child(secondstudent);
                            myre2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        secondstudenthashmapHashMap = (HashMap<String, Object>) dataSnapshot.getValue();
                                        beforesendRequest(secondstudenthashmapHashMap, firsstid, secondstudent);
                                    } else if (language.equals("ar"))
                                        Toast.makeText(getApplicationContext(), "الرقم غير مخزن  ", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(getApplicationContext(), "The number is not stored!", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }


                    } catch (Exception e) {
                        if (language.equals("ar"))
                            Toast.makeText(getApplicationContext(), "الرجاء ادخل ارقام صحيحة  ", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "Please enter correct numbers  ", Toast.LENGTH_SHORT).show();

                        return;
                    }

                }


            }
        });


    }

    private void beforesendRequest(final HashMap<String, Object> secondstudenthashmapHashMap,
                                   final String firsstid, final String secondstudent) {

        firststudentmyref = __databaseAdd.getReference("System").child("Roomchangerequests").child(firsstid);
        firststudentmyref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (language.equals("ar"))
                        Toast.makeText(getApplicationContext(), "يمكنك تقديم طلب واحد فقط  ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "You can only apply for one  ", Toast.LENGTH_SHORT).show();

                } else
                    sendRequest(secondstudenthashmapHashMap, firsstid, secondstudent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendRequest(final HashMap<String, Object> secondstudenthashmapHashMap,
                             final String firsstid, final String secondstudent) {
        firststudentmyref = __databaseAdd.getReference("System_students").child(firsstid);
        firststudentmyref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                firststudenthashmapHashMap = (HashMap<String, Object>) dataSnapshot.getValue();

                String buildforfirst = String.valueOf(firststudenthashmapHashMap.get("Building"));
                String buildforsecond = String.valueOf(secondstudenthashmapHashMap.get("Building"));


                if (buildforfirst.equals(buildforsecond)) {

                    infohashmap.put("SecondStudentId", secondstudent);

                    infohashmap.put("SecondStudentStatus", "Waiting");
                    infohashmap.put("mangerstatus", "Waiting");

                    systemref.child("Roomchangerequests").child(firsstid).setValue(infohashmap);
                    if (language.equals("ar"))
                        Toast.makeText(getApplicationContext(), "تم اضافة بيناتك يرجى انتظار موافقة الطالبة الاخرى  ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Your information has been added Please wait for the approval of the other student", Toast.LENGTH_SHORT).show();


                } else if (language.equals("ar"))
                    Toast.makeText(getApplicationContext(), "يجب ان تكون الطالبة الاخرى في نفس المبنى ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "The other student must be in the same building ", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
