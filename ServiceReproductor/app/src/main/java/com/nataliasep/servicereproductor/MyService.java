package com.nataliasep.servicereproductor;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;

public class MyService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener,
MediaPlayer.OnErrorListener{
    private MediaPlayer mediaPlayer;
    MediaMetadataRetriever retriever;
    private final MyBinder myBinder = new MyBinder();

    private static final String ACTION = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Uri uri = null;
        MediaPlayer mediaPlayer = new MediaPlayer();
        retriever = new MediaMetadataRetriever();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public String extractMetadata(int keyCode) {
        return retriever.extractMetadata(keyCode);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mediaPlayer.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //interfaz q se invoca cuando la canción este lista para reproducirse
        mediaPlayer.start();


    }

    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
        //este metodo me dice si el reproductor está reproducciendose o no
    }

    public class MyBinder extends Binder {
        public MyService getMyService(){
            return MyService.this;
        }
    }
}
