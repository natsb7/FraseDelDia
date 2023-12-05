package com.nataliasep.contactsfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {
    private final Contacto[] contactos;
    private IContactoListener listener;

    public ContactoAdapter(Contacto[] contactos, IContactoListener listener){
        this.contactos = contactos;
        this.listener = listener;
    }

    /*public void setListener(IContactoListener listener) {
        this.listener = listener;
    }*/

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoAdapter.ContactoViewHolder holder, int position) {
        Contacto contacto = contactos[position];
        holder.bindContacto(contacto);
    }

    @Override
    public int getItemCount() {
        return contactos.length;
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private final TextView tvName;
        private final TextView tvPhone;
        private final StringBuilder sb;
        private final Context contexto;
        public ContactoViewHolder(View itemView){
            super(itemView);
            this.contexto = itemView.getContext();
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            sb = new StringBuilder();
            itemView.setOnClickListener(this);
        }

        public void bindContacto(Contacto contacto){
            sb.setLength(0);
            sb.append(contacto.getName()).append(" ").append(contacto.getFirstSurname());
            tvName.setText(sb.toString());
            tvPhone.setText(contacto.getPhone1());
        }
        @Override
        public void onClick(View view) {
            if(listener != null){
                listener.onClick(getAdapterPosition());
            }

        }
    }
}
