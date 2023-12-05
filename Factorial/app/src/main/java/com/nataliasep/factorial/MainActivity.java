package com.nataliasep.factorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText num = findViewById(R.id.etNum);
        Button calcula = findViewById(R.id.BCalcula);
        TextView resultado = findViewById(R.id.tvResultado);

        calcula.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String number = num.getText().toString();
            int numero = 0;
            try {
                numero = Integer.parseInt(number);
            } catch (Exception e){
                System.err.println("Ha ocurrido un error");
            }
            resultado.setText(factorial(numero));
        }
    });

    }
    public String factorial(int num){
        int factorial = 1;
        for (int i = 1; i<= num; i++){
            factorial = factorial * i;
        }
        return String.valueOf(factorial);
    }
}