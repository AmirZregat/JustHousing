package com.example.newversion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class regesterpage extends AppCompatActivity {

    Spinner buildSpinner, discSpinner;
    Button Reger;

    int BuildSelected = 0;
    int DiscSelected = 0;

    String name;
    int count = 0;

    String room_double;
    String Room_single;
    String Room_special;

    String room_doubleB4;
    String Room_TripleB4;
    String studio_DoubleB4;
    String studio_SingleB4;
    TextView textView;
    String Double_First;
    String Double_Second;
    String Double_Second_Kitchen;
    String Single_First;
    String Single_Second;
    String Single_Second_Kitchen;

    CheckBox checkBox;

    FirebaseDatabase database, __databaseAdd, anotherdata;
    DatabaseReference myRef, myre2, getprices;
    String id = "0";

    String f = "";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;

    ArrayList<HashMap<String, String>> hashMapswitharray;

    HashMap hashMap, person1, person2, person3, person4, person5;


    public void __Add__user(final String id, final String BuildName, final String Room, final String Roomtype, final String studentidinhous, final String price) {


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Student").child(id);


        myRef.child("Student_Housing_Bill").setValue(price);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                hashMap = (HashMap) dataSnapshot.getValue();


                hashMap.put("Building", BuildName);
                hashMap.put("Room_num", Room);
                hashMap.put("Room_Type", Roomtype);
                hashMap.put("StudentidinHouse", studentidinhous);

                hashMap.put("Brother_phone_num", "Not Provided !!");
                hashMap.put("City", "Not Provided !!");
                hashMap.put("Father_phone_num", "Not Provided !!");
                hashMap.put("Home_phone_num", "Not Provided !!");
                hashMap.put("More_than_one_day_trip", "Not Provided !!");
                hashMap.put("Name_of_the_client", "Not Provided !!");
                hashMap.put("Neighborhood", "Not Provided !!");
                hashMap.put("One_day_trip", "Not Provided !!");
                hashMap.put("Sleep_any_time", "Not Provided !!");
                hashMap.put("Sleep_in_holidays", "Not Provided !!");
                hashMap.put("Street", "Not Provided !!");
                hashMap.put("mother_phone_num", "Not Provided !!");
                hashMap.put("mother_phone_num", "Not Provided !!");
                hashMap.put("Student_Housing_Bill", price);


                hassan(hashMap, id);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    void hassan(HashMap n, String id) {

        __databaseAdd = FirebaseDatabase.getInstance();
        myre2 = __databaseAdd.getReference("System_students");

        person1 = new HashMap<String, Object>();
        person2 = new HashMap<String, Object>();
        person3 = new HashMap<String, Object>();
        person4 = new HashMap<String, Object>();
        person5 = new HashMap<String, Object>();


        person1.put("Name", "Not Provided !!");
        person1.put("Phone", "Not Provided !!");

        person2.put("Name", "Not Provided !!");
        person2.put("Phone", "Not Provided !!");

        person3.put("Name", "Not Provided !!");
        person3.put("Phone", "Not Provided !!");

        person4.put("Name", "Not Provided !!");
        person4.put("Phone", "Not Provided !!");

        person5.put("Name", "Not Provided !!");
        person5.put("Phone", "Not Provided !!");


        myre2.child(id).setValue(n);

        myre2.child(id).child("Person1").setValue(person1);
        myre2.child(id).child("Person2").setValue(person2);
        myre2.child(id).child("Person3").setValue(person3);
        myre2.child(id).child("Person4").setValue(person4);
        myre2.child(id).child("Person5").setValue(person5);


        Toast.makeText(regesterpage.this, "Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(regesterpage.this, Home.class);

        startActivity(intent);
        finish();
    }


    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regesterpage);
        final String value = getIntent().getExtras().getString("id");

        Reger = (Button) findViewById(R.id.regester);
        count = 0;
        //id=value;

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);
        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        textView = findViewById(R.id.txt88);
        checkBox = findViewById(R.id.check);

        boolean flag = checkBox.isChecked();
        Reger.setEnabled(false);
        Reger.setBackgroundResource(R.drawable.hassantest);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Reger.setEnabled(true);
                    Reger.setBackgroundResource(R.drawable.btn_bg2);


                } else {
                    Reger.setEnabled(false);
                    Reger.setBackgroundResource(R.drawable.hassantest);
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), taahd.class);
                startActivity(i);
            }
        });

        hashMapswitharray = new ArrayList<>();
        dialog = new ProgressDialog(this);

        dialog.setMessage("Loading");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();


        anotherdata = FirebaseDatabase.getInstance();
        getprices = anotherdata.getReference("System").child("prices");


        getprices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count += 1;
                if (count == 1) {

                    if (dataSnapshot.exists()) {
                        hashMapswitharray = new ArrayList<>();
                        for (DataSnapshot t : dataSnapshot.getChildren()) {
                            HashMap<String, String> stringObjectHashMap = new HashMap<>();

                            stringObjectHashMap = (HashMap<String, String>) dataSnapshot.child(t.getKey()).getValue();

                            hashMapswitharray.add(stringObjectHashMap);


                            // Toast.makeText(getApplicationContext(),""+stringObjectHashMap.get("Room_Double"),Toast.LENGTH_LONG).show();

                        }

                        setspinner(hashMapswitharray);


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void confirmDialogDemo(final String string) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("تسجيل في السكن ");

        builder.setMessage("تكلفة هذه الغرفة هي " + string + " دينار " +
                "\nهل انتي متاكدة من اتمام عملية التسجيل !");

        builder.setCancelable(false);

        builder.setPositiveButton("نعم, متاكدة !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (BuildSelected == 0) {
                    ___B__Regester(id, DiscSelected, string);
                    // Toast.makeText(regesterpage.this,"B"+BuildSelected+DiscSelected+string,Toast.LENGTH_SHORT).show();
                } else if (BuildSelected == 1) {
                    ___Basmah__Regester(id, DiscSelected, string);
                    // Toast.makeText(regesterpage.this,"basmah"+BuildSelected+DiscSelected+string,Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(regesterpage.this,"b4"+BuildSelected+DiscSelected+string,Toast.LENGTH_SHORT).show();
                    ___B4__Regester(id, DiscSelected, string);
                }


            }
        });
        builder.setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void setspinner(ArrayList<HashMap<String, String>> arrayList) {


        hashMap = new HashMap<String, Object>();

        buildSpinner = (Spinner) findViewById(R.id.Build);

        discSpinner = (Spinner) findViewById(R.id.Disc);


        ArrayList<String> build = new ArrayList<String>();
        build.add("مباني السكن");
        build.add("سكن الاميرة بسمة");
        build.add("B4 سكن الاستوديوهات");


        HashMap<String, String> Bhashma = new HashMap<String, String>();

        Bhashma = arrayList.get(0);

        room_double = "" + (String) String.valueOf(Bhashma.get("Room_Double")).toString();
        Room_single = "" + (String) String.valueOf(Bhashma.get("Room_single")).toString();
        Room_special = "" + (String) String.valueOf(Bhashma.get("Room_Double")).toString();

        //  Toast.makeText(regesterpage.this,str,Toast.LENGTH_LONG).show();

        ArrayList<String> B_disc = new ArrayList<String>();
        B_disc.add(" غرفة مزدوجة " + room_double + " دينار ");
        B_disc.add(" غرفة مفردة " + Room_single + " دينار ");
        B_disc.add(" غرفة خاصة " + Room_special + " دينار ");


        HashMap<String, String> B4hashmapp = new HashMap<String, String>();

        B4hashmapp = arrayList.get(1);

        room_doubleB4 = "" + (String) String.valueOf(B4hashmapp.get("Room_Double")).toString();
        Room_TripleB4 = "" + (String) String.valueOf(B4hashmapp.get("Room_Triple")).toString();
        studio_DoubleB4 = "" + (String) String.valueOf(B4hashmapp.get("studio_Double")).toString();
        studio_SingleB4 = "" + (String) String.valueOf(B4hashmapp.get("studio_Single")).toString();


        final ArrayList<String> B4_disc = new ArrayList<String>();
        B4_disc.add(" استوديو مزدوج " + studio_DoubleB4 + " دينار ");
        B4_disc.add(" غرفة مزدوجة " + room_doubleB4 + " دينار ");
        B4_disc.add(" استوديو مفرد " + studio_SingleB4 + " دينار ");
        B4_disc.add(" غرفة ثلاثية " + Room_TripleB4 + " دينار ");

        HashMap<String, String> basmahhashmapp = new HashMap<String, String>();

        B4hashmapp = arrayList.get(2);

        Double_First = "" + (String) String.valueOf(B4hashmapp.get("Double_First")).toString();
        Double_Second = "" + (String) String.valueOf(B4hashmapp.get("Double_Second")).toString();
        Double_Second_Kitchen = "" + (String) String.valueOf(B4hashmapp.get("Double_Second_Kitchen")).toString();
        Single_First = "" + (String) String.valueOf(B4hashmapp.get("Single_First")).toString();
        Single_Second = "" + (String) String.valueOf(B4hashmapp.get("Single_Second")).toString();
        Single_Second_Kitchen = "" + (String) String.valueOf(B4hashmapp.get("Single_Second_Kitchen")).toString();


        ArrayList<String> Bbasmah_disc = new ArrayList<String>();
        Bbasmah_disc.add(" غرفة مزدوجة جناح اول " + Double_First + " دينار ");
        Bbasmah_disc.add(" غرفة مزدوجة جناح ثاني تحوي مطبخ " + Double_Second_Kitchen + " دينار ");
        Bbasmah_disc.add(" غرفة مزدوجة جناح ثاني " + Double_Second + " دينار ");
        Bbasmah_disc.add(" غرفة مفردة جناح اول " + Single_First + " دينار ");
        Bbasmah_disc.add(" غرفة مفردة جناح ثاني تحوي مطبخ " + Single_Second_Kitchen + " دينار ");
        Bbasmah_disc.add(" غرفة مفردة جناح ثاني " + Single_Second + " دينار ");


        //0 for Double-Firstwing
        //1 for Double-second wing-kitchen
        //2 for Double-second wing-withoutkitchen
        //3 for single firstwing
        //4for single second wing kitchen
        //5 for single second wing withuout kitchen


        ArrayAdapter<String> buildadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, build);
        buildSpinner.setAdapter(buildadapter);


        final ArrayAdapter<String> Badapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, B_disc);
        discSpinner.setAdapter(Badapter);

        final ArrayAdapter<String> B4adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, B4_disc);

        final ArrayAdapter<String> basmah = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Bbasmah_disc);


        buildSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BuildSelected = position;

                if (position == 0)
                    discSpinner.setAdapter(Badapter);
                else if (position == 1)
                    discSpinner.setAdapter(basmah);
                else
                    discSpinner.setAdapter(B4adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        discSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(supervisor.this,"+"+position,Toast.LENGTH_LONG).show();
                DiscSelected = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Reger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BuildSelected == 0) {
                    if (DiscSelected == 0) {
                        confirmDialogDemo(room_double);
                    } else if (DiscSelected == 1) {
                        confirmDialogDemo(Room_single);
                    } else {
                        confirmDialogDemo(Room_special);
                    }

                } else if (BuildSelected == 1) {


                    if (DiscSelected == 0) {
                        confirmDialogDemo(Double_First);
                    } else if (DiscSelected == 1) {
                        confirmDialogDemo(Double_Second_Kitchen);
                    } else if (DiscSelected == 2) {
                        confirmDialogDemo(Double_Second);
                    } else if (DiscSelected == 3) {
                        confirmDialogDemo(Single_First);
                    } else if (DiscSelected == 4) {
                        confirmDialogDemo(Single_Second_Kitchen);
                    } else {
                        confirmDialogDemo(Single_Second);
                    }

                } else {
                    //Toast.makeText(regesterpage.this,"b4"+BuildSelected+DiscSelected,Toast.LENGTH_SHORT).show();

                    if (DiscSelected == 0) {
                        confirmDialogDemo(studio_DoubleB4);
                    } else if (DiscSelected == 1) {
                        confirmDialogDemo(room_doubleB4);
                    } else if (DiscSelected == 2) {
                        confirmDialogDemo(studio_SingleB4);
                    } else {
                        confirmDialogDemo(Room_TripleB4);
                    }


                }


            }
        });
        dialog.dismiss();

    }


    public void ___Basmah__Regester(final String id, final int Disc, final String price) {
        //0 for Double-Firstwing
        //1 for Double-second wing-kitchen
        //2 for Double-second wing-withoutkitchen
        //3 for single firstwing
        //4for single second wing kitchen
        //5 for single second wing withuout kitchen
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Buildings").child("Basmah");


        myRef.addValueEventListener(new ValueEventListener() {

            int co = 0;
            int c = 0;
            int flag = -1;
            String Firstcheck = "0";
            String Secindcheck = "0";

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                flag = -1;
                c += 1;
                if (c == 1) {
                    if (Disc == 0) {
                        for (DataSnapshot innert : dataSnapshot.child("Double").child("First_wing").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);
                            Secindcheck = innert.child("Student_Id2").getValue(String.class);
                            if (Firstcheck.equals("0")) {
                                myRef.child("Double").child("First_wing").child(innert.getKey()).child("Student_Id").setValue(id);
                                flag = 1;
                                co = 1;
                                __Add__user(id, "Basmah", innert.getKey(), "Double_First_wing", "Student_Id", price);
                                break;
                            } else if (Secindcheck.equals("0")) {
                                myRef.child("Double").child("First_wing").child(innert.getKey()).child("Student_Id2").setValue(id);
                                flag = 1;
                                co = 1;
                                __Add__user(id, "Basmah", innert.getKey(), "Double_First_wing", "Student_Id2", price);
                                break;
                            }
                        }
                    } else if (Disc == 1) {

                        for (DataSnapshot innert : dataSnapshot.child("Double").child("Second_wing").child("Kitchen").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);
                            Secindcheck = innert.child("Student_Id2").getValue(String.class);
                            if (Firstcheck.equals("0")) {

                                myRef.child("Double").child("Second_wing").child("Kitchen").child(innert.getKey()).child("Student_Id").setValue(id);
                                flag = 1;
                                co = 1;
                                __Add__user(id, "Basmah", innert.getKey(), "Double_Second_wing_Kitchen", "Student_Id", price);

                                break;
                            } else if (Secindcheck.equals("0")) {

                                myRef.child("Double").child("Second_wing").child("Kitchen").child(innert.getKey()).child("Student_Id2").setValue(id);
                                co = 1;
                                flag = 1;
                                __Add__user(id, "Basmah", innert.getKey(), "Double_Second_wing_Kitchen", "Student_Id2", price);

                                break;
                            }

                        }
                    } else if (Disc == 2) {

                        for (DataSnapshot innert : dataSnapshot.child("Double").child("Second_wing").child("WithoutKitchen").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);
                            Secindcheck = innert.child("Student_Id2").getValue(String.class);
                            if (Firstcheck.equals("0")) {
                                myRef.child("Double").child("Second_wing").child("WithoutKitchen").child(innert.getKey()).child("Student_Id").setValue(id);
                                co = 1;
                                flag = 1;
                                __Add__user(id, "Basmah", innert.getKey(), "Double_Second_wing_WithoutKitchen", "Student_Id", price);

                                break;
                            } else if (Secindcheck.equals("0")) {
                                myRef.child("Double").child("Second_wing").child("WithoutKitchen").child(innert.getKey()).child("Student_Id2").setValue(id);
                                __Add__user(id, "Basmah", innert.getKey(), "Double_Second_wing_WithoutKitchen", "Student_Id2", price);
                                flag = 1;
                                co = 1;

                                break;
                            }

                        }

                    } else if (Disc == 3) {

                        for (DataSnapshot innert : dataSnapshot.child("Single").child("First_wing").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);

                            if (Firstcheck.equals("0")) {
                                myRef.child("Single").child("First_wing").child(innert.getKey()).child("Student_Id").setValue(id);

                                co = 1;
                                __Add__user(id, "Basmah", innert.getKey(), "Single_First_wing", "Student_Id", price);
                                flag = 1;
                                break;
                            }

                        }

                    } else if (Disc == 4) {
                        for (DataSnapshot innert : dataSnapshot.child("Single").child("Second_wing").child("Kitchen").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);

                            if (Firstcheck.equals("0")) {
                                myRef.child("Single").child("Second_wing").child("Kitchen").child(innert.getKey()).child("Student_Id").setValue(id);
                                __Add__user(id, "Basmah", innert.getKey(), "Single_Second_wing_Kitchen", "Student_Id", price);
                                co = 1;
                                flag = 1;

                                break;
                            }
                            //  WithoutKitchen
                        }

                    } else if (Disc == 5) {


                        for (DataSnapshot innert : dataSnapshot.child("Single").child("Second_wing").child("WithoutKitchen").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);

                            if (Firstcheck.equals("0")) {
                                myRef.child("Single").child("Second_wing").child("WithoutKitchen").child(innert.getKey()).child("Student_Id").setValue(id);
                                co = 1;
                                __Add__user(id, "Basmah", innert.getKey(), "Single_Second_wing_WithoutKitchen", "Student_Id", price);
                                flag = 1;
                                break;
                            }
                            //  WithoutKitchen
                        }

                    }//Disc==5

                    if (flag == -1)
                        Toast.makeText(regesterpage.this, "الغرفة ممتلئة ,يرجى اختيار غرفة من نوع اخر", Toast.LENGTH_SHORT).show();
                }
                if (c == 2)
                    return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//////*******************************************************

    //////*******************************************************

    //////*******************************************************

    //////*******************************************************
    public void ___B4__Regester(final String id, final int Disc, final String price) {
        //11324 123m

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Buildings").child("B4");
        myRef.addValueEventListener(new ValueEventListener() {


            int co = 0;
            int c = 0;
            int flag = -1;
            String Firstcheck = "0";
            String Secindcheck = "0";
            String third = "0";
            //0 for Double/with kitchen
            //1 for Double/without kitchen
            //2 for single
            //3 for Triple

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                flag = -1;
                c += 1;
                // Toast.makeText(regesterpage.this,"+"+dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                if (c == 1) {
                    if (Disc == 0) {
                        // Double/with kitchen
                        for (DataSnapshot innert : dataSnapshot.child("Double").child("Kitchen").getChildren()) {

                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);
                            Secindcheck = innert.child("Student_Id2").getValue(String.class);
                            if (Firstcheck.equals("0")) {
                                myRef.child("Double").child("Kitchen").child(innert.getKey()).child("Student_Id").setValue(id);
                                __Add__user(id, "B4", innert.getKey(), "DoublewithKitchen", "Student_Id", price);
                                co = 1;
                                flag = 1;
                            } else if (Secindcheck.equals("0")) {
                                //add value
                                myRef.child("Double").child("Kitchen").child(innert.getKey()).child("Student_Id2").setValue(id);
                                __Add__user(id, "B4", innert.getKey(), "DoublewithKitchen", "Student_Id2", price);
                                flag = 1;
                                co = 1;
                            }
                        }
                    } else if (Disc == 1) {
                        // Double/without kitchen
                        for (DataSnapshot innert : dataSnapshot.child("Double").child("WithoutKitchen").getChildren()) {

                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);
                            Secindcheck = innert.child("Student_Id2").getValue(String.class);
                            if (Firstcheck.equals("0")) {
                                myRef.child("Double").child("WithoutKitchen").child(innert.getKey()).child("Student_Id").setValue(id);
                                flag = 1;
                                __Add__user(id, "B4", innert.getKey(), "DoublewithoutKitchen", "Student_Id", price);
                                co = 1;
                            } else if (Secindcheck.equals("0")) {
                                //add value
                                myRef.child("Double").child("WithoutKitchen").child(innert.getKey()).child("Student_Id2").setValue(id);
                                flag = 1;
                                __Add__user(id, "B4", innert.getKey(), "DoublewithoutKitchen", "Student_Id2", price);
                                co = 1;
                            }
                        }
                    } else if (Disc == 2) {

                        for (DataSnapshot innert : dataSnapshot.child("Single").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);

                            if (Firstcheck.equals("0")) {
                                myRef.child("Single").child(innert.getKey()).child("Student_Id").setValue(id);
                                flag = 1;
                                __Add__user(id, "B4", innert.getKey(), "Single", "Student_Id", price);
                                co = 1;

                            }
                        }
                    } else if (Disc == 3) {
                        for (DataSnapshot innert : dataSnapshot.child("Treble").getChildren()) {
                            if (co == 1)
                                break;
                            Firstcheck = innert.child("Student_Id").getValue(String.class);
                            Secindcheck = innert.child("Student_Id2").getValue(String.class);
                            third = innert.child("Student_Id3").getValue(String.class);

                            if (Firstcheck.equals("0")) {
                                myRef.child("Treble").child(innert.getKey()).child("Student_Id").setValue(id);
                                flag = 1;
                                __Add__user(id, "B4", innert.getKey(), "Treble", "Student_Id", price);
                                co = 1;
                            } else if (Secindcheck.equals("0")) {
                                myRef.child("Treble").child(innert.getKey()).child("Student_Id2").setValue(id);
                                flag = 1;
                                __Add__user(id, "B4", innert.getKey(), "Treble", "Student_Id2", price);
                                co = 1;

                            } else if (third.equals("0")) {
                                myRef.child("Treble").child(innert.getKey()).child("Student_Id3").setValue(id);
                                flag = 1;
                                __Add__user(id, "B4", innert.getKey(), "Treble", "Student_Id3", price);
                                co = 1;
                            }
                        }
                    }
                    if (flag == -1)
                        Toast.makeText(regesterpage.this, "الغرفة ممتلئة ,يرجى اختيار غرفة من نوع اخر", Toast.LENGTH_SHORT).show();
                }
                if (c == 2)
                    return;
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void ___B__Regester(final String id, final int Disc, final String price) {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Buildings").child("B");
        myRef.addValueEventListener(new ValueEventListener() {


            int co = 0;
            int c = 0;
            int flag = -1;


            String nameofBuilding = "";


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                flag = -1;
                c += 1;
                String Firstcheck = "0";
                String Secindcheck = "0";

                if (c == 1) {
                    for (DataSnapshot t : dataSnapshot.getChildren()) {
                        if (co == 1)
                            break;
                        nameofBuilding = t.getKey();
                        if (Disc == 0) {
                            for (DataSnapshot innert : t.child("Double").getChildren()) {
                                if (co == 1)
                                    break;
                                Firstcheck = innert.child("Student_Id").getValue(String.class);
                                Secindcheck = innert.child("Student_Id2").getValue(String.class);

                                if (Firstcheck.equals("0")) {
                                    myRef.child(t.getKey()).child("Double").child(innert.getKey()).child("Student_Id").setValue(id);
                                    flag = 1;
                                    __Add__user(id, t.getKey(), innert.getKey(), "Double", "Student_Id", price);
                                    co = 1;
                                    break;
                                } else if (Secindcheck.equals("0")) {
                                    //add value
                                    myRef.child(t.getKey()).child("Double").child(innert.getKey()).child("Student_Id2").setValue(id);
                                    co = 1;
                                    flag = 1;
                                    __Add__user(id, t.getKey(), innert.getKey(), "Double", "Student_Id2", price);
                                    break;
                                }
                            }//end of inner loop
                        } else if (Disc == 1) {
                            for (DataSnapshot innert : t.child("Single").getChildren()) {
                                if (co == 1)
                                    break;
                                Firstcheck = innert.child("Student_Id").getValue(String.class);
                                if (Firstcheck.equals("0")) {
                                    //Toast.makeText(regesterpage.this, "+" + "hassan", Toast.LENGTH_SHORT).show();
                                    myRef.child(t.getKey()).child("Single").child(innert.getKey()).child("Student_Id").setValue(id);
                                    __Add__user(id, t.getKey(), innert.getKey(), "Single", "Student_Id", price);
                                    co = 1;
                                    flag = 1;
                                    break;
                                }
                            }//end of inner loop
                        } else if (Disc == 2) {
                            //special
                            for (DataSnapshot innert : t.child("Special").getChildren()) {
                                if (co == 1)
                                    break;
                                Firstcheck = innert.child("Student_Id").getValue(String.class);
                                if (Firstcheck.equals("0")) {
                                    myRef.child(t.getKey()).child("Special").child(innert.getKey()).child("Student_Id").setValue(id);
                                    __Add__user(id, t.getKey(), innert.getKey(), "Special", "Student_Id", price);
                                    flag = 1;
                                    co = 1;
                                }
                            }//end of inner loop
                        }
                    }//end of first loop
                    if (flag == -1)
                        Toast.makeText(regesterpage.this, "الغرفة ممتلئة ,يرجى اختيار غرفة من نوع اخر", Toast.LENGTH_SHORT).show();
                }
                if (c == 2)
                    return;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
