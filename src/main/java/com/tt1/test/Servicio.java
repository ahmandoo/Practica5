package com.tt1.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de servicio que coordina las operaciones entre el repositorio de datos y el sistema de mensajería.
 * Implementa las reglas de negocio para la creación, consulta y finalización de tareas.
 */
public class Servicio {
    private IMailer mailer;
    private IRepositorio repo;

    /**
     * Constructor que inyecta las dependencias necesarias para el funcionamiento del servicio.
     * @param repositorio Implementación del almacenamiento de datos.
     * @param mailer Implementación del sistema de envío de correos.
     */
    public Servicio(IRepositorio repositorio, IMailer mailer) {
        this.repo = repositorio;
        this.mailer = mailer;
    }

    /**
     * Crea una nueva tarea tras validar que el nombre no sea vacío y la fecha sea futura.
     * Antes de crear, verifica si existen tareas caducadas para alertar.
     * @param nombre Nombre de la tarea.
     * @param fechaLimite Fecha en la que debe estar terminada.
     */
    public void crearToDo(String nombre, LocalDate fechaLimite) {
        alertarTareas();
        if(nombre != null && !nombre.isEmpty() && fechaLimite.isAfter(LocalDate.now())){
            repo.guardarNuevoToDo(new ToDo(nombre, fechaLimite));
        }
    }

    /**
     * Registra un correo electrónico en la agenda si cumple con el formato estándar.
     * El formato requerido es nombre@dominio.com.
     * @param email Dirección de correo a validar y guardar.
     */
    public void agregarEmail(String email) {
        if (email != null && !email.isBlank()) {
            String emailLimpio = email.trim();
            if (emailLimpio.contains("@") && !emailLimpio.startsWith("@") && emailLimpio.endsWith(".com")) {
                repo.guardarEmail(emailLimpio);
            }
        }
    }

    /**
     * Marca una tarea existente como completada utilizando su nombre.
     * @param nombre El nombre de la tarea a finalizar.
     */
    public void finalizarTarea(String nombre) {
        repo.marcarComoCompletado(nombre);
    }

    /**
     * Devuelve la lista de todas las tareas que no han sido marcadas como completadas.
     * Realiza una comprobación previa de tareas caducadas.
     * @return Lista de objetos ToDo pendientes.
     */
    public List<ToDo> consultarPendientes(){
        alertarTareas();
        List<ToDo> pendientes = new ArrayList<>();
        for(ToDo tarea: repo.obtenerTareas()){
            if(!tarea.isCompletado()){
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }

    /**
     * Método interno que comprueba si hay tareas cuya fecha límite ha pasado.
     * Envía un correo de alerta a todas las direcciones de la agenda por cada tarea caducada.
     */
    private void alertarTareas(){
        for(ToDo tarea: repo.obtenerTareas()){
            if(tarea.getFechaLimite().isBefore(LocalDate.now())){
                for(String email: repo.obtenerEmails()){
                    mailer.enviarCorreo(email, "La tarea "+tarea.getNombre()+" ha caducado su fecha de realización");
                }
            }
        }
    }
}
