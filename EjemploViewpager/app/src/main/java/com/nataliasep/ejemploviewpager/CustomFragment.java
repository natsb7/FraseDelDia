package com.nataliasep.ejemploviewpager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CustomFragment extends Fragment {
    public interface IOnAttach{
        int getNumero();
    }
    private int numero;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_custom, container, false);
        TextView tvInfo = layout.findViewById(R.id.tvInfo);
        /*Bundle bundle = getArguments();
        if (bundle != null){
            numero = bundle.getInt(MainActivity.POSICION_ACTUAL);
        }*/
        tvInfo.setText("Estoy en la pestanya "+ numero);
        return layout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        numero = ((IOnAttach) context).getNumero();
    }
}
