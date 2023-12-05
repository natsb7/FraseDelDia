package com.nataliasep.toolbarcontainer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {
    private final Alumno[] listaAlumnos;

    public AlumnoAdapter(Alumno[] listaAlumnos) {
        this.listaAlumnos = listaAlumnos;

    }

    @NonNull
    @Override
    public AlumnoAdapter.AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        return new AlumnoViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoAdapter.AlumnoViewHolder holder, int position) {
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

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {
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

        }

        public void bindAlumno(Alumno alumno) {
            sb.setLength(0);
            sb.append(alumno.getNombre()).append(" ").append(alumno.getApellido1()).append(" ").append(alumno.getApellido2());
            tvNombre.setText(sb.toString());
            tvEmail.setText(alumno.getEmail());
            tvEdad.setText(String.valueOf(alumno.getEdad()));
        }
    }
}
