package com.tt1.test.unitarios;

import com.tt1.test.MailerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MailerStubTest {
    private MailerStub mailer;

    @BeforeEach
    void setUp() {
        mailer = new MailerStub();
    }

    @Test
    void testEnvioDeCorreoExitoso() {
        String destino = "usuario@gmail.com";
        String mensaje = "Tu tarea ha sido finalizada";

        boolean resultado = mailer.enviarCorreo(destino, mensaje);

        assertTrue(resultado, "El mailer debería devolver true tras un envío exitoso");
    }
}
