package com.tt1.test.unitarios;

import com.tt1.test.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {
    private ToDo tarea;

    @BeforeEach
    void setUp() {
        tarea = new ToDo("Tarea 1", LocalDate.now().plusDays(3));
    }

    @Test
    void testNombre() {
        String nombreEsperado = "Comprar leche";
        tarea.setNombre(nombreEsperado);
        assertEquals(nombreEsperado, tarea.getNombre(), "El nombre de la tarea debe coincidir");
    }

    @Test
    void testDescripcion(){
        String descripcion = "Se recomienda hacerlo antes de la tarea 2";
        tarea.setDescripcion(descripcion);
        assertEquals(descripcion, tarea.getDescripcion(), "La descripción de la tarea debe coincidir");
    }
    @Test
    void testFechaLimite() {
        LocalDate hoy = LocalDate.now();
        tarea.setFechaLimite(hoy);
        assertEquals(hoy, tarea.getFechaLimite(), "La fecha límite debe coincidir");
    }

    @Test
    void testEstadoInicialNoCompletado() {
        assertFalse(tarea.isCompletado(), "Una tarea nueva debe empezar como no completada");
    }

    @Test
    void testCambiarEstadoACompletado() {
        tarea.setCompletado(true);
        assertTrue(tarea.isCompletado(), "El estado debe cambiar a completado");
    }
}
