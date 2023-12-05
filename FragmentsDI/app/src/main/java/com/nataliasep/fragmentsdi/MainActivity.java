package com.nataliasep.fragmentsdi;

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
        ViewPager2 miviewpager = findViewById(R.id.vpPager);
        miviewpager.setAdapter(adaptador);


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
                    PrimerFragment uno = new PrimerFragment();
                    return uno;
                case 1: 
                    SegundoFragment dos = new SegundoFragment();
                    return dos;
                case 2: 
                    TercerFragment tercer = new TercerFragment();
                    return tercer;
                    
            }
            return new Fragment();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}