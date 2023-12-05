package com.nataliasep.notasalumnos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorAlumno extends RecyclerView.Adapter<AdaptadorAlumno.AlumnoViewHolder> {

    private final Alumno[] listaAlumnos;
    private final IClickListener listener;

    public AdaptadorAlumno(Alumno[] listaAlumnos, IClickListener listener) {
        this.listaAlumnos = listaAlumnos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdaptadorAlumno.AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        return new AlumnoViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAlumno.AlumnoViewHolder holder, int position) {
        Alumno alumno = listaAlumnos[position];
        holder.bindAlumno(alumno);

    }

    @Override
    public int getItemCount() {
        if(listaAlumnos != null){
            return listaAlumnos.length;
        }else{
            return 0;
        }
    }
    public class AlumnoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvNombre;
        private final TextView tvEmail;
        private final TextView tvEdad;
        private final StringBuilder sb;


        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            sb = new StringBuilder();
            itemView.setOnClickListener(this);

        }

        public void bindAlumno(Alumno alumno){
            sb.setLength(0);
            sb.append(alumno.getNombre()).append(" ").append(alumno.getApellido1()).append(" ").append(alumno.getApellido2());
            tvNombre.setText(sb.toString());
            tvEmail.setText(alumno.getEmail());
            tvEdad.setText(String.valueOf(alumno.getEdad()));
        }
        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onClick(getAdapterPosition());
            }
        }
    }
}
