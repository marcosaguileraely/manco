package com.manco.app.Model;

/**
 * Created by marcosantonioaguilerely on 6/5/15.
 */
public class Equipos {

    private int     id_equipo;
    private String  nombre;
    private String  descripcion;

    public Equipos(int id_equipo, String nombre, String descripcion) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
