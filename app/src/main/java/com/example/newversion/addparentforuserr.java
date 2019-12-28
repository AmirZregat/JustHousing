package com.example.newversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addparentforuserr extends AppCompatActivity {


    EditText studentID,fatherID;
    EditText Password2;
    Button add;
    String Password;
    String SId,FId;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparentforuserr);

        Password2 = (EditText) findViewById(R.id.parentPassword);
        studentID=(EditText)findViewById(R.id.StudentIDD);
        fatherID=(EditText)findViewById(R.id.parentID);
        add=(Button)findViewById(R.id.addusertodatabase);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int test=0;
                SId = studentID.getText().toString().trim();
                FId = fatherID.getText().toString().trim();
                Password = Password2.getText().toString().trim();
                if (TextUtils.isEmpty(FId)) {
                    Toast.makeText(addparentforuserr.this, "Enter  Father ID", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if (TextUtils.isEmpty(Password)) {

                    Toast.makeText(addparentforuserr.this, "Enter  password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(SId)) {

                    Toast.makeText(addparentforuserr.this, "Enter  Student ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Long.parseLong(SId);
                    Long.parseLong(FId);

                    __AddFather(SId,FId,Password);


                }catch (Exception e)
                {

                    Toast.makeText(addparentforuserr.this, "Enter Only Number" +
                            "", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    void __AddFather(String Stid,String Faid,String faPas){
        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("Student_Father").child(Faid);
        myRef.child("Password").setValue(faPas);
        myRef.child("Student_Id").setValue(Stid);

        Toast.makeText(addparentforuserr.this, "Succefully Added" +
                "", Toast.LENGTH_SHORT).show();

    }


}
