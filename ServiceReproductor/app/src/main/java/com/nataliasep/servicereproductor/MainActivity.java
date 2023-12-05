package com.nataliasep.servicereproductor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ICancionListener{
    private RecyclerView recyclerView;
    private CancionAdapter cancionAdapter;
    private ArrayList<Cancion> listaCanciones = new ArrayList<>();
    private ImageView ivFoto;
    private ImageButton bPlayPause, bAnterior, bSiguiente;
    private TextView tvTitulo, tvInicio, tvFinal;
    private SeekBar seekBar;
    private int cancionSeleccionada;
    private MyService myService;

    private enum Action{
        ACTION_PLAY, ACTION_PAUSE, ACTION_STOP, ACTION_NEXT, ACTION_PREVIOUS
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder)service).getMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE); //metodo asincrono
        //cuando esté conectado se iniciará onServiceConnected


        recyclerView = findViewById(R.id.rvListaCanciones);
        recyclerView.setAdapter(cancionAdapter);


        ivFoto = findViewById(R.id.ivImagen);
        bPlayPause = findViewById(R.id.ibPlayPause);
        bAnterior = findViewById(R.id.ibAnterior);
        bSiguiente = findViewById(R.id.ibSiguiente);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvInicio = findViewById(R.id.tvInicio);
        tvFinal = findViewById(R.id.tvFinal);
        seekBar = findViewById(R.id.seekBar);

        String[] nombresCanciones = {"song1", "song2", "song3"};

        for (String nombreCancion : nombresCanciones) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + nombreCancion);
            myService.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, uri);
            String artista = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String titulo = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String año = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR);
            String duracion = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

            Cancion cancion = new Cancion(uri, titulo, artista, año, album, duracion);
            listaCanciones.add(cancion);
        }


        bPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myService.isPlaying()){

                }

            }
        });

        bSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    public Cancion getCancion(){
        if(cancionSeleccionada == -1){
            cancionSeleccionada = 0;
        }
        return listaCanciones.get(cancionSeleccionada);
    }

    public int getCancionSeleccionada(){
        return cancionSeleccionada;
    }

   @Override
   public void onClick(int position){
        cancionSeleccionada = position;
   }
}