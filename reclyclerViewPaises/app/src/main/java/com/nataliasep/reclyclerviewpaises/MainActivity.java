package com.nataliasep.reclyclerviewpaises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Pais[] paises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cargamos los datos
        setContentView(R.layout.activity_main);
        RecyclerView recView = findViewById(R.id.rvListaPaises);
        recView.setHasFixedSize(true);

        CountryParser parser = new CountryParser(this);

        if(parser.parse()) {
            //parseamos los datos
            paises = parser.getCountries();
            CountryAdapter adapter = new CountryAdapter(paises, this);
            recView.setAdapter(adapter);
            recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            //recView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            Toast.makeText(this, "No se pudieron obtener los datos de los países", Toast.LENGTH_SHORT).show();
        }
    }
}