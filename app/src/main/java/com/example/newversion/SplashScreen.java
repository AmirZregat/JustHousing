package com.example.newversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000); //how long do u want the splash screen to stay
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    finish();//to close the splash screen
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

    }
}

