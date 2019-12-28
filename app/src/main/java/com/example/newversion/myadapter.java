package com.example.newversion;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class myadapter extends BaseAdapter  {
         LayoutInflater inflater;
        FirebaseDatabase database;
        DatabaseReference myRef,getemail;
        int __LOCAL_i;

        Session s = null ;

View v;
    String r , ss , m ;
String emailstring;


    ArrayList<studentinfoo> item = new ArrayList<studentinfoo>();


        myadapter(LayoutInflater layoutInflater, ArrayList<studentinfoo> list,int i){
        inflater = layoutInflater;
        //list =new ArrayList<studentinfoo>();

            __LOCAL_i=i;
        item = list;

emailstring
        ="";

                database = FirebaseDatabase.getInstance();
                myRef =database.getReference("ResidenceRequests");
            getemail =database.getReference("System_students");


        }
@Override
public int getCount() {
        return item.size();
        }

@Override
public String getItem(int position) {
        return item.get(position).Student_Num;
        }

@Override
public long getItemId(int position) {
        return position;
        }

@Override
public View getView(final int position, View convertView, ViewGroup parent) {



   final View  view = inflater.inflate(R.layout.hassancustom, null);
v=view;

        final TextView name = (TextView) view.findViewById(R.id.nameinhassan);
        final TextView status = (TextView) view.findViewById(R.id.statusinhassan);
        final TextView id = (TextView) view.findViewById(R.id.idinhassan);
        final TextView email = (TextView) view.findViewById(R.id.emailinhassan);
        final TextView Bplace = (TextView) view.findViewById(R.id.birthdayplace);
        final TextView Penalties = (TextView) view.findViewById(R.id.penalty);
        final TextView nationality = (TextView) view.findViewById(R.id.nationality);
        final TextView dateofaddmisio = (TextView) view.findViewById(R.id.dateofadmmision);


        final Button approve = (Button) view.findViewById(R.id.approve);
        final Button decli = (Button) view.findViewById(R.id.ignore);

        name.setText(item.get(position).Name);
        id.setText(item.get(position).Student_Num);
        status.setText(item.get(position).Status);
        email.setText(item.get(position).thisemail);
        Bplace.setText(item.get(position).thisbirthdayplace);
        Penalties.setText(item.get(position).thispenality);
        nationality.setText(item.get(position).thisnationality);
        dateofaddmisio.setText(item.get(position).thisdateofadmission);


        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), name.getText(), Toast.LENGTH_LONG).show();


                myRef.child(item.get(position).getParent()).child("Status").setValue("Yes");




            emailstring=item.get(position).getThisemail();

                m = "عزيزتي الطالبة: \n"+"نود أن نعلمك أنه قد تم قبولك في سكن الطالبات. \n"+"يرجى إكمال الإجراءات. \n"+"شكرا لكم. \n";
                Properties props = new Properties();
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port","465");
                props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.port","465");

                s = Session.getDefaultInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("studenthousing46@gmail.com","std321123");
                    }
                });
                Toast.makeText(view.getContext(),"sending email",Toast.LENGTH_LONG).show();
                retrive t = new retrive();
                t.execute();


                approve.setEnabled(false);

                decli.setEnabled(false);


            }
        });

        decli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child(item.get(position).getParent()).child("Status").setValue("No");
                emailstring=item.get(position).getThisemail();

                m = "عزيزتي الطالبة: \n"+"نود أن نعلمك أنه قد تم رفضك في سكن الطالبات, يرجى زيارة الادارة لمعرفة السبب\n"+" \n";
                Properties props = new Properties();
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port","465");
                props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.port","465");

                s = Session.getDefaultInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("studenthousing46@gmail.com","std321123");
                    }
                });
                Toast.makeText(view.getContext(),"sending email",Toast.LENGTH_LONG).show();
                retrive t = new retrive();
                t.execute();
                approve.setEnabled(false);

                decli.setEnabled(false);

            }
        });

        return view;


    }

    class  retrive extends AsyncTask<String , Void , String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                Message message = new MimeMessage(s);
                message.setFrom(new InternetAddress("studenthousing46@gmail.com"));
                message.setSubject("اشعار قبول");
                message.setRecipient(Message.RecipientType.TO ,new InternetAddress(emailstring));
                message.setContent("عزيزتي الطالبة: \n"+"نود أن نعلمك أنه قد تم قبولك في سكن الطالبات. \n"+"يرجى إكمال الإجراءات. \n"+"شكرا لكم. \n" ,"text/html; charset=utf-8");

                Transport.send(message);

            }catch (MessagingException e)
            {
                e.printStackTrace();;
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String aVoid)
        {
            Toast.makeText(v.getContext(),"تم ارسال رسالة الى المستخدم",Toast.LENGTH_LONG).show();
        }
    }


}