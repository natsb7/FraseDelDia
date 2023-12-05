package com.nataliasep.adivinanumero;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int intentos = 3;
    private boolean gameover = false;
    private int numAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Creando la pantalla");
        TextView tvIntentos = findViewById(R.id.tvIntentos);
        TextView tvNum = findViewById(R.id.tvNum);
        TextView tvPistaResult = findViewById(R.id.tvPistaResultado);

        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        Button b7 = findViewById(R.id.b7);
        Button b8 = findViewById(R.id.b8);
        Button b9 = findViewById(R.id.b9);
        Button b10 = findViewById(R.id.b10);
        numAleatorio = Numeros.numAleatorio();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameover == false) {
                    if (intentos > 0) {
                        Button botonElegido = (Button) view;
                        //texto del numero del boton e int
                        String numeroBoton = botonElegido.getText().toString();
                        int numBoton = Integer.parseInt(numeroBoton);
                        //int del numero a adivinar y su texto
                        String numero = String.valueOf(numAleatorio);
                        intentos--;
                        if (numeroBoton.equals(numero)) {
                            tvNum.setText(numero);
                            tvPistaResult.setText("Has ganado! :) Pulsa cualquier botón para seguir jugando");
                            gameover = true;
                        } else {
                            tvIntentos.setText("Intentos restantes: " + intentos);
                            botonElegido.setEnabled(false);
                            if (numAleatorio > numBoton) {
                                tvPistaResult.setText("El número es mayor");
                            } else {
                                tvPistaResult.setText("El número es menor");
                            }
                        }

                    }if (intentos == 0) {
                        tvIntentos.setText("Intentos restantes: "+ intentos);
                        tvNum.setText(String.valueOf(numAleatorio));
                        tvNum.setTextColor(Color.RED);
                        tvPistaResult.setText("Has agotado todos los intentos, pulsa para cualquier botón para volver a empezar");
                        gameover = true;
                    }
                } else {
                    empezarNuevo();
                }

            }

            private void empezarNuevo() {
                tvNum.setText("?");
                intentos = 3;
                gameover = false;
                tvNum.setTextColor(Color.BLUE);
                tvIntentos.setText("Intentos restantes: " + intentos);
                tvPistaResult.setText("");
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);
                b4.setEnabled(true);
                b5.setEnabled(true);
                b6.setEnabled(true);
                b7.setEnabled(true);
                b8.setEnabled(true);
                b9.setEnabled(true);
                b10.setEnabled(true);
                numAleatorio = Numeros.numAleatorio();

            }
        };
        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);
        b4.setOnClickListener(listener);
        b5.setOnClickListener(listener);
        b6.setOnClickListener(listener);
        b7.setOnClickListener(listener);
        b8.setOnClickListener(listener);
        b9.setOnClickListener(listener);
        b10.setOnClickListener(listener);
    }


}