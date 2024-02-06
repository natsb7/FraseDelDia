package com.nataliasep.apifrases.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nataliasep.apifrases.Models.Frase;
import com.nataliasep.apifrases.R;

import java.util.List;

public class FraseAdapter extends RecyclerView.Adapter<FraseAdapter.FraseViewHolder> {

    private List<Frase> frases;

    public FraseAdapter(List<Frase> frases) {
        this.frases = frases;

    }

    public void setData(List<Frase> frases) {
        this.frases = frases;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public FraseAdapter.FraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new FraseAdapter.FraseViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull FraseViewHolder holder, int position) {
        Frase frase = frases.get(position);
        holder.bindFrase(frase);
    }

    @Override
    public int getItemCount() {
        return frases.size();
    }

    public class FraseViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvText;

        public FraseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvText = itemView.findViewById(R.id.tvText);
        }

        public void bindFrase(Frase frase) {
            tvText.setText(frase.getTexto());
        }
    }
}
