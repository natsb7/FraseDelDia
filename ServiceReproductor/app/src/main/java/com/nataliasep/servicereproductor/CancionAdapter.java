package com.nataliasep.servicereproductor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CancionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //
    Context context;
    List<Cancion> canciones;
    private ICancionListener listener;

    public CancionAdapter(Context context, List<Cancion> canciones, ICancionListener listener){
        this.context = context;
        this.canciones = canciones;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new CancionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cancion cancion = canciones.get(position);
        CancionViewHolder viewHolder = (CancionViewHolder) holder;
        viewHolder.tvTituloCancion.setText(cancion.getTitulo());
    }

    public class CancionViewHolder extends  RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView tvTituloCancion;
        public CancionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTituloCancion = itemView.findViewById(R.id.tvTituloCancion);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onClick(getAdapterPosition());
            }
        }
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }
}
