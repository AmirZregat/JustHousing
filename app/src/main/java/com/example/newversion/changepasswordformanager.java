package com.example.newversion;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class changepasswordformanager extends AppCompatActivity {

    FirebaseDatabase __databaseAdd;
    DatabaseReference myre2;
    ProgressDialog dialog;
    Button submit;

    EditText Password,confirmpassword;
    EditText Idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepasswordformanager);

        __databaseAdd = FirebaseDatabase.getInstance();

        myre2 = __databaseAdd.getReference("Manager");

        dialog = new ProgressDialog(this);

        Idd = (EditText) findViewById(R.id.managernumber);
        Password = (EditText) findViewById(R.id.managerpasswordnew);
        confirmpassword=(EditText)findViewById(R.id.confirmnewpasswordformanager);
        submit = (Button) findViewById(R.id.submitformanager);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              final String  Id = Idd.getText().toString().trim();
              final String  password = Password.getText().toString().trim();
              String confer= confirmpassword.getText().toString().trim();
                if (TextUtils.isEmpty(Id)) {
                    Toast.makeText(changepasswordformanager.this, "ادخل الرقم", Toast.LENGTH_SHORT).show();


                    return;
                } else if (TextUtils.isEmpty(password)) {


                    Toast.makeText(changepasswordformanager.this, "ادخل الرقم السري ", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(confer)) {


                    Toast.makeText(changepasswordformanager.this, "ادخل تاكيد الرقم السري", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    Long.parseLong(Id);


                    dialog.setMessage("جاري تغيير كلمة السر ...");
                    dialog.setIndeterminate(true);
                    dialog.setCancelable(false);
                    dialog.show();
                    myre2.child(Id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                myre2.child(Id).child("Password").setValue(password);
                                dialog.dismiss();

                                Toast.makeText(changepasswordformanager.this, "تم تغيير الرقم السري", Toast.LENGTH_SHORT).show();
                            }else{
                                dialog.dismiss();
                                Toast.makeText(changepasswordformanager.this, "رقم المستخدم غير موجود ويرجى التاكد", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }catch (Exception e)
                {
                    dialog.dismiss();
                    Toast.makeText(changepasswordformanager.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}
