package com.nataliasep.apifrases;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nataliasep.apifrases.API.IAPIService;
import com.nataliasep.apifrases.API.RestClient;
import com.nataliasep.apifrases.Models.Autor;
import com.nataliasep.apifrases.Models.Categoria;
import com.nataliasep.apifrases.Models.Frase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button bAutores, bCategorias, bFDD;
    private List<Autor> autores;
    private List<Categoria> categorias;
    private IAPIService api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAutores = findViewById(R.id.bAutor);
        bCategorias = findViewById(R.id.bCategoria);
        bFDD = findViewById(R.id.bFDD);

        api = RestClient.getInstance();

        Intent i = new Intent(this, ListActivity.class);

        bAutores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAutores(i);

            }
        });

        bCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategorias(i);

            }
        });

        bFDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.obtenerFraseDelDia(getFechaActual()).enqueue(new Callback<Frase>() {
                    @Override
                    public void onResponse(Call<Frase> call, Response<Frase> response) {
                        if (response.isSuccessful()) {
                            Log.d("FRASE DEL DÍA", "Frase del día obtenida de la api rest: " + response.body().getTexto());
                            Frase frase = response.body();
                            mostrarDialogoFrases(frase);
                        }
                    }

                    @Override
                    public void onFailure(Call<Frase> call, Throwable t) {
                        Log.d("RETROFIT", "Error al obtener la frase del día: " + t.getMessage());
                        String url = call.request().url().toString();
                        Log.d("RETROFIT", "URL de la llamada: " + url);
                    }
                });
            }
        });

    }

    private void mostrarDialogoFrases(Frase frase) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
            builder.setView(view);

            TextView messageTextView = view.findViewById(R.id.tvFrase);
            Button acceptButton = view.findViewById(R.id.dialogButton);
            messageTextView.setText("'" + frase.getTexto() + "'");

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   builder.create().dismiss();
                }
            });

            builder.show();

    }

    private void loadAutores(Intent i) {
        api.getAutores().enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                autores = response.body();
                i.putExtra("autores", new ArrayList<>(autores));
                startActivity(i);

            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void loadCategorias(Intent i) {
        api.getCategorias().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                categorias = response.body();
                i.putExtra("categorias", new ArrayList<>(categorias));
                startActivity(i);


            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });


    }

    public String getFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }


}