package org.example;

public class Miembro {
    private String cedula;
    private String nombre;
    private String apellido;
    private double cuotaBase;
    private int asistencias;

    public Miembro(String cedula, String nombre, String apellido, double cuotaBase) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuotaBase = cuotaBase;
        this.asistencias = 0;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getCuotaBase() {
        return cuotaBase;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void registrarAsistencia() {
        this.asistencias++;
    }

    public double calcularCuota() {
        return cuotaBase;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s, Cuota: $%.2f, Asistencias: %d",
                cedula, nombre, apellido, calcularCuota(), asistencias);
    }
}