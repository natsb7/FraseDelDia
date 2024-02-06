package com.nataliasep.toolbarperfilusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nataliasep.toolbarperfilusuario.fragments.FragmentAccess;
import com.nataliasep.toolbarperfilusuario.fragments.FragmentPersonal;
import com.nataliasep.toolbarperfilusuario.fragments.FragmentProfessional;
import com.nataliasep.toolbarperfilusuario.models.AccessData;
import com.nataliasep.toolbarperfilusuario.models.PersonalData;
import com.nataliasep.toolbarperfilusuario.models.ProfessionalData;
import com.nataliasep.toolbarperfilusuario.models.User;

public class MainActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonalData personal = new PersonalData("Juan", "Palomo García", "12345678B", "C/ Major, 35  03730 Xàbia", "avatar1", "2003-04-08");
        ProfessionalData profesional = new ProfessionalData("B123456","John Doe, S.L.", "C/ Mayor, 25  03002 Alacant", "http://www.johndoe.com", "juanpalomo@johndoe.com");
        AccessData acceso = new AccessData("pepito", "palotes");
        user = new User(personal, profesional, acceso);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MyFragmentStateAdapter mMyFragmentStateAdapter = new MyFragmentStateAdapter(this);
        ViewPager2 mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mMyFragmentStateAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        TabLayoutMediator tlm = new TabLayoutMediator(tabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText(R.string.tab_personal);
                        break;
                    case 1:
                        tab.setText(R.string.tab_professional);
                        break;
                    case 2:
                        tab.setText(R.string.tab_access);
                        break;
                }
            }
        });
        tlm.attach();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyFragmentStateAdapter extends FragmentStateAdapter {

        public MyFragmentStateAdapter(@NonNull FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Bundle b = new Bundle();
            Fragment f = null;
            switch (position) {
                case 0:
                    f = new FragmentPersonal();
                    b.putSerializable(FragmentPersonal.BUNDLE_PERSONAL, user.getPersonalData());
                    f.setArguments(b);
                    break;
                case 1:
                    f = new FragmentProfessional();
                    b.putSerializable(FragmentProfessional.BUNDLE_PROFESSIONAL, user.getProfessionalData());
                    f.setArguments(b);
                    break;
                case 2:
                    f = new FragmentAccess();
                    b.putSerializable(FragmentAccess.BUNDLE_ACCESS, user.getAccessData());
                    f.setArguments(b);
                    break;
                default:
                    throw new RuntimeException("Position invalid: " + position);
            }
            return f;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}