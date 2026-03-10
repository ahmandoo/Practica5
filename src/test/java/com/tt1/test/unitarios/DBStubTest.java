package com.tt1.test.unitarios;

import com.tt1.test.DBStub;
import com.tt1.test.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBStubTest {
    private DBStub db;

    @BeforeEach
    void setUp() {
        db = new DBStub();
    }

    @Test
    void testGuardarYObtenerTareas() {
        ToDo tarea = new ToDo("Tirar la basura", null);

        db.guardarToDo(tarea);
        List<ToDo> lista = db.getToDos();

        assertTrue(lista.contains(tarea), "La tarea debería estar en la lista");
    }

    @Test
    void testEliminarTarea() {
        ToDo tarea = new ToDo("Tarea 1", null);
        db.guardarToDo(tarea);

        db.removeToDo(tarea);

        assertEquals(0, db.getToDos().size(), "La lista debería estar vacía tras eliminar la tarea");
    }
    @Test
    void testBuscarToDoPorNombre() {
        ToDo tarea = new ToDo("Tirar la basura", null);
        db.guardarToDo(tarea);

        ToDo encontrada = db.obtenerPorNombre("Tirar la basura");

        assertNotNull(encontrada);
        assertEquals("Tirar la basura", encontrada.getNombre());
    }

    @Test
    void testActualizarTareaExistente() {
        ToDo t1 = new ToDo("Tarea 1", null);
        db.guardarToDo(t1);

        t1.setCompletado(true);
        db.actualizarToDo(t1);

        assertTrue(db.obtenerPorNombre("Tarea 1").isCompletado(),"La tarea en la DB debería reflejar el cambio a completado");
    }

    @Test
    void testGestionDeEmails() {
        String email = "usuario@gmail.com";

        db.addEmail(email);
        List<String> agenda = db.getAgenda();

        assertTrue(agenda.contains(email), "El email debería estar en la agenda");
    }
}
