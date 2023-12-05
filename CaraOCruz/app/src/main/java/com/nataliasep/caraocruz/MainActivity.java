package com.nataliasep.caraocruz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bCara = findViewById(R.id.BCara);
        Button bCruz = findViewById(R.id.BCruz);
        Button bRestart = findViewById(R.id.BRestart);
        ImageView IMoneda = findViewById(R.id.IMoneda);
        TextView TResult = findViewById(R.id.TResult);


        IMoneda.setImageResource(R.drawable.INTERROGACION);

        bCara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {







            }
        });

        bCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        bRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}