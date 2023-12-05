package com.nataliasep.superheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context contexto;
    List<Item> items;

    public Adapter(Context contexto, List<Item> items) {
        this.contexto = contexto;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(contexto).inflate(R.layout.item_sh,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.nombre.setText(items.get(position).getNombre());
        holder.poderes.setText(items.get(position).getPoderes());
        holder.saga.setText(items.get(position).getSaga());
        holder.foto.setImageResource(items.get(position).getFoto());
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
