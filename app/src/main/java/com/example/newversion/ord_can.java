package com.example.newversion;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ord_can extends BaseAdapter {

    LayoutInflater inflater;
int __Locali;
  ArrayList<complaintinfooClass> item = new ArrayList<complaintinfooClass>();
    FirebaseDatabase database;
    DatabaseReference myRef;

   ord_can(LayoutInflater layoutInflater, ArrayList<complaintinfooClass> list,int i){
        inflater = layoutInflater;
        item = list;
__Locali=i;
       database = FirebaseDatabase.getInstance();
        if(i==1){
            myRef =database.getReference("Complaints").child("OnSupervisor");
        }else if (i==2){
            myRef =database.getReference("Complaints").child("OnStudent");
        }

    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public String getItem(int position) {
        return item.get(position).Name;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        final View  view = inflater.inflate(R.layout.approvedrequet, null);
        final Button approve = (Button) view.findViewById(R.id.okaybtn);

        final TextView id = (TextView) view.findViewById(R.id.idofstudentwhomade);
        final TextView nameofsupervisor = (TextView) view.findViewById(R.id.nameofSuperviso);
        final TextView complatxt = (TextView) view.findViewById(R.id.complainttext);

        if (__Locali==1){
            id.setText("رقم الطالبة المشتكية :"+item.get(position).IdOfpersonWhoMadeComplaint);
            nameofsupervisor.setText("المشرفة المشتكى عليها :"+item.get(position).Name);
            complatxt.setText("الشكوى \n"+item.get(position).ComplaintText);
        }else if(__Locali==2){
            id.setText("رقم الطالبة المشتكية :"+item.get(position).IdOfpersonWhoMadeComplaint);
            nameofsupervisor.setText("الطالبةالمشتكى عليها :"+item.get(position).Name);
            complatxt.setText("الشكوى \n"+item.get(position).ComplaintText);
        }




        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              myRef.child(item.get(position).IdOfpersonWhoMadeComplaint).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()){
                           approve.setEnabled(false);

                       }
                   }
               });

            }
        });


        return view;


    }

}
