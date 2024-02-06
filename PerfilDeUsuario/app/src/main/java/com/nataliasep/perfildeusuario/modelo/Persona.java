package com.nataliasep.perfildeusuario.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Persona implements Serializable {
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final Calendar fch_nacimiento;
    private final String direccion;
    private final int id;
    private String contrasenya;
    private Empresa empresa;

    public Persona(String nombre, String apellido1, String apellido2, String fch_nacimiento, String direccion, int id, String contrasenya, Empresa empresa) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fch_nacimiento = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            // convierte un String en formato fecha en una fecha real
            Date fecha = sdf.parse(fch_nacimiento);
            assert fecha != null;
            this.fch_nacimiento.setTime(fecha);
            
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.direccion = direccion;
        this.id = id;
        this.contrasenya = contrasenya;
        this.empresa = empresa;
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

    public Calendar getFch_nacimiento() {
        return fch_nacimiento;
    }

    public int getEdad() {
        Calendar currentCalendar = Calendar.getInstance();
        int edad = currentCalendar.get(Calendar.YEAR) - fch_nacimiento.get(Calendar.YEAR);
        // Comprobamos si ya pasó el cumpleaños de este año
        if (currentCalendar.get(Calendar.MONTH) < fch_nacimiento.get(Calendar.MONTH)
                || (currentCalendar.get(Calendar.MONTH) == fch_nacimiento.get(Calendar.MONTH)
                && currentCalendar.get(Calendar.DAY_OF_MONTH) < fch_nacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--; // Porque aún no ha sido su cumpleaños este año
        }
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getId() {
        return id;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String nuevaContrasenya) {
        this.contrasenya = nuevaContrasenya;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getApellidoCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append(apellido1).append(" ");
        sb.append(apellido2);
        return sb.toString();
    }

    public String getFechaNacimientoYEdad() {
        int edad;
        StringBuilder fechaNacimientoYEdad = new StringBuilder();
        Calendar currentCalendar = Calendar.getInstance();
        edad = currentCalendar.get(Calendar.YEAR) - fch_nacimiento.get(Calendar.YEAR);
        // Comprobamos si ya pasó el cumpleaños de este año
        if (currentCalendar.get(Calendar.MONTH) < fch_nacimiento.get(Calendar.MONTH)
                || (currentCalendar.get(Calendar.MONTH) == fch_nacimiento.get(Calendar.MONTH)
                && currentCalendar.get(Calendar.DAY_OF_MONTH) < fch_nacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--; // Porque aún no ha sido su cumpleaños este año
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        String fechaNacimientoString = sdf.format(fch_nacimiento.getTime());
        fechaNacimientoYEdad.append(fechaNacimientoString).append(" (").append(edad).append(")");
        return fechaNacimientoYEdad.toString();
    }
}
