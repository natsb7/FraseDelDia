package com.nataliasep.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private boolean gameover = false;
    private String palabraGuiones;
    private String palabraSecreta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ahorcado_layout);

        ImageView ivAhorcado = findViewById(R.id.ivAhorcado);
        TextView tvIntentos = findViewById(R.id.tvIntentos);
        TextView tvPalabra = findViewById(R.id.tvPalabraSecreta);
        EditText edLetra = findViewById(R.id.editTextText);
        TextView tvLetrasIntroducidas = findViewById(R.id.tvLetrasIntroducidas);
        Button bJugar = findViewById(R.id.bJugar);
        TextView tvPalabrita = findViewById(R.id.tvPalabrita);


        Game game = new Game();
        palabraSecreta = game.generarPalabra(this);
        palabraGuiones = game.toGuiones(palabraSecreta);
        tvPalabra.setText(game.toEspacios(palabraGuiones));
        tvPalabrita.setText(palabraSecreta);


        bJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameover == false){
                    if(game.getIntentos() > 0){
                        String letra = edLetra.getText().toString();
                        boolean letraEncontrada = false;
                        String palabraGuiones = game.comprobarLetra(letra);
                        if(palabraGuiones != null){
                            letraEncontrada = true;
                            tvPalabra.setText(game.toEspacios(palabraGuiones));
                        }
                        int indice = game.comprobarAcierto(palabraGuiones);
                        if(indice == -1){
                            gameover = true;
                            Toast.makeText(MainActivity.this, "¡Has ganado! Pulsar el boton 'Jugar' para empezar", Toast.LENGTH_SHORT).show();
                        }
                        edLetra.setText("");
                        if(!letraEncontrada){
                            game.registrarFallo(letra);
                            tvIntentos.setText(String.valueOf(game.getIntentos()));
                            tvLetrasIntroducidas.setText(game.getCadenaLetras());
                            String fotoName = "hangman_" + game.getIntentos();
                            int resID = getResources().getIdentifier(fotoName, "drawable", getPackageName());
                            ivAhorcado.setImageResource(resID);
                        }
                    }if(game.getIntentos() == 0){
                        //Has perdido!
                        Toast.makeText(MainActivity.this, "¡Has perdido! Pulsar el boton 'Jugar' para empezar", Toast.LENGTH_SHORT).show();
                        tvPalabra.setText(palabraSecreta);
                        ivAhorcado.setImageResource(R.drawable.hangman_0);
                        gameover = true;
                    }
                }else{
                    tvIntentos.setText(String.valueOf(game.getIntentos()));
                    tvLetrasIntroducidas.setText("");
                    gameover = false;


                    palabraSecreta = game.generarPalabra(MainActivity.this);
                    palabraGuiones = game.toGuiones(palabraSecreta);
                    tvPalabra.setText(game.toEspacios(palabraGuiones));
                    tvPalabrita.setText(palabraSecreta);


                    ivAhorcado.setImageResource(R.drawable.hangman_6);
                }
            }
        });
    }
}