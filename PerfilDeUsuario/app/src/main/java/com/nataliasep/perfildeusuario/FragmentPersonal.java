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

public class FragmentPersonal extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_personal, container, false);
        TextView tvNombre = layout.findViewById(R.id.tvNombre);
        TextView tvApellidos = layout.findViewById(R.id.tvApellidos);
        TextView tvCumple = layout.findViewById(R.id.tvCumple);
        TextView tvDireccion = layout.findViewById(R.id.tvDireccion);
        TextView tvID = layout.findViewById(R.id.tvID);
        return layout;
    }
}
