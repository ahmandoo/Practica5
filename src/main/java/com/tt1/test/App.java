package com.tt1.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Servicio servicio = new Servicio(new Repositorio(new DBStub()), new MailerStub());
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("*******GESTOR DE TAREAS *******");
        while (!salir) {
            System.out.println("\nOPERACIONES DISPONIBLES:");
            System.out.println("1. Crear nueva tarea");
            System.out.println("2. Agregar email a la agenda");
            System.out.println("3. Consultar tareas pendientes");
            System.out.println("4. Finalizar una tarea");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    System.out.print("Nombre de la tarea: ");
                    String nombreTarea = sc.nextLine();
                    System.out.print("¿En cuántos días vence? (ej. 3): ");
                    try {
                        int dias = Integer.parseInt(sc.nextLine());
                        servicio.crearToDo(nombreTarea, LocalDate.now().plusDays(dias));
                        System.out.println("Operación realizada.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un número válido de días.");
                    }
                    break;

                case "2":
                    System.out.print("Ingrese el email (debe ser XX@XX.com): ");
                    String email = sc.nextLine();
                    servicio.agregarEmail(email);
                    System.out.println("Operación realizada.");
                    break;

                case "3":
                    System.out.println("--- LISTA DE PENDIENTES ---");
                    List<ToDo> pendientes = servicio.consultarPendientes();
                    if (pendientes.isEmpty()) {
                        System.out.println("No hay tareas pendientes.");
                    } else {
                        for (ToDo t : pendientes) {
                            System.out.println("- " + t.getNombre() + " (Vence: " + t.getFechaLimite() + ")");
                        }
                    }
                    break;

                case "4":
                    System.out.print("Nombre de la tarea a finalizar: ");
                    String nombreFin = sc.nextLine();
                    servicio.finalizarTarea(nombreFin);
                    System.out.println("Tarea marcada como completada.");
                    break;

                case "5":
                    salir = true;
                    System.out.println("Cerrando aplicación...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        sc.close();
    }
}
