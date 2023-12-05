package com.nataliasep.ejerciciointents;

import static com.nataliasep.ejerciciointents.MainActivity.Permission.PHONE_CALL;
import static com.nataliasep.ejerciciointents.MainActivity.Permission.TAKE_PHOTO;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public enum Permission{
        PHONE_CALL, TAKE_PHOTO
    }

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bAbrirWeb = findViewById(R.id.bAbriWeb);
        Button bListaPaises = findViewById(R.id.bListaPaises);
        Button bLlamadaTelf = findViewById(R.id.bLlamadaTelf);
        Button bGoogleMaps = findViewById(R.id.bGoogleMaps);
        Button bHacerFoto = findViewById(R.id.bHacerFoto);
        Button bEnviarCorreo = findViewById(R.id.bEnviarCorreo);

        bAbrirWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebpage("developer.android.com");
            }
        });

        bListaPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String pkg = "com.nataliasep.reclyclerviewpaises";
                intent.setComponent(new ComponentName(pkg, pkg + "." + "MainActivity"));

                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException anfe){
                    Toast.makeText(MainActivity.this, "Tu aplicación de paises no está instalada", Toast.LENGTH_SHORT).show();

                }

            }
        });

        bLlamadaTelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall("965900001");
            }
        });

        bGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarUbicacion();

            }
        });

        bEnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail("pepito@palotes.com", "Hola", "Caracola");

            }
        });

        ImageView ivFoto = findViewById(R.id.ivFoto);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                         if(o.getResultCode() == RESULT_OK){
                             Bundle bundle = o.getData().getExtras();
                             Bitmap bitmap = (Bitmap) bundle.get("data");
                             ivFoto.setImageBitmap(bitmap);
                         }
                         //Toast.makeText(MainActivity.this, "Datos: "+ o.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

        bHacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerfoto();

            }
        });

    }

    public void hacerfoto() {
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activityResultLauncher.launch(intent);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO.ordinal() );
        }


    }

    public void mostrarUbicacion() {
        double latitud = 35.681278;
        double longitud = 140.026639;
        int zoomLevel = 17;
        String uriString = "geo:" + latitud + "," + longitud + "?z=" + zoomLevel;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void sendMail(String to, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

    public void openWebpage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + url));
        startActivity(intent);
    }

    public void phoneCall (String tel){
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +tel));
            startActivity(intent);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL.ordinal() );
        }

    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PHONE_CALL.ordinal()){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                phoneCall("956900001");
            }
        }else if(requestCode == TAKE_PHOTO.ordinal()){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                hacerfoto();
            }

        }
    }
}