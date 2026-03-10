package com.tt1.test;

/**
 * Interfaz para el servicio de notificaciones por correo electrónico.
 */
public interface IMailer {
    /** Envía un mensaje a una dirección específica y devuelve el estado del envío. */
    boolean enviarCorreo(String direccion, String mensaje);
}
