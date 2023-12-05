package com.nataliasep.reclyclerviewpaises;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private final Pais[] paises;
    private  final Context contexto;

    public CountryAdapter(Pais[] paises, Context contexto) {
        this.paises = paises;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //cada vez que un layout del xml se cree en el view
        //con el inflater traducimos el xml a datos?
        //creamos los layout, esto tb hace q los recicles
        //se ejecuta cada vez para decirle QUE DATOS NECESITAMOS rellenar
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView, contexto);
    }
    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        //este método se ejecuta cada vez que se crea un pais que se debe mostrar
        //rellenamos el layout
        Pais pais = paises[position];
        holder.bindCountry(pais);
    }
    @Override
    public int getItemCount() {
        //devolvemos el num de elementos de la array de paises, pq lo necesitamos?
        return paises.length;
    }
    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivBandera;
        private final TextView tvNombrePais;
        private final TextView tvCapital;
        private final TextView tvPoblacion;
        private final Context context;

        public CountryViewHolder(@NonNull View itemView, Context contexto) {
            //cada vez q se cargue el layout se llama este metodo q le dice con QUÉ datos se rellenan los layouts
            super(itemView);
            this.context = contexto;
            this.ivBandera = itemView.findViewById(R.id.ivBandera);
            this.tvNombrePais = itemView.findViewById(R.id.tvPais);
            this.tvCapital = itemView.findViewById(R.id.tvCapital);
            tvPoblacion = itemView.findViewById(R.id.tvPoblacion);
        }
        public void bindCountry(Pais pais) {
            //rellenamos los datos
            //aqui rellenamos concretamente los datos de la bandera, ya q lo deberemos hacer de manera distinta
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
