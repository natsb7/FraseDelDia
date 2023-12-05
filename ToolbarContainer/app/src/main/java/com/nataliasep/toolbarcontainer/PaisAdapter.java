package com.nataliasep.toolbarcontainer;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaisAdapter extends RecyclerView.Adapter<PaisAdapter.PaisViewHolder> {
    private final Pais[] paises;
    //private  final Context contexto;

    public PaisAdapter(Pais[] paises) {
        this.paises = paises;
        //this.contexto = contexto;
    }

    @NonNull
    @Override
    public PaisAdapter.PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new PaisViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisAdapter.PaisViewHolder holder, int position) {
        Pais pais = paises[position];
        holder.bindPais(pais);
    }

    @Override
    public int getItemCount() {
        return paises.length;
    }

    public class PaisViewHolder extends RecyclerView.ViewHolder{
        private final ImageView ivBandera;
        private final TextView tvNombrePais;
        private final TextView tvCapital;
        private final TextView tvPoblacion;
        private final Context contexto;

        public PaisViewHolder(@NonNull View itemView) {
            super(itemView);
            this.contexto = itemView.getContext();
            this.ivBandera = itemView.findViewById(R.id.ivBandera);
            this.tvNombrePais = itemView.findViewById(R.id.tvPais);
            this.tvCapital = itemView.findViewById(R.id.tvCapital);
            tvPoblacion = itemView.findViewById(R.id.tvPoblacion);
        }

        public void bindPais(Pais pais){
            try {
                Resources res = contexto.getResources();
                String flagName = "_" + pais.getCodigo().toLowerCase();
                int resID = res.getIdentifier(flagName, "drawable", contexto.getPackageName());
                if (resID != 0) {
                    ivBandera.setImageResource(resID);
                } else {
                    flagName = "_onu";
                    resID = res.getIdentifier(flagName, "drawable", contexto.getPackageName());
                    ivBandera.setImageResource(resID);
                }
            } catch (Exception e) {

            }
            tvNombrePais.setText(pais.getNombre());
            tvCapital.setText(pais.getCapital());
            tvPoblacion.setText(String.valueOf(pais.getPoblacion()));
        }
    }
}
