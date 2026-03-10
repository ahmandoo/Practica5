package com.tt1.test.integración;

import com.tt1.test.DBStub;
import com.tt1.test.Repositorio;
import com.tt1.test.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepositorioIntTest {
    private Repositorio repo;
    private DBStub db;

    @BeforeEach
    void setUp() {
        db = new DBStub();
        repo = new Repositorio(this.db);
    }

    @Test
    void testGuardarNuevoToDo() {
        ToDo tarea = new ToDo("Estudiar TDD", null);

        repo.guardarNuevoToDo(tarea);

        ToDo recuperada = db.obtenerPorNombre("Estudiar TDD");
        assertNotNull(recuperada, "El repositorio debería haber guardado la tarea en la DB");
        assertEquals("Estudiar TDD", recuperada.getNombre());
    }

    @Test
    void testBuscarPorNombre() {
        ToDo tarea = new ToDo("Lavar platos", null);
        db.guardarToDo(tarea);

        ToDo encontrada = repo.buscarPorNombre("Lavar platos");

        assertNotNull(encontrada);
        assertEquals("Lavar platos", encontrada.getNombre());
    }

    @Test
    void testMarcarComoCompletado() {
        ToDo tarea = new ToDo("Ejercicio", null);
        db.guardarToDo(tarea);

        repo.marcarComoCompletado("Ejercicio");

        assertTrue(db.obtenerPorNombre("Ejercicio").isCompletado(),
            "La tarea debería estar marcada como completada en la DB");
    }

    @Test
    void testGuardarEmail() {
        String email = "contacto@gmail.com";

        repo.guardarEmail(email);

        assertTrue(db.getAgenda().contains(email), "El email debería haberse guardado en la DB");
    }

    @Test
    void testObtenerTareas(){
        ToDo tarea = new ToDo("Ejercicio", null);
        ToDo tarea2 = new ToDo("Ejercicio 2", null);
        db.guardarToDo(tarea);
        db.guardarToDo(tarea2);

        List<ToDo> tareas = repo.obtenerTareas();

        assertEquals(2, tareas.size(), "Debería haber 2 tareas en la DB");
    }

    @Test
    void testObtenerEmails(){
        String email = "usuario978@gmail.com";
        String email2 = "usuario222@gmail.com";
        db.addEmail(email);
        db.addEmail(email2);

        List<String> agenda = repo.obtenerEmails();

        assertEquals(2, agenda.size(), "Debería haber 2 emails guardados en la agenda");
    }
}
