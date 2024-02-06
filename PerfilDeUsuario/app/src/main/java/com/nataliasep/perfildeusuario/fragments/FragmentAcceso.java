package com.nataliasep.perfildeusuario.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nataliasep.perfildeusuario.R;
import com.nataliasep.perfildeusuario.modelo.Persona;

import org.w3c.dom.Text;

public class FragmentAcceso extends Fragment {
    public interface IOnAttachListener {
        Persona getPersona();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_acceso, container, false);
        TextView tvUsername = layout.findViewById(R.id.tvUsername);
        return layout;
    }
}