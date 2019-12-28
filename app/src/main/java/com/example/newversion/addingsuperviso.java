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

import java.util.HashMap;

public class addingsuperviso extends AppCompatActivity {
    EditText Name,Password,Confirmpassword,Phone,idd,Salary;
    Button add;
    HashMap<String,String> stringStringHashMap;



    String namestr,passwordstr,confirmpassstr,phonestr,iddstr,salarystr="";

    FirebaseDatabase database ;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingsuperviso);

        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("Supervisor");

        stringStringHashMap = new HashMap<String,String>();


        Name = (EditText)findViewById(R.id.namefornew);
        Password = (EditText)findViewById(R.id.passwordfornew);
        Confirmpassword = (EditText)findViewById(R.id.confirmpass);
        Phone = (EditText)findViewById(R.id.phoneforenew);
        idd = (EditText)findViewById(R.id.IdforNew);
        Salary = (EditText)findViewById(R.id.salaryfornew);
        add = (Button) findViewById(R.id.addbtnfornew);






        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                namestr=Name.getText().toString();
                passwordstr=Password.getText().toString();
                confirmpassstr=Confirmpassword.getText().toString();
                phonestr=Phone.getText().toString();
                iddstr=idd.getText().toString();
                salarystr=Salary.getText().toString();


                if (TextUtils.isEmpty(namestr)) {
                    Toast.makeText(addingsuperviso.this, "يرجى تعبئة جميع الحقول ", Toast.LENGTH_SHORT).show();


                    return;
                } else if (TextUtils.isEmpty(passwordstr)) {


                    Toast.makeText(addingsuperviso.this, "يرجى تعبئة جميع الحقول ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(confirmpassstr)) {
                    Toast.makeText(addingsuperviso.this, "يرجى تعبئة جميع الحقول ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(phonestr)) {
                    Toast.makeText(addingsuperviso.this, "يرجى تعبئة جميع الحقول ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(iddstr)) {
                    Toast.makeText(addingsuperviso.this, "يرجى تعبئة جميع الحقول ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(salarystr)) {

                    Toast.makeText(addingsuperviso.this, "يرجى تعبئة جميع الحقول ", Toast.LENGTH_SHORT).show();
                    return;
                } else{

                    if (!passwordstr.equals(confirmpassstr))
                    {
                        Toast.makeText(addingsuperviso.this, "الرقم السري غير متطابق ", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        stringStringHashMap.put("Name",namestr);
                        stringStringHashMap.put("Salary",salarystr);
                        stringStringHashMap.put("Password",passwordstr);
                        stringStringHashMap.put("Phone",phonestr);
                        myRef.child(iddstr).setValue(stringStringHashMap);

                        Toast.makeText(addingsuperviso.this, "تم اضافة المشرفة", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });









    }
}
