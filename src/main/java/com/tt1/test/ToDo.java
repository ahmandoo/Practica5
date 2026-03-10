package com.tt1.test;
import java.time.LocalDate;

/**
 * Clase que representa una tarea individual en el sistema.
 * Contiene la información básica, su descripción, plazo de entrega y estado.
 */
public class ToDo {
    private String nombre;
    private String descripcion;
    private LocalDate fechaLimite;
    private boolean completado;

    /**
     * Constructor para crear una nueva tarea.
     * @param nombre El nombre identificativo de la tarea.
     * @param fechaLimite La fecha máxima para completar la tarea.
     */
    public ToDo(String nombre, LocalDate fechaLimite) {
        this.nombre=nombre;
        descripcion="";
        this.fechaLimite=fechaLimite;
        completado=false;
    }

    /**
     * Obtiene el nombre de la tarea.
     * @return El nombre de la tarea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre de la tarea.
     * @param nombre El nuevo nombre de la tarea.
     */
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    /**
     * Obtiene la descripción de la tarea.
     * @return La descripción actual.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una descripción para la tarea.
     * @param descripcion Texto descriptivo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion=descripcion;
    }

    /**
     * Obtiene la fecha límite de realización.
     * @return Objeto LocalDate con la fecha límite.
     */
    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    /**
     * Modifica la fecha límite de la tarea.
     * @param fechaLimite Nueva fecha límite.
     */
    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite=fechaLimite;
    }

    /**
     * Indica si la tarea ha sido finalizada.
     * @return true si está completada, false en caso contrario.
     */
    public boolean isCompletado() {
        return completado;
    }

    /**
     * Cambia el estado de finalización de la tarea.
     * @param completado Nuevo estado de la tarea.
     */
    public void setCompletado(boolean completado) {
        this.completado=completado;
    }
}