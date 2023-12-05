package com.nataliasep.mireloj_clase.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.nataliasep.mireloj_clase.R;

public class MainActivity extends AppCompatActivity {
    EditText minombre;
    Button btvalidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minombre = findViewById(R.id.ptNombre);
        btvalidar = findViewById(R.id.bValidar);

        btvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNombre = String.valueOf(minombre.getText());
                if(textNombre.equals("Natalia")){
                    Toast.makeText(MainActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();
                    Intent intento = new Intent(MainActivity.this, Pantalla2.class);
                    startActivity(intento);
                }else{
                    Toast.makeText(MainActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}