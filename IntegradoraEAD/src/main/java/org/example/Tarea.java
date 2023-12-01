package org.example;

import java.util.Date;

public class Tarea {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String titulo;
    private String descripcion;
    private String prioridad;
    private String estatus;
    private String fecha;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Tarea(String titulo, String descripcion, String prioridad, String estatus, String fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estatus = estatus;
        this.fecha = fecha;
    }


    public Tarea(Integer id, String titulo, String descripcion, String prioridad, String estatus, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estatus = estatus;
        this.fecha = fecha;
    }

    public Tarea() {

    }
    @Override
    public String toString() {
        return "_________________________________________" + "\n" +
                "|" + "Título: " + titulo + "|" + "\n" +
                "|" + "Descripción: " + descripcion + "|" + "\n" +
                "|" + "Prioridad: " + prioridad + "|" + "\n" +
                "|" + "Estatus: " + estatus + "|" + "\n" +
                "|" + "Fecha: " + fecha + "|"+ "\n" +
                "_________________________________________";
    }

}




