package com.nataliasep.contactsfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetalle extends Fragment {
    public interface IOnAttachListener{
        Contacto getContacto();
    }

    private TextView tvName;
    private TextView tvSurnames;
    private TextView tvBirth;
    private TextView tvCompany;
    private TextView tvPhone1;
    private TextView tvPhone2;
    private TextView tvEmail;
    private TextView tvAddress;
    private final StringBuilder sb;
    private Contacto contacto;

    public FragmentDetalle(){
        super(R.layout.fragment_detalle);
        sb = new StringBuilder();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tvName);
        tvSurnames = view.findViewById(R.id.tvSur);
        tvBirth = view.findViewById(R.id.tvBirth);
        tvCompany = view.findViewById(R.id.tvComp);
        tvAddress = view.findViewById(R.id.tvAdress);
        tvPhone1 = view.findViewById(R.id.tvPhone1);
        tvPhone2 = view.findViewById(R.id.tvPhone2);
        tvEmail = view.findViewById(R.id.tvEmail);

        if (contacto != null)
            muestraDetalle(contacto);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        contacto = attachListener.getContacto();
    }
    public void muestraDetalle(Contacto contacto){
        this.contacto = contacto;
        tvName.setText(contacto.getName());
        sb.setLength(0);
        sb.append(contacto.getFirstSurname()).append(" ").append(contacto.getSecondSurname());
        tvSurnames.setText(sb.toString());
        tvBirth.setText(contacto.getBirth());
        tvCompany.setText(contacto.getCompany());
        tvAddress.setText(contacto.getAddress());
        tvPhone1.setText(contacto.getPhone1());
        tvPhone2.setText(contacto.getPhone2());
        tvEmail.setText(contacto.getEmail());

    }
}
