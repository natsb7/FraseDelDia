package com.nataliasep.pruebaintents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            textView.setText(String.valueOf(bundle.getInt("dato")));
        }
    }
}