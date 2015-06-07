package com.manco.app.Model;

import java.util.Date;

/**
 * Created by marcosantonioaguilerely on 6/5/15.
 */
public class Instalacion {
    private int     id;
    private String  nombre;
    private Date    fecha;
    private String  lat_long;
    private Boolean ter_comin;

    public Instalacion(int id, String nombre, Date fecha, String lat_long, Boolean ter_comin) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lat_long = lat_long;
        this.ter_comin = ter_comin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLat_long() {
        return lat_long;
    }

    public void setLat_long(String lat_long) {
        this.lat_long = lat_long;
    }

    public Boolean getTer_comin() {
        return ter_comin;
    }

    public void setTer_comin(Boolean ter_comin) {
        this.ter_comin = ter_comin;
    }
}
