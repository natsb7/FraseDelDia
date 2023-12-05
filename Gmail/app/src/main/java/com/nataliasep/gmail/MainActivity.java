package com.nataliasep.gmail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.nataliasep.gmail.databinding.ActivityMainBinding;
import com.nataliasep.gmail.fragment.FragmentEnvio;
import com.nataliasep.gmail.fragment.FragmentListado;
import com.nataliasep.gmail.models.Correo;
import com.nataliasep.gmail.models.Usuario;
import com.nataliasep.gmail.parser.Parser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
FragmentListado.IOnAttachListener{
    private DrawerLayout drawerLayout;
    private FragmentListado.ListType tipoListado;
    private FragmentListado fragmentListado;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Correo> listasCorreos;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            tipoListado = FragmentListado.ListType.CORREOS_RECIBIDOS;
            setContentView(R.layout.activity_main);
            fragmentListado = (FragmentListado) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            drawerLayout = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();


            navigationView.setNavigationItemSelectedListener(this);
            View headerLayout = navigationView.getHeaderView(0);
            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    if(drawerLayout.isOpen()){
                        drawerLayout.close();
                    }else{
                        finish();
                        getSupportFragmentManager().popBackStack();
                    }
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        listasCorreos = null;
        if (itemId == R.id.nav_recibidos){
                listasCorreos = usuarios.get(0).getRecibidos();
                tipoListado = FragmentListado.ListType.CORREOS_RECIBIDOS;
                fragmentListado.actualizarListado(tipoListado, usuarios.get(0), listasCorreos);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, FragmentListado.class,null).commit();
                return true;
        } else if(itemId == R.id.nav_enviados) {
                listasCorreos = usuarios.get(0).getEnviados();
                tipoListado = FragmentListado.ListType.CORREOS_ENVIADOS;
                fragmentListado.actualizarListado(tipoListado, usuarios.get(0), listasCorreos);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, FragmentListado.class,null).commit();
                return true;
        }else if (itemId == R.id.nav_noleidos) {
                listasCorreos = usuarios.get(0).getNoLeidos();
                tipoListado = FragmentListado.ListType.CORREOS_NOLEIDOS;
                fragmentListado.actualizarListado(tipoListado, usuarios.get(0), listasCorreos);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, FragmentListado.class,null).commit();
                return true;
        }else if (itemId == R.id.nav_enviar){
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, FragmentEnvio.class,null).commit();
                return true;
        }else if(itemId == R.id.nav_spam) {
                listasCorreos = usuarios.get(0).getSpam();
                tipoListado = FragmentListado.ListType.CORREOS_SPAM;
                fragmentListado.actualizarListado(tipoListado, usuarios.get(0), listasCorreos);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, FragmentListado.class,null).commit();
                return true;
        }else if(itemId == R.id.nav_papelera){
                listasCorreos = usuarios.get(0).getBorrados();
                tipoListado = FragmentListado.ListType.CORREOS_PAPELERA;
                fragmentListado.actualizarListado(tipoListado, usuarios.get(0), listasCorreos);
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, FragmentListado.class,null).commit();
                return true;
        }else
            throw new RuntimeException("Unknown Fragment");
        //drawerLayout.close();

    }
    public void cargarDatos(){
        Parser parser = new Parser(this);
        if(parser.parse()){
            this.usuarios = parser.getUsuarios();
        }
    }
    public Usuario getUsuario(){
        if (usuarios == null){
            cargarDatos();
        }
        return usuarios.get(0);
    }

    @Override
    public ArrayList<Correo> getListaCorreos() {
        return listasCorreos;
    }

    @Override
    public FragmentListado.ListType tipoListado() {
        return tipoListado;
    }
}