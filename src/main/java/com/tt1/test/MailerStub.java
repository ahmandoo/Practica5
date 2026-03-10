package com.tt1.test;

/**
 * Implementación del servicio de mensajería para la gestión de notificaciones del sistema.
 */
public class MailerStub implements IMailer {

    /**
     * Procesa el envío de un correo electrónico y muestra los detalles del destinatario y mensaje.
     * @param direccion La dirección de correo electrónico del destinatario.
     * @param mensaje El contenido textual que se desea enviar.
     * @return true tras completar el proceso de notificación.
     */
    @Override
    public boolean enviarCorreo(String direccion, String mensaje) {
        System.out.println("Destinatario: "+ direccion);
        System.out.println("Mensaje: \n\t"+ mensaje);
        return true;
    }
}