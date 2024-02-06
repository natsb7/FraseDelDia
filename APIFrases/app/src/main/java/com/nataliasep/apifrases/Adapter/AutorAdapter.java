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
import com.nataliasep.apifrases.R;

import java.util.List;

public class AutorAdapter extends RecyclerView.Adapter<AutorAdapter.AutorViewHolder> {

    private final List<Autor> autores;

    public AutorAdapter(List<Autor> autores) {
        this.autores = autores;
    }
    @NonNull
    @Override
    public AutorAdapter.AutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AutorViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AutorAdapter.AutorViewHolder holder, int position) {
        Autor autor = autores.get(position);
        holder.bindAutor(autor);

    }

    @Override
    public int getItemCount() {
        return autores.size();
    }

    public class AutorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvText;
        private final Context contexto;

        public AutorViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvText = itemView.findViewById(R.id.tvText);
            itemView.setOnClickListener(this);
            contexto = itemView.getContext();
        }

        public void bindAutor(Autor autor) {
            tvText.setText(autor.getNombre());
        }

        @Override
        public void onClick(View v) {
            Autor autor = autores.get(getAdapterPosition());
            int autorID = autores.get(getAdapterPosition()).getId();
            Log.d("AutorAdapter", "Autor seleccionado: " + autor.getNombre());
            Intent i = new Intent(contexto, FraseActivity.class);
            i.putExtra("autorID", autorID);
            contexto.startActivity(i);
        }
    }
}
