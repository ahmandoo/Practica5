package com.tt1.test.mocks;

import com.tt1.test.IRepositorio;
import com.tt1.test.ToDo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMock implements IRepositorio {
    public boolean guardarLlamado = false;
    public boolean marcarCompletadoLlamado = false;
    private List<ToDo> tareas = new ArrayList<>();
    private List<String> agenda = new ArrayList<>();

    @Override
    public ToDo buscarPorNombre(String nombre) {
        for(ToDo todo: tareas){
            if(todo.getNombre().equals(nombre)){
                return todo;
            }
        }
        return null;
    }

    @Override
    public void marcarComoCompletado(String nombre) {
        for(ToDo tarea: tareas){
            if(tarea.getNombre().equals(nombre)){
                tarea.setCompletado(true);
            }
        }
        this.marcarCompletadoLlamado = true;
    }

    @Override
    public void guardarNuevoToDo(ToDo todo) {
        tareas.add(todo);
        this.guardarLlamado = true;
    }

    @Override
    public void guardarEmail(String email) {
        agenda.add(email);
        this.guardarLlamado = true;
    }

    @Override
    public List<ToDo> obtenerTareas() {
        return tareas;
    }

    @Override
    public List<String> obtenerEmails() {
        return agenda;
    }
}
