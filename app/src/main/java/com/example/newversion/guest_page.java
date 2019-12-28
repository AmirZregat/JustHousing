package com.example.newversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class guest_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page);
    }

    public void roomprices(View view) {
        Intent intent = new Intent(getApplicationContext(), ReadONLY_Room_Price.class);
        startActivity(intent);
    }


    public void btnregulartions(View view) {
        Intent intent = new Intent(getApplicationContext(), regulationsAndInstructions.class);
        startActivity(intent);

    }
}
