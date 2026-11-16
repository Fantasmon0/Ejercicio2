package org.example;

import  java.util.Collection;
import  java.util.HashMap;
public class Gimnasio {
    private HashMap<String, Miembro> miembros;

    public Gimnasio() {
        this.miembros = new HashMap<>();
    }
    public boolean existeCedula(String cedula){
        return miembros.containsKey(cedula);
    }
    public boolean registrarMiembro(Miembro miembro){
        if(miembros.containsKey(miembro.getCedula())){
            return false;

        }
        miembros.put(miembro.getCedula(), miembro);
        return true;
    }
    public Miembro buscar(String cedula) {
        return miembros.get(cedula);
    }
    public Miembro buscar(String nombre, String apellido){
        for(Miembro m : miembros.values()){
            if (m.getNombre().equalsIgnoreCase(nombre) && m.getApellido().equalsIgnoreCase(apellido)){
                return m;
            }
        }
        return null;
    }
    public boolean estaVacio(){
        return miembros.isEmpty();
    }
    public Collection<Miembro> getMiembros() {
        return miembros.values();
    }

    public void mostrarEstadisticas() {
        if (miembros.isEmpty()) {
            System.out.println("Error: No hay miembros registrados.");
            return;
        }

        Miembro mayorPagador = null;
        double ingresoTotal = 0.0;
        int totalAsistencias = 0;

        for (Miembro m : miembros.values()) {
            double cuotaActual = m.calcularCuota();
            ingresoTotal += cuotaActual;
            totalAsistencias += m.getAsistencias();

            if (mayorPagador == null || cuotaActual > mayorPagador.calcularCuota()) {
                mayorPagador = m;
            }
        }

        double promedioAsistencias = (double) totalAsistencias / miembros.size();

        System.out.printf("Miembro que más paga: %s %s ($%.2f)%n", mayorPagador.getNombre(), mayorPagador.getApellido(), mayorPagador.calcularCuota());
        System.out.printf("Ingreso mensual total: $%.2f%n", ingresoTotal);
        System.out.printf("Promedio de asistencias por miembro: %.2f%n", promedioAsistencias);
    }
}
