package com.nataliasep.pruebaservicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyService myService; //instanciamos la clase myService

    private ServiceConnection serviceConnection = new ServiceConnection() {
        //El objeto serviceConnection se utilizara para gestionar la conexion con el servicio
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //este metodo se llama cuando la conexion con el servicio se establece con éxito
            //cuando nos conectemos a un servicio nos conectaremos al componente y en el binder irán los datos q nos hagan falta
            myService = ((MyService.MyBinder)service).getMyService();
            //se obtiene la instancia del servicio a partir del IBinder proporcionado
            if (myService.checkUpdates()) {
                Log.d(MainActivity.class.getSimpleName(), "Hay actualizaciones");
                //se verifica si hay actualizaciones
            }
            Log.d(MainActivity.class.getSimpleName(), "Conectado al servicio");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
        }
        //metodo llamado cuando la conexión al servicio se rompe, se establece como null al servicio
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                //intent creado para iniciar el servicio
                startService(intent);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                //vinculación al servicio utilizando el serviceConnection q es quien gestiona la conexion al servicio
            }
        });
    }
}