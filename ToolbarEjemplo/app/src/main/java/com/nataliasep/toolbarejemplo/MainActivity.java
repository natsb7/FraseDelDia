package com.nataliasep.toolbarejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_saluda){
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show();
            return true;
        }else if(itemId == R.id.action_setting){
            Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show();
            return true;
        }else if(itemId == R.id.action_hide){
            getSupportActionBar().hide();

        }
        return false;
    }
}