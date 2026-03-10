package com.tt1.test.unitarios;

import com.tt1.test.Repositorio;
import com.tt1.test.ToDo;
import com.tt1.test.mocks.DBStubMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioUnitTest {
    private Repositorio repo;
    private DBStubMock dbMock;

    @BeforeEach
    void setUp() {
        dbMock = new DBStubMock();
        repo = new Repositorio(this.dbMock);
    }
    @Test
    void testGuardarNuevoToDo() {
        ToDo tarea = new ToDo("Estudiar TDD", null);

        repo.guardarNuevoToDo(tarea);

        ToDo recuperada = dbMock.obtenerPorNombre("Estudiar TDD");
        assertNotNull(recuperada, "El repositorio debería haber guardado la tarea en la DB");
        assertEquals("Estudiar TDD", recuperada.getNombre());
    }

    @Test
    void testBuscarPorNombre() {
        ToDo tarea = new ToDo("Lavar platos", null);
        dbMock.guardarToDo(tarea);

        ToDo encontrada = repo.buscarPorNombre("Lavar platos");

        assertNotNull(encontrada);
        assertEquals("Lavar platos", encontrada.getNombre());
    }

    @Test
    void testMarcarComoCompletado() {
        ToDo tarea = new ToDo("Ejercicio", null);
        dbMock.guardarToDo(tarea);

        repo.marcarComoCompletado("Ejercicio");

        assertTrue(dbMock.obtenerPorNombre("Ejercicio").isCompletado(),
            "La tarea debería estar marcada como completada en la DB");
    }

    @Test
    void testGuardarEmail() {
        String email = "contacto@gmail.com";

        repo.guardarEmail(email);

        assertTrue(dbMock.getAgenda().contains(email), "El email debería haberse guardado en la DB");
    }

    @Test
    void testObtenerTareas(){
        ToDo tarea = new ToDo("Ejercicio", null);
        ToDo tarea2 = new ToDo("Ejercicio 2", null);
        dbMock.guardarToDo(tarea);
        dbMock.guardarToDo(tarea2);

        List<ToDo> tareas = repo.obtenerTareas();

        assertEquals(2, tareas.size(), "Debería haber 2 tareas en la DB");
    }

    @Test
    void testObtenerEmails(){
        String email = "usuario978@gmail.com";
        String email2 = "usuario222@gmail.com";
        dbMock.addEmail(email);
        dbMock.addEmail(email2);

        List<String> agenda = repo.obtenerEmails();

        assertEquals(2, agenda.size(), "Debería haber 2 emails guardados en la agenda");
    }
}
