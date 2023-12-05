package com.nataliasep.rvcorreos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CorreoAdapter extends RecyclerView.Adapter<CorreoAdapter.CorreoViewHolder> {

    private final Correo[] listaCorreos;

    public CorreoAdapter(Correo[] listaCorreos, Context contexto){
        this.listaCorreos = listaCorreos;
    }

    @NonNull
    @Override
    public CorreoAdapter.CorreoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_correo, parent, false);
        return new CorreoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CorreoAdapter.CorreoViewHolder holder, int position) {
        Correo correo = listaCorreos[position];
        holder.bindCorreo(correo);

    }

    @Override
    public int getItemCount() {
        return listaCorreos.length;
    }

    public class  CorreoViewHolder extends RecyclerView.ViewHolder{

        private final TextView asunto;
        private final TextView cuerpo;
        private final Context context;

        public CorreoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.asunto = itemView.findViewById(R.id.tvAsunto);
            this.cuerpo = itemView.findViewById(R.id.tvCuerpo);
            this.context = itemView.getContext();
        }

        public void bindCorreo(Correo correo){
            asunto.setText(correo.getAsunto());
            cuerpo.setText(correo.getCuerpo());
        }
    }
}
