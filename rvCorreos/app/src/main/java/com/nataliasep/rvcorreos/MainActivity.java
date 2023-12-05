package com.nataliasep.rvcorreos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int cantidadDeCorreos = 20;
    private Correo[] listaCorreos = new Correo[cantidadDeCorreos];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < cantidadDeCorreos; i++) {
            String asunto = "Asunto del correo " + (i + 1);
            String cuerpo = "Cuerpo del correo " + (i + 1);
            listaCorreos[i] = new Correo(asunto, cuerpo);
        }

        RecyclerView recView = findViewById(R.id.rvListaCorreos);
        recView.setHasFixedSize(true);

        try{
            CorreoAdapter adapter = new CorreoAdapter(listaCorreos, this);
            recView.setAdapter(adapter);
            recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        }catch (Exception e){
            Toast.makeText(this, "No se pudieron cargar los datos de Correos", Toast.LENGTH_SHORT).show();
        }
    }
}