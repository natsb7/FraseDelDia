package com.nataliasep.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName(); //otra manera de obtener la clase parar referenciarla en los métodos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); //esto es lo que une el código con la interfaz


        Log.d(getClass().getSimpleName(), "OnCreate");
        //obtener todas las vistas que sean necesarias para hacer funcionar la app
        EditText TNombre = findViewById(R.id.TNombre);
        EditText TApellido = findViewById(R.id.TApellido);

        Button bSaludo = findViewById(R.id.BSaludo);
        bSaludo.setText("Saludar");
        bSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = " " + TNombre.getText().toString() + " " + TApellido.getText().toString();
                Toast.makeText(MainActivity.this, "Hola" + texto, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(getClass().getSimpleName(), "OnRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getClass().getSimpleName(), "OnPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getSimpleName(), "OnDestroy");
    }
}