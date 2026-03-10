package com.tt1.test.mocks;
import com.tt1.test.IMailer;

public class MailerMock implements IMailer {
    public boolean esEnviado = false;
    @Override
    public boolean enviarCorreo(String direccion, String mensaje) {
        this.esEnviado = true;
        return true;
    }
}
