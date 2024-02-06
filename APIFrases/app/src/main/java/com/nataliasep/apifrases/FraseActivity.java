package com.nataliasep.apifrases;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nataliasep.apifrases.API.IAPIService;
import com.nataliasep.apifrases.API.RestClient;
import com.nataliasep.apifrases.Adapter.FraseAdapter;
import com.nataliasep.apifrases.Models.Frase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FraseActivity extends AppCompatActivity {

    private FraseAdapter fraseAdapter;
    private RecyclerView rvList;
    private IAPIService api;
    private List<Frase> frases;

    private int autorID;
    private int categoriaID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        rvList = findViewById(R.id.rvList);
        api = RestClient.getInstance();
        Intent i = getIntent();

        if (i.hasExtra("autorID")) {
            autorID = i.getIntExtra("autorID", 0);
            api.getFrasesAutor(autorID).enqueue(new Callback<List<Frase>>() {
                @Override
                public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {
                    frases = response.body();
                    Log.d("frases", frases.size() + "");
                    setupRecyclerView();
                }

                @Override
                public void onFailure(Call<List<Frase>> call, Throwable t) {
                    Log.e("FraseActivity", "Error al cargar frases");
                }
            });
        } else if (i.hasExtra("categoriaID")) {
            categoriaID = i.getIntExtra("categoriaID", 0);
            api.getFrasesCategoria(categoriaID).enqueue(new Callback<List<Frase>>() {
                @Override
                public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {
                    frases = response.body();
                    Log.d("frases", frases.size() + "");
                    setupRecyclerView();
                }

                @Override
                public void onFailure(Call<List<Frase>> call, Throwable t) {
                    Log.e("FraseActivity", "Error al cargar frases");
                }
            });
        } else {
            // Manejar el caso en el que no hay extras en el Intent
        }
    }

    private void setupRecyclerView() {
        fraseAdapter = new FraseAdapter(frases);
        rvList.setAdapter(fraseAdapter);
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
    }
}
