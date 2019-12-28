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

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class sleepadapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<sleep> item = new ArrayList<sleep>();
    FirebaseDatabase database;
    DatabaseReference myRef;

    private int mYear, mMonth, mDay, mHour, mMinute;

    sleepadapter(LayoutInflater layoutInflater, ArrayList<sleep> list, int i) {
        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("3.00"));
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        inflater = layoutInflater;
        item = list;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Sleep_outside_requests");
    }


    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position).Student_Id;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.aprovedsleeprequest, null);
        final TextView b_id = view.findViewById(R.id.bid);
        final TextView b_date = view.findViewById(R.id.bdate);
        final TextView g_date = view.findViewById(R.id.gdate);
        final TextView p_name = view.findViewById(R.id.pname);
        final TextView p_phone = view.findViewById(R.id.pphone);

        b_id.setText("الرقم الجامعي :" + item.get(position).Student_Id);
        b_date.setText("وقت الذهاب :" + item.get(position).Back_date);
        g_date.setText("وقت العوده : " + item.get(position).Going_date);
        p_name.setText(" اسم الشخص : " + item.get(position).PersonName);
        p_phone.setText("رقم الهاتف : " + item.get(position).Person_phone_num);


        return view;
    }
}
