package com.nataliasep.perfildeusuario.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nataliasep.perfildeusuario.R;
import com.nataliasep.perfildeusuario.modelo.Empresa;

import org.w3c.dom.Text;

public class FragmentProfesional extends Fragment {

    public interface IOnAttachListener {
        Empresa getEmpresa();
    }
    private Empresa empresa;
    private TextView tvNom;
    private TextView tvCif;
    private TextView tvDir;
    private TextView tvWeb;
    private TextView tvCorreo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_profesional, container, false);
        tvNom = layout.findViewById(R.id.tvNom);
        tvCif = layout.findViewById(R.id.tvCif);
        tvDir = layout.findViewById(R.id.tvDir);
        tvWeb = layout.findViewById(R.id.tvWeb);
        tvCorreo = layout.findViewById(R.id.tvCorreo);

        tvNom.setText(empresa.getNombreEmp());
        tvCif.setText(empresa.getCif());
        tvDir.setText(empresa.getDirEmp());
        tvWeb.setText(empresa.getWeb());
        tvCorreo.setText(empresa.getEmail());

        return layout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FragmentProfesional.IOnAttachListener listener = (FragmentProfesional.IOnAttachListener) context;
        empresa = listener.getEmpresa();
    }
}
