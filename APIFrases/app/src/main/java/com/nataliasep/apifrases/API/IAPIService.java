package com.nataliasep.apifrases.API;

import com.nataliasep.apifrases.Models.Autor;
import com.nataliasep.apifrases.Models.Categoria;
import com.nataliasep.apifrases.Models.Frase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAPIService {

    @GET("categoria/all")
        public Call<List<Categoria>> getCategorias();

    @GET("categoria/{id}")
        public Call<Categoria> getCategoria(@Path("id") int id);

    @GET("autor/{id}")
        public Call<Autor> getAutor(@Path("id") int id);

    @GET("autor/all")
        public Call<List<Autor>> getAutores();

    @GET("frase/autor/{id}")
        public Call<List<Frase>> getFrasesAutor(@Path("id") int id);

    @GET("frase/categoria/{id}")
        public Call<List<Frase>> getFrasesCategoria(@Path("id") int id);

    @GET("frase/dia/{fecha}")
    Call<Frase> obtenerFraseDelDia(@Path("fecha") String fecha);
}
