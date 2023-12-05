package com.nataliasep.notasalumnos;

import android.os.Build;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Alumno implements Serializable {

    private final int nia;
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final String fchNacimiento;
    private final String email;
    private final Nota[] notas;

    public Alumno(int nia, String nombre, String apellido1, String apellido2, String fchNacimiento, String email, Nota[] notas) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fchNacimiento = fchNacimiento;
        this.email = email;
        this.notas = notas;
    }

    public int getNia() {
        return nia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getFchNacimiento() {
        return fchNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public Nota[] getNotas() {
        return notas;
    }

    public int getEdad(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Cambia el formato según tu necesidad
        Date birthDate;
        try {
            birthDate = sdf.parse(fchNacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Manejo de error
        }

        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);

        Calendar currentCalendar = Calendar.getInstance();
        int age = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        // Comprobamos si ya ha pasado el cumpleaños de este año
        if (currentCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                (currentCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                        currentCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
            age--; // No ha llegado el cumpleaños de este año
        }

        return age;
    }
}
