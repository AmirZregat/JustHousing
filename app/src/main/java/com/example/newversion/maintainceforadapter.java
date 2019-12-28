package com.example.newversion;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class maintainceforadapter extends BaseAdapter {

    LayoutInflater inflater;
    int __Locali;
    ArrayList<maintaincce> item = new ArrayList<maintaincce>();
    FirebaseDatabase database;
    DatabaseReference myRef;

    maintainceforadapter(LayoutInflater layoutInflater, ArrayList<maintaincce> list,int i){
        inflater = layoutInflater;
        item = list;
        __Locali=i;
        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("Maintenance_Requests");

    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public String getItem(int position) {
        return item.get(position).text;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View  view = inflater.inflate(R.layout.approvedrequet, null);
        final Button okay = (Button) view.findViewById(R.id.okaybtn);

        final TextView id = (TextView) view.findViewById(R.id.idofstudentwhomade);
        final TextView Room_num = (TextView) view.findViewById(R.id.nameofSuperviso);
        final TextView maintainc = (TextView) view.findViewById(R.id.complainttext);

        id.setText("رقم الطالبة  :"+item.get(position).Student_Id);
        Room_num.setText("رقم الغرفة :"+item.get(position).Room_num);
        maintainc.setText("الخلل : \n"+item.get(position).text);


        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(item.get(position).Student_Id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            okay.setEnabled(false);

                        }
                    }
                });

            }
        });


        return view;
    }
}
