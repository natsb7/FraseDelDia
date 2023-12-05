package com.nataliasep.pruebaservicios;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private Thread thread; //hilo que se utilizara para realizar tareas en segundo plano
    private final MyBinder myBinder = new MyBinder(); // objeto tipo myBinder que proporciona un interfaz de enlace

    public MyService() {
    }

    //metodo para verificar si hay alguna actualizacion
    public boolean checkUpdates(){
        return false;
    }

    @Override
    //el siguiente metodo se inicia cuando se lance un Intent desde el método startService()
    public int onStartCommand(Intent intent, int flags, int startId) {
        //ejecutamos tareas en segundo plano
        if (thread == null || !thread.isAlive()){
            //se comprueba que el servicio q se inicia (un hilo en este caso) no sea null o no esté inicializado ya
            thread = new Thread(new Runnable() {
                @Override
                //mensaje q muestra que se inicia el hilo
                public void run() {
                    Log.d(MyService.class.getSimpleName(), "Iniciando HILO");
                }
            });
            thread.start(); //orden de inicio del hilo
        }
        //mensaje q muestra que se inicia el servicio
        Log.d(MyService.class.getSimpleName(), "Iniciando serivicio");
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
        // Devuelve un valor que especifica cómo debe comportarse el servicio en caso de que sea detenido
        // START_STICKY significa que el servicio será reiniciado si es cerrado por el sistema



    }

    @Override
    public IBinder onBind(Intent intent) {
        return  myBinder;
    }
    //metodo q se llama cuando se enlaza un componente al servicio
    //devuelve el objeto myBinder para permitir que los componentes se conecten al servicio
    //este método se invoca cuando se ejecuta el método bindService() desde una Activity, sirve para
    //devolver una referncia de la instancia del servicio a la activity y esta pueda tener acceso a los miembros
    //y métodos del servicio (mi clase myService)

    public class MyBinder extends Binder{
        //clase interna MyBinder
        public MyService getMyService(){
            return MyService.this;
        }
        //devuelve una instancia de myservice (esta misma clase)
    }
}