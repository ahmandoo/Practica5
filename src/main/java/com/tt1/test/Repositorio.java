package com.tt1.test;

import java.util.List;

/**
 * Implementación de la capa de acceso a datos que media entre la lógica de negocio y la persistencia.
 */
public class Repositorio implements IRepositorio{
    private final IDB db;

    /**
     * Construye el repositorio vinculándolo a una implementación específica de base de datos.
     * @param db La base de datos que se utilizará para las operaciones.
     */
    public Repositorio(IDB db){
        this.db= db;
    }

    /**
     * Busca una tarea específica por su nombre.
     * @param nombre El nombre de la tarea a buscar.
     * @return El objeto ToDo encontrado.
     */
    @Override
    public ToDo buscarPorNombre(String nombre) {
        return db.obtenerPorNombre(nombre);
    }

    /**
     * Localiza una tarea por su nombre y actualiza su estado a completado en la base de datos.
     * @param nombre El nombre de la tarea que se desea marcar como finalizada.
     */
    @Override
    public void marcarComoCompletado(String nombre) {
        db.obtenerPorNombre(nombre).setCompletado(true);
    }

    /**
     * Registra una nueva tarea.
     * @param todo La instancia de ToDo que se desea guardar.
     */
    @Override
    public void guardarNuevoToDo(ToDo todo) {
        db.guardarToDo(todo);
    }

    /**
     * Guarda una dirección de correo electrónico en la agenda de persistencia.
     * @param email La dirección de correo electrónico del contacto.
     */
    @Override
    public void guardarEmail(String email) {
        db.addEmail(email);
    }

    /**
     * Recupera el listado de todas las tareas existentes en el almacenamiento.
     * @return Lista de todos los objetos ToDo.
     */
    @Override
    public List<ToDo> obtenerTareas() {
        return db.getToDos();
    }

    /**
     * Recupera la lista de todos los correos electrónicos de la agenda.
     * @return Lista de correos electrónicos registrados.
     */
    @Override
    public List<String> obtenerEmails() {
        return db.getAgenda();
    }
}