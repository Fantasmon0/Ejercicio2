package org.example;

import java.util.Scanner;

public class Main {
    private static final Gimnasio gimnasio = new Gimnasio();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ GYMESFOT ---");
            System.out.println("1. Registrar miembro básico");
            System.out.println("2. Registrar miembro premium");
            System.out.println("3. Registrar asistencia");
            System.out.println("4. Buscar miembro");
            System.out.println("5. Mostrar miembros");
            System.out.println("6. Mostrar estadísticas");
            System.out.println("7. Salir");
            System.out.print("Ingrese opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Opción no válida.");
                continue;
            }

            if (opcion == 1) {
                registrarBasico();
            } else if (opcion == 2) {
                registrarPremium();
            } else if (opcion == 3) {
                registrarAsistencia();
            } else if (opcion == 4) {
                buscarMiembro();
            } else if (opcion == 5) {
                mostrarMiembros();
            } else if (opcion == 6) {
                gimnasio.mostrarEstadisticas();
            } else if (opcion == 7) {
                System.out.println("Saliendo del sistema GymESFOT...");
            } else {
                System.out.println("Error: Opción no válida.");
            }
        } while (opcion != 7);
    }

    private static void registrarBasico() {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine().trim();

        if (cedula.isEmpty()) {
            System.out.println("Error: La cédula no puede estar vacía.");
            return;
        }

        if (gimnasio.existeCedula(cedula)) {
            System.out.println("Error: La cédula " + cedula + " ya está registrada.");
            return;
        }

        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine().trim();

            System.out.print("Cuota base: ");
            double cuotaBase = Double.parseDouble(sc.nextLine().trim());
            if (cuotaBase < 0) {
                System.out.println("Error: La cuota base no puede ser negativa.");
                return;
            }

            gimnasio.registrarMiembro(new MiembroBasico(cedula, nombre, apellido, cuotaBase));
            System.out.println("Miembro básico registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
        }
    }

    private static void registrarPremium() {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine().trim();

        if (cedula.isEmpty()) {
            System.out.println("Error: La cédula no puede estar vacía.");
            return;
        }

        if (gimnasio.existeCedula(cedula)) {
            System.out.println("Error: La cédula " + cedula + " ya está registrada.");
            return;
        }

        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine().trim();

            System.out.print("Cuota base: ");
            double cuotaBase = Double.parseDouble(sc.nextLine().trim());
            if (cuotaBase < 0) {
                System.out.println("Error: La cuota base no puede ser negativa.");
                return;
            }

            System.out.print("Sesiones con entrenador: ");
            int sesiones = Integer.parseInt(sc.nextLine().trim());
            if (sesiones < 0) {
                System.out.println("Error: Las sesiones con entrenador no pueden ser negativas.");
                return;
            }

            gimnasio.registrarMiembro(new MiembroPremium(cedula, nombre, apellido, cuotaBase, sesiones));
            System.out.println("Miembro premium registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
        }
    }

    private static void registrarAsistencia() {
        if (gimnasio.estaVacio()) {
            System.out.println("Error: No hay miembros registrados.");
            return;
        }

        System.out.print("Cédula: ");
        String cedula = sc.nextLine().trim();
        Miembro m = gimnasio.buscar(cedula);

        if (m == null) {
            System.out.println("Error: No existe ningún miembro con la cédula " + cedula + ".");
            return;
        }

        m.registrarAsistencia();
        System.out.println("Asistencia registrada. " + m.getNombre() + " " + m.getApellido() +
                " lleva " + m.getAsistencias() + " asistencias.");
    }

    private static void buscarMiembro() {
        if (gimnasio.estaVacio()) {
            System.out.println("Error: No hay miembros registrados.");
            return;
        }

        System.out.println("1. Por cédula   2. Por nombre y apellido");
        System.out.print("Opción: ");
        String subOpcion = sc.nextLine().trim();

        Miembro encontrado = null;
        if (subOpcion.equals("1")) {
            System.out.print("Cédula: ");
            String cedula = sc.nextLine().trim();
            encontrado = gimnasio.buscar(cedula);
        } else if (subOpcion.equals("2")) {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine().trim();
            encontrado = gimnasio.buscar(nombre, apellido);
        } else {
            System.out.println("Error: Opción no válida.");
            return;
        }

        if (encontrado == null) {
            System.out.println("Error: El miembro no existe.");
        } else {
            System.out.println(encontrado);
        }
    }

    private static void mostrarMiembros() {
        if (gimnasio.estaVacio()) {
            System.out.println("Error: No hay miembros registrados.");
            return;
        }

        for (Miembro m : gimnasio.getMiembros()) {
            System.out.println(m);
        }
    }
}