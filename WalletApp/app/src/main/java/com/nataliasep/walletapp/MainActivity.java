package com.nataliasep.walletapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask inicio = new TimerTask() {

            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ActividadCredito.class);
                startActivity(intent);
                finish();

            }
        };
        Timer temps = new Timer();
        temps.schedule(inicio,5000);


    }
}