package com.nataliasep.adivinanumero;

import java.util.Random;

public class Numeros {
    public static int numAleatorio(){
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
