package com.nataliasep.gmail.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nataliasep.gmail.R;
import com.nataliasep.gmail.models.Contacto;
import com.nataliasep.gmail.models.Correo;
import com.nataliasep.gmail.models.Usuario;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

   private final Usuario usuario;
   private final ArrayList<Correo> listaCorreos;



    public Adapter(Usuario usuario, ArrayList<Correo>listaCorreos) {
        this.usuario = usuario;
        this.listaCorreos = listaCorreos;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Correo correo = listaCorreos.get(position);
      Usuario usuario = this.usuario;
      holder.bindCorreo(correo, usuario);
    }

    @Override
    public int getItemCount() {
        return listaCorreos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivFoto;
        private final TextView tvNombre;
        private final TextView tvTitulo;
        private final TextView tvCuerpo;
        private final TextView tvFecha;
        private final TextView tvHora;
        private final Context context;
        private final StringBuilder sb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvCuerpo = itemView.findViewById(R.id.tvCuerpo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHora = itemView.findViewById(R.id.tvHora);
            this.context = itemView.getContext();
            sb = new StringBuilder();

        }

        public void bindCorreo(Correo correo, Usuario usuario){

            ArrayList<Contacto> listaContactos = usuario.getContactos();
            String remitenteEmail = listaCorreos.get(getAdapterPosition()).getFrom();
            Contacto contacto = null;

            for(int i = 0; i< listaContactos.size(); i++){
                if(listaContactos.get(i).getEmail().toString().equals(remitenteEmail)){
                    contacto = listaContactos.get(i);
                    break;
                }
            }

            try{
                Resources res = context.getResources();
                String foto = "c" + contacto.getFoto();
                int resID = res.getIdentifier(foto, "drawable", context.getPackageName());
                ivFoto.setImageResource(resID);
            }catch (Exception e){

            }
            sb.setLength(0);
            sb.append(contacto.getNombre()).append(" ").append(contacto.getPrimerApellido()).append(" ").append(contacto.getSegundoApellido());

            if(correo.isLeido()){
                tvNombre.setText(sb);
                tvTitulo.setText(correo.getAsunto());
                tvCuerpo.setText(correo.getCuerpo());
                tvFecha.setText(correo.getSoloFecha());
                tvHora.setText(correo.getHora());
            }else{
                tvNombre.setText(sb);
                tvNombre.setTypeface(null, Typeface.BOLD);
                tvTitulo.setText(correo.getAsunto());
                tvTitulo.setTypeface(null, Typeface.BOLD);
                tvCuerpo.setText(correo.getCuerpo());
                tvCuerpo.setTypeface(null, Typeface.BOLD);
                tvFecha.setText(correo.getSoloFecha());
                tvFecha.setTypeface(null, Typeface.BOLD);
                tvFecha.setTextColor(Color.BLUE);
                tvHora.setText(correo.getHora());
                tvHora.setTypeface(null, Typeface.BOLD);
                tvHora.setTextColor(Color.BLUE);
            }
        }
    }
}
