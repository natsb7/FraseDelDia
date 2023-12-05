package com.nataliasep.pruebafragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CorreoViewHolder> {

    private final List<Correo> correos;
    private final IOnClickListener listener;
    public Adapter(List<Correo> correos, IOnClickListener listener) {
        this.correos = correos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CorreoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_correo, parent, false);
        return new CorreoViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CorreoViewHolder holder, int position) {
        Correo correo = correos.get(position); //obtenemos le correo de la posición solicitada
        holder.bindCorreo(correo);
    }

    @Override
    public int getItemCount() {
        return correos.size();
    }

    public class CorreoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        private TextView tvAsunto;
        private TextView tvCuerpo;
        private final Context context;
        public CorreoViewHolder(@NonNull View itemView) {
            super(itemView);
            //guardamos el contexto para poder acceder a los recursos de la aplicación
            this.context = itemView.getContext();
            //obtenemos la referencia a los componentes del layout
            this.tvAsunto = itemView.findViewById(R.id.tvAsunto);
            this.tvCuerpo = itemView.findViewById(R.id.tvCuerpo);
            itemView.setOnClickListener(this);
        }
        public void bindCorreo(Correo correo) {
            tvAsunto.setText(correo.getAsunto());
            tvCuerpo.setText(correo.getCuerpo());
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onClick(getAdapterPosition());
            }

        }
    }
}
