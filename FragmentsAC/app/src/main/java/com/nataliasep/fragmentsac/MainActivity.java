package com.nataliasep.fragmentsac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miAdaptadorFragment adaptador = new miAdaptadorFragment(this);
        ViewPager2 miviewPager = findViewById(R.id.vpPager);
        miviewPager.setAdapter(adaptador);
    }

    public class miAdaptadorFragment extends FragmentStateAdapter{
        public miAdaptadorFragment(FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    FragmentInicio inicio = new FragmentInicio();
                    return inicio;
                case 1:
                    FragmentLigas ligas = new FragmentLigas();
                    return ligas;
                case 2:
                    FragmentUbicacion ubicacion = new FragmentUbicacion();
                    return ubicacion;
                case 3:
                    FragmentVecinos vecinos = new FragmentVecinos();
                    return vecinos;
                case 4:
                    FragmentVecino vecino = new FragmentVecino();
                    return vecino;
                case 5:
                    FragmentPujas pujas = new FragmentPujas();
                    return pujas;
                case 6:
                    FragmentMudanza mudanza = new FragmentMudanza();
                    return mudanza;
                case 7:
                    FragmentNoActivity noActivity = new FragmentNoActivity();
                    return noActivity;
                case 8:
                    FragmentPreferencias preferencias = new FragmentPreferencias();
                    return preferencias;
                case 9:
                    FragmentCreditos creditos = new FragmentCreditos();
                    return creditos;

            }
            return new Fragment();
        }

        @Override
        public int getItemCount() {
            return 9;
        }
    }
}