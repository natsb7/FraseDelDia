package com.nataliasep.notasalumnos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class AdaptadorNotas extends RecyclerView.Adapter<AdaptadorNotas.NotasViewHolder> {
    private Nota[] notas;

    public AdaptadorNotas(Nota[] notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public AdaptadorNotas.NotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asignatura, parent, false);
        return new NotasViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorNotas.NotasViewHolder holder, int position) {
        Nota nota = notas[position];
        holder.BindNotas(nota);

    }

    @Override
    public int getItemCount() {
        return notas.length;
    }

    public void setNotas(Nota[] notas) {
        this.notas = notas;
        notifyDataSetChanged();
    }

    public class NotasViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvcodAsig;
        private final TextView tvnomAsig;
        private final TextView tvnotaAsig;

        public NotasViewHolder(View itemView) {
            super(itemView);
            this.tvcodAsig = itemView.findViewById(R.id.tvAsigID);
            this.tvnomAsig = itemView.findViewById(R.id.tvAsigNom);
            this.tvnotaAsig = itemView.findViewById(R.id.tvNota);
        }

        public void BindNotas(Nota nota) {
            tvcodAsig.setText(nota.getAsignatura().getId());
            tvnomAsig.setText(nota.getAsignatura().getNombre());
            tvnotaAsig.setText(String.valueOf(nota.getNota()));

        }
    }
}
