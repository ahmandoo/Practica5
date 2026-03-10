package com.tt1.test;

import java.util.List;

/**
 * Interfaz que define las operaciones de persistencia para tareas y contactos.
 */
public interface IRepositorio {
    /** Busca una tarea por su nombre exacto. */
    ToDo buscarPorNombre(String nombre);
    /** Cambia el estado de una tarea a completado. */
    void marcarComoCompletado(String nombre);
    /** Guarda una nueva instancia de ToDo en el almacenamiento. */
    void guardarNuevoToDo(ToDo todo);
    /** Añade un email a la lista de contactos. */
    void guardarEmail(String email);
    /** Recupera todas las tareas almacenadas. */
    List<ToDo> obtenerTareas();
    /** Recupera todos los emails de la agenda. */
    List<String> obtenerEmails();
}
