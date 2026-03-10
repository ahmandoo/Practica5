package com.tt1.test.integración;

import com.tt1.test.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicioIntTest {
    private Servicio servicio;
    private MailerStub mailer;
    private Repositorio repo;

    @BeforeEach
    void setUp() {
        repo = new Repositorio(new DBStub());
        mailer = new MailerStub();
        servicio = new Servicio(repo, mailer);
    }

    @Test
    void testCrearToDo() {
        String nombre = "Estudiar para examen";
        LocalDate fecha = LocalDate.now().plusDays(3);
        String nombre2 = "Estudiar para examen2";
        LocalDate fecha2 = LocalDate.now().minusDays(3);
        String nombre3 = "";
        LocalDate fecha3 = LocalDate.now().plusDays(3);
        String nombre4 = "";
        LocalDate fecha4 = null;

        servicio.crearToDo(nombre, fecha);
        servicio.crearToDo(nombre2, fecha2);
        servicio.crearToDo(nombre3, fecha3);
        servicio.crearToDo(nombre4, fecha4);

        assertEquals(1, repo.obtenerTareas().size(), "No se debe añadir tareas con nombre vacio/nulo o una fecha límite menor que la actual");
    }

    @Test
    void testAgregarEmailAAgenda() {
        String email = "usuario@gmail.com";
        String email2 = "miemail";
        String email3 = "@.com.gmail";
        String email4 = "";
        servicio.agregarEmail(email);
        servicio.agregarEmail(email2);
        servicio.agregarEmail(email3);
        servicio.agregarEmail(email4);

        assertEquals(1, repo.obtenerEmails().size(),"El email debe tener el formato índicado XXX@XX.com");
    }

    @Test
    void testFinalizarTarea() {
        String nombre = "Estudiar para examen";
        LocalDate fecha = LocalDate.now().plusDays(3);
        servicio.crearToDo(nombre, fecha);

        servicio.finalizarTarea(nombre);

        assertTrue(repo.buscarPorNombre(nombre).isCompletado(), "La tarea debe cambiar su estado a finalizada");
    }

    @Test
    void testConsultarPendientes() {
        servicio.crearToDo("Pendiente", LocalDate.now().plusDays(1));
        servicio.crearToDo("Hecha", LocalDate.now().plusDays(1));
        servicio.finalizarTarea("Hecha");

        List<ToDo> pendientes = servicio.consultarPendientes();

        assertEquals(1, pendientes.size(), "Debería haber exactamente una tarea pendiente");
        assertEquals("Pendiente", pendientes.getFirst().getNombre());
    }
}
