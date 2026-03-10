package com.tt1.test.mocks;

import com.tt1.test.IDB;
import com.tt1.test.ToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBStubMock implements IDB {
    private Map<String, ToDo> tareas = new HashMap<>();
    private List<String> agenda = new ArrayList<>();

    @Override
    public void guardarToDo(ToDo tarea) {
        tareas.put(tarea.getNombre(), tarea);
    }

    @Override
    public ToDo obtenerPorNombre(String nombre) {
        ToDo tarea = tareas.get(nombre);
        return tarea;
    }

    @Override
    public List<ToDo> getToDos() {
        return new ArrayList<>(tareas.values());
    }

    @Override
    public void actualizarToDo(ToDo tarea) {

    }

    @Override
    public void removeToDo(ToDo tarea) {

    }

    @Override
    public void addEmail(String email) {
        agenda.add(email);
    }

    @Override
    public List<String> getAgenda() {
        return agenda;
    }
}
