package com.nataliasep.mireloj_clase.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nataliasep.mireloj_clase.R;

public class Pantalla2 extends AppCompatActivity {

    EditText miedad;
    Button bValidar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        miedad = findViewById(R.id.etEdad);
        bValidar = findViewById(R.id.bValidarEdad);



        bValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int laedad = Integer.parseInt(miedad.getText().toString());
                if(laedad >= 18){
                    Toast.makeText(Pantalla2.this, "Eres mayor de edad", Toast.LENGTH_SHORT).show();
                    finishAffinity();

                }else{
                    Toast.makeText(Pantalla2.this, "No eres mayor de edad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}