package com.nataliasep.superheroes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView foto;
    TextView nombre, poderes, saga;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        foto = itemView.findViewById(R.id.ivFoto); // Reemplaza R.id.imageViewFoto con el ID correcto de la imagen.
        nombre = itemView.findViewById(R.id.tvNombre); // Reemplaza R.id.textViewNombre con el ID correcto del TextView.
        poderes = itemView.findViewById(R.id.tvPoderes); // Reemplaza R.id.textViewPoderes con el ID correcto del TextView.
        saga = itemView.findViewById(R.id.tvSaga); // Reemplaza R.id.textViewSaga con el ID correcto del TextView.
    }
}
