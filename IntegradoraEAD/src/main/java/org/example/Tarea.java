package org.example;

import java.util.Date;

public class Tarea implements Comparable<Tarea> {

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
        return String.format("Titulo: %s%nDescripcion: %s%nPrioridad: %s%nEstatus: %s%nFecha: %s%n",
                titulo,descripcion,prioridad,estatus,fecha);
    }

    @Override
    public int compareTo(Tarea tarea) {
        int comparacionPrioridad = compararPrioridades(this.prioridad, tarea.prioridad);
        if (comparacionPrioridad != 0) {
            return comparacionPrioridad;
        }

        int comparacionFechas = this.fecha.compareTo(tarea.fecha);
        if (comparacionFechas != 0) {
            return comparacionFechas;
        }



        return this.titulo.compareTo(tarea.titulo);

    }

    private int compararPrioridades(String prioridad1, String prioridad2) {
        if (prioridad1.equals("alta")) {
            return -1;
        } else if (prioridad2.equals("alta")) {
            return 1;
        } else if (prioridad1.equals("media")) {
            return -1;
        } else if (prioridad2.equals("media")) {
            return 1;
        } else {
            return 0;
        }
    }
}




