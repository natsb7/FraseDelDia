package com.nataliasep.calculadoradiseo;

public class analizarString {

    public String result(String calculo){
        int op = getSymbol(calculo);
        String result = String.valueOf(calculate(op, calculo));
        return result;
    }
    public double calculate(int op, String calculo){
        char symbol = calculo.charAt(op);
        double num1 =  Double.parseDouble(calculo.substring(0, op));
        double num2 = Double.parseDouble(calculo.substring(op+1));

        switch (symbol) {
            case '+':
                return sumar(num1, num2);
            case '-':
                return restar(num1, num2);
            case '*':
                return multiplicar(num1, num2);
            case '/':
                return dividir(num1, num2);
            case '%':
                return porCien(num1, num2);
        }
        return -1;
    }
    public double sumar(double a, double b){
        return a + b;
    }
    public double restar(double a, double b){
        return a - b;
    }

    public double multiplicar(double a, double b){
        return a * b;
    }

    public double dividir(double a, double b){
        return a / b;
    }

    public double porCien(double a, double b){
        return dividir(multiplicar(a,b),100);
    }

    public int getSymbol(String calculo){
        int op = calculo.indexOf("+");
        if (op != -1 ) {
            return op;
        }else {
            op = calculo.indexOf("-");
            if (op != -1) {
                return op;
            }else {
                op = calculo.indexOf("*");
                if (op !=  -1) {
                    return op;
                }else {
                    op = calculo.indexOf("/");
                    if(op != -1){
                        return op;
                    }else{
                        op = calculo.indexOf("%");
                    }
                }
            }
        }
        return op;
    }
}


