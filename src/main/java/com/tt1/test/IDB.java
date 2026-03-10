package com.tt1.test;

import java.util.List;
/**
 * Interfaz que define las operaciones de acceso a datos para tareas y agenda.
 */
public interface IDB {
    /** Guarda un objeto ToDo en la base de datos. */
    void guardarToDo(ToDo tarea);
    /** Recupera una tarea específica filtrando por su nombre. */
    ToDo obtenerPorNombre(String nombre);
    /** Devuelve la lista completa de tareas registradas. */
    List<ToDo> getToDos();
    /** Actualiza la información de una tarea ya existente. */
    void actualizarToDo(ToDo tarea);
    /** Elimina una tarea del registro de la base de datos. */
    void removeToDo(ToDo tarea);
    /** Registra una nueva dirección de correo en la base de datos. */
    void addEmail(String email);
    /** Recupera todas las direcciones de correo almacenadas en la agenda. */
    List<String> getAgenda();
}
