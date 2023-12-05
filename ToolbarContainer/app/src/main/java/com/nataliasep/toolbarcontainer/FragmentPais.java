package com.nataliasep.toolbarcontainer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentPais extends Fragment {
    public interface IOnAttachListener{
        Pais[] getPaises();
    }

    private Pais[] listaPaises;
    private Context contexto;
    public FragmentPais(){
        super(R.layout.fragment_pais);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PaisAdapter paisAdapter = new PaisAdapter(listaPaises);
        RecyclerView recyclerView = view.findViewById(R.id.rvPaises);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(paisAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    public void onAttach(Context context){
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        listaPaises = attachListener.getPaises();
    }
}
