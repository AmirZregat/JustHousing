package com.example.newversion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class absentadabter extends BaseAdapter {

    LayoutInflater inflater;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseDatabase databaseabsent;
    DatabaseReference myRefabsent;
    HashMap<String,Object>stringObjectHashMap;
    ArrayList<AbsentsClass> item = new ArrayList<AbsentsClass>();
    String addstr="";
    String str="";
    int flag=0;

    absentadabter(LayoutInflater layoutInflater, ArrayList<AbsentsClass> list){
        inflater = layoutInflater;
        item = list;
        stringObjectHashMap=new HashMap<>();
flag=0;
        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("System_students");
        databaseabsent = FirebaseDatabase.getInstance();
        myRefabsent =database.getReference("System_students");

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
    public View getView(int position, View convertView, ViewGroup parent) {
final int local_pos=position;
        final View  view = inflater.inflate(R.layout.custom_absent_list, null);
        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
      final String  mYear = String.valueOf(c.get(Calendar.YEAR));
       final String mMonth = String.valueOf(c.get(Calendar.MONTH)+1);
      final int mDay = c.get(Calendar.DAY_OF_MONTH);

       stringObjectHashMap=item.get(position).getStringObjectHashMap();

        final TextView name = (TextView) view.findViewById(R.id.AbsentNameTextView);
        final TextView id = (TextView) view.findViewById(R.id.AbsentIdTextView);
        final TextView number = (TextView) view.findViewById(R.id.AbsentNumberTextView);
        final TextView show = (TextView) view.findViewById(R.id.showabsents);

        myRefabsent =database.getReference("System_students").child(item.get(position).getId()).child("Absents");
        myRefabsent.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    number.setText("عدد غيابات الطالبة :"+dataSnapshot.getChildrenCount());
                    flag=1;
                }else{
                    number.setText("عدد غيابات الطالبة :"+0);
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;


                str="";
                myRef.child(item.get(local_pos).getId()).child("Absents").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        flag+=1;
                        if (flag==1){
                            if (dataSnapshot.exists()){
                                for (DataSnapshot t:dataSnapshot.getChildren()) {
                                    str+=t.getKey();
                                    str+="\n";

                                }
                                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                                alertDialog.setTitle(item.get(local_pos).getName());
                                alertDialog.setMessage(str);
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();


                            }else{
                                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                                alertDialog.setTitle(item.get(local_pos).getName());
                                alertDialog.setMessage("لا يوجد غيابات");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                        }else{
                           return;
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });





        name.setText("اسم الطالبة :"+item.get(position).getName());
        id.setText("رقم الطالبة :"+item.get(position).getId());
        number.setText("عدد غيابات الطالبة :"+item.get(position).getnumber());

        final Button approve = (Button) view.findViewById(R.id.AbsentConfirmbtn);
        approve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


              myRef.child(item.get(local_pos).getId()).child("Absents").child(mYear+"_"+mMonth+"_"+mDay).setValue("1");
                    Toast.makeText(view.getContext(),"تم اضافة الغياب",Toast.LENGTH_LONG).show();


            }
        });

        final Button delete = (Button) view.findViewById(R.id.Absentremovebtnbtn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               myRef.child(item.get(local_pos).getId()).child("Absents").child(mYear+"_"+mMonth+"_"+mDay).removeValue();

                       Toast.makeText(view.getContext(),"تم مسح الغياب ",Toast.LENGTH_LONG).show();


            }
        });


        return view;


    }
}
