package com.example.newversion;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class req_maintenance extends AppCompatActivity {
    Button req_m;
    EditText textOfM;
    DatabaseReference mainteRef;
    SharedPreferences p;
    String language;

    String id = "0";
    SharedPreferences.Editor editor;
    final String MYREFERNCES = "usernameANDpassword";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_maintenance);

        p = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        language = p.getString("my_lang","");



        editor = getSharedPreferences(MYREFERNCES, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MYREFERNCES, MODE_PRIVATE);
        id = prefs.getString("Id", null);


        req_m = findViewById(R.id.btn_req_mainte);
        textOfM = findViewById(R.id.textOfMaintenance);
        mainteRef = FirebaseDatabase.getInstance().getReference("Maintenance_Requests");
        req_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        String s = id;
        mainteRef.child(s).child("Room_num").setValue("023");
        mainteRef.child(s).child("Student_Id").setValue(s);
        mainteRef.child(s).child("Building").setValue("B1");
        mainteRef.child(s).child("text").setValue(textOfM.getText().toString());
        if(language.equals("ar"))
        Toast.makeText(getApplicationContext(), "تم طلب الصيانة..سيتم حل المشكلة بأقرب وقت", Toast.LENGTH_LONG).show();
        else if(language.equals("en"))
            Toast.makeText(getApplicationContext(), "Maintenance has been requested!", Toast.LENGTH_LONG).show();
        textOfM.setText("");

    }
}
