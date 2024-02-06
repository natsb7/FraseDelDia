package com.nataliasep.apifrases;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nataliasep.apifrases.API.IAPIService;
import com.nataliasep.apifrases.API.RestClient;
import com.nataliasep.apifrases.Adapter.AutorAdapter;
import com.nataliasep.apifrases.Adapter.CategoriaAdapter;
import com.nataliasep.apifrases.Models.Autor;
import com.nataliasep.apifrases.Models.Categoria;
import com.nataliasep.apifrases.Models.Frase;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        rvList = findViewById(R.id.rvList);

        Intent i = getIntent();
        if(i.hasExtra("autores")) {
            List<Autor> autores = (List<Autor>) i.getSerializableExtra("autores");
            AutorAdapter autorAdapter = new AutorAdapter(autores);
            rvList.setAdapter(autorAdapter);



        } else if (i.hasExtra("categorias")) {
            List<Categoria> categorias = (List<Categoria>) i.getSerializableExtra("categorias");
            CategoriaAdapter categoriaAdapter = new CategoriaAdapter(categorias);
            rvList.setAdapter(categoriaAdapter);

        }
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
    }
}
