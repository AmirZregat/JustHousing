package com.example.newversion;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class adsadapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<ads> item = new ArrayList<ads>();
    FirebaseDatabase database;
    DatabaseReference myRef;

    adsadapter(LayoutInflater layoutInflater, ArrayList<ads> list, int i) {
        inflater = layoutInflater;
        item = list;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("System").child("Adds");
    }


    @Override
    public int getCount() { return  item.size(); }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final View view = inflater.inflate(R.layout.ads_ads, null);
        final TextView ads_n = view.findViewById(R.id.ads_num);
        final TextView ads_t = view.findViewById(R.id.ads_text);

        ads_n.setText("إعلان ." + item.get(position).ads_num);
        ads_t.setText(""+item.get(position).ads_text);


        return view;
    }
}
