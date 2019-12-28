package com.example.newversion;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class changeroomadapter extends BaseAdapter {

    LayoutInflater inflater;
    int __Locali;
    ArrayList<classchangeroom> item = new ArrayList<classchangeroom>();
    FirebaseDatabase database;
    DatabaseReference myRef,firstref,buildref,systemstudentref,systemref;

    HashMap<String,Object> firsthashmap,secondhashmap;
    changeroomadapter(LayoutInflater layoutInflater, ArrayList<classchangeroom> list){
        inflater = layoutInflater;
        item = list;

        firsthashmap = new HashMap<>();
        secondhashmap = new HashMap<>();
        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("System").child("Roomchangerequests");
        firstref =database.getReference("System_students");
        buildref =database.getReference("Buildings");
        systemstudentref=database.getReference("System_students");
        systemref=database.getReference("System").child("Roomchangerequests");
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View  view = inflater.inflate(R.layout.changeroomlayout, null);


        final TextView name = (TextView) view.findViewById(R.id.firststudentincustom);
        final TextView id = (TextView) view.findViewById(R.id.seondstudentincustom);

        name.setText("رقم الطالبة الاولى :"+item.get(position).getFirststudentid());
        id.setText("رقم الطالبة الثانية :"+item.get(position).getSecondStudentId());

        final Button approve = (Button) view.findViewById(R.id.acceptchangeroom);
        approve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                firstref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        firsthashmap= (HashMap<String, Object>) dataSnapshot.child(item.get(position).getFirststudentid()).getValue();
                        secondhashmap= (HashMap<String, Object>) dataSnapshot.child(item.get(position).getSecondStudentId()).getValue();
                        String Building = (String) firsthashmap.get("Building");
                        String Room_Type = (String) firsthashmap.get("Room_Type");

                        String secondRoom_Type = (String) secondhashmap.get("Room_Type");


                        String firstRoom_num = (String) firsthashmap.get("Room_num");

                        String secondRoom_num = (String) secondhashmap.get("Room_num");


                        String firstStudentidinHouse = (String) firsthashmap.get("StudentidinHouse");

                        String secondStudentidinHouse = (String) secondhashmap.get("StudentidinHouse");
                        if (Building.equals("B4")){
                            if (Room_Type.equals("DoublewithKitchen")){
                                buildref.child("B4").child("Double").child("Kitchen").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (Room_Type.equals("DoublewithoutKitchen")){

                                buildref.child("B4").child("Double").child("WithoutKitchen").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());

                            }else if (Room_Type.equals("Single")){
                                buildref.child("B4").child("Single").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (Room_Type.equals("Treble")){
                                buildref.child("B4").child("Treble").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }
                            //************* ADD the seond student
                            if (secondRoom_Type.equals("DoublewithKitchen")){
                                buildref.child("B4").child("Double").child("Kitchen").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getFirststudentid());
                            }else if (secondRoom_Type.equals("DoublewithoutKitchen")){

                                buildref.child("B4").child("Double").child("WithoutKitchen").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getFirststudentid());

                            }else if (secondRoom_Type.equals("Single")){
                                buildref.child("B4").child("Single").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getFirststudentid());
                            }else if (secondRoom_Type.equals("Treble")){
                                buildref.child("B4").child("Treble").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getFirststudentid());
                            }

//########################################################3

                        }else if (Building.equals("Basmah")){
                            if (Room_Type.equals("Double_First_wing")){
                                buildref.child("Basmah").child("Double").child("First_wing").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (Room_Type.equals("Double_Second_wing_Kitchen")){

                                buildref.child("Basmah").child("Double").child("Second_wing").child("Kitchen").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());

                            }else if (Room_Type.equals("Double_Second_wing_WithoutKitchen")){
                                buildref.child("Basmah").child("Double").child("Second_wing").child("WithoutKitchen").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (Room_Type.equals("Single_First_wing")){
                                buildref.child("Basmah").child("Single").child("First_wing").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (Room_Type.equals("Single_Second_wing_Kitchen")){
                                buildref.child("Basmah").child("Single").child("Second_wing").child("Kitchen").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (Room_Type.equals("Single_First_wing")){
                                buildref.child("Basmah").child("Single").child("Second_wing").child("WithoutKitchen").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }

                            //************* ADD the seond student
                            if (secondRoom_Type.equals("Double_First_wing")){
                                buildref.child("Basmah").child("Double").child("First_wing").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (secondRoom_Type.equals("Double_Second_wing_Kitchen")){

                                buildref.child("Basmah").child("Double").child("Second_wing").child("Kitchen").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getSecondStudentId());

                            }else if (secondRoom_Type.equals("Double_Second_wing_WithoutKitchen")){
                                buildref.child("Basmah").child("Double").child("Second_wing").child("WithoutKitchen").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (secondRoom_Type.equals("Single_First_wing")){
                                buildref.child("Basmah").child("Single").child("First_wing").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (secondRoom_Type.equals("Single_Second_wing_Kitchen")){
                                buildref.child("Basmah").child("Single").child("Second_wing").child("Kitchen").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }else if (secondRoom_Type.equals("Single_First_wing")){
                                buildref.child("Basmah").child("Single").child("Second_wing").child("WithoutKitchen").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getSecondStudentId());
                            }

                        }else{

                            if (Room_Type.equals("Double")){

                                buildref.child("B").child(Building).child("Double").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());

                            }else if (Room_Type.equals("Single")){

                                buildref.child("B").child(Building).child("Single").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());

                            }else if (Room_Type.equals("Special")){

                                buildref.child("B").child(Building).child("Special").child(firstRoom_num)
                                        .child(firstStudentidinHouse).setValue(item.get(position).getSecondStudentId());

                            }

                            //*****************
                            if (secondRoom_Type.equals("Double")){

                                buildref.child("B").child(Building).child("Double").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getFirststudentid());

                            }else if (secondRoom_Type.equals("Single")){

                                buildref.child("B").child(Building).child("Single").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getFirststudentid());

                            }else if (secondRoom_Type.equals("Special")){

                                buildref.child("B").child(Building).child("Special").child(secondRoom_num)
                                        .child(secondStudentidinHouse).setValue(item.get(position).getFirststudentid());

                            }
                        }


                        systemstudentref.child(item.get(position).getFirststudentid()).child("Room_Type").setValue(secondRoom_Type);
                        systemstudentref.child(item.get(position).getSecondStudentId()).child("Room_Type").setValue(firstRoom_num);


                        systemstudentref.child(item.get(position).getFirststudentid()).child("Room_num").setValue(firstRoom_num);
                        systemstudentref.child(item.get(position).getSecondStudentId()).child("Room_num").setValue(secondRoom_num);




                        systemref.child(item.get(position).getFirststudentid()).child("mangerstatus").setValue("Yes");
                        Toast.makeText(view.getContext(),"تم تغيير الغرفة ",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });

        final Button delete = (Button) view.findViewById(R.id.ignorechangeroom);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                systemref.child(item.get(position).getFirststudentid()).child("mangerstatus").setValue("No");
                Toast.makeText(view.getContext(),"تم رفض الطلب ",Toast.LENGTH_LONG).show();

            }
        });


        return view;
    }
}
