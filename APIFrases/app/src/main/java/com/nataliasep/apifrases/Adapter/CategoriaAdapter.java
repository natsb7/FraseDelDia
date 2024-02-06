package com.nataliasep.apifrases.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nataliasep.apifrases.FraseActivity;
import com.nataliasep.apifrases.Models.Autor;
import com.nataliasep.apifrases.Models.Categoria;
import com.nataliasep.apifrases.R;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private final List<Categoria> categorias;

    public CategoriaAdapter(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    @NonNull
    @Override
    public CategoriaAdapter.CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CategoriaAdapter.CategoriaViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.bindCategoria(categoria);
    }


    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvText;
        private final Context contexto;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvText = itemView.findViewById(R.id.tvText);
            itemView.setOnClickListener(this);
            contexto = itemView.getContext();
        }

        public void bindCategoria(Categoria categoria) {
            tvText.setText(categoria.getNombre());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Categoria categoria = categorias.get(position);
            Intent i = new Intent(contexto, FraseActivity.class);
            i.putExtra("categoriaID", categoria.getId());
            contexto.startActivity(i);

        }
    }
}
