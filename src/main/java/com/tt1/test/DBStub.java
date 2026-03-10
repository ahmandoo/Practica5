package com.tt1.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementación de la interfaz IDB que gestiona el almacenamiento de datos.
 */
public class DBStub implements IDB{
    private final HashMap<String, ToDo> tareas;
    private final List<String> agenda;


    public DBStub(){
        tareas = new HashMap<>();
        agenda = new ArrayList<>();
    }
    /**
     * Almacena una tarea utilizando su nombre como clave.
     * @param tarea El objeto ToDo que se desea persistir.
     */
    @Override
    public void guardarToDo(ToDo tarea) {
        tareas.put(tarea.getNombre(), tarea);
    }

    /**
     * Recupera una tarea del almacenamiento basándose en su nombre.
     * @param nombre El nombre identificativo de la tarea.
     * @return El objeto ToDo correspondiente o null si no existe el registro.
     */
    @Override
    public ToDo obtenerPorNombre(String nombre) {
        return tareas.get(nombre);
    }

    /**
     * Proporciona la lista completa de todas las tareas almacenadas actualmente.
     * @return Una lista con todos los objetos ToDo registrados.
     */
    @Override
    public List<ToDo> getToDos() {
        return new ArrayList<>(tareas.values());
    }

    /**
     * Actualiza la información de una tarea existente reemplazándola por la nueva instancia.
     * @param tarea El objeto ToDo con los datos actualizados.
     */
    @Override
    public void actualizarToDo(ToDo tarea) {
        tareas.replace(tarea.getNombre(), tarea);
    }

    /**
     * Elimina una tarea específica de los registros de memoria.
     * @param tarea La tarea que se desea eliminar.
     */
    @Override
    public void removeToDo(ToDo tarea) {
        tareas.remove(tarea.getNombre());
    }

    /**
     * Añade una dirección de correo electrónico a la lista de contactos de la agenda.
     * @param email La dirección de correo a registrar.
     */
    @Override
    public void addEmail(String email) {
        agenda.add(email);
    }

    /**
     * Recupera la lista completa de correos electrónicos registrados en la agenda.
     * @return Una lista de cadenas de texto con los correos electrónicos.
     */
    @Override
    public List<String> getAgenda() {
        return agenda;
    }
}