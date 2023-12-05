package com.nataliasep.perfildeusuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class FragmentProfesional extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_profesional, container, false);
        TextView tvNom = layout.findViewById(R.id.tvNom);
        TextView tvCif = layout.findViewById(R.id.tvCif);
        TextView tvDir = layout.findViewById(R.id.tvDir);
        TextView tvWeb = layout.findViewById(R.id.tvWeb);
        TextView tvCorreo = layout.findViewById(R.id.tvCorreo);
        return layout;
    }
}
