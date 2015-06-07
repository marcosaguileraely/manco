package com.manco.app.Model;

import java.util.Date;

/**
 * Created by marcosantonioaguilerely on 6/5/15.
 */
public class Termograma {

    private int     id_equipo; //esta es una tabla o listado que trae el listado de equipos
    private String  ubicación;
    private String  foto_camara;
    private String  foto_termograma;
    private String  condicion_termica; //listado simple que se captura desde un listView
    private Date    creado;
    private Date    actualizado;

    public Termograma(int id_equipo, String ubicación, String foto_camara, String foto_termograma, String condicion_termica, Date creado, Date actualizado) {
        this.id_equipo = id_equipo;
        this.ubicación = ubicación;
        this.foto_camara = foto_camara;
        this.foto_termograma = foto_termograma;
        this.condicion_termica = condicion_termica;
        this.creado = creado;
        this.actualizado = actualizado;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getUbicación() {
        return ubicación;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }

    public String getFoto_camara() {
        return foto_camara;
    }

    public void setFoto_camara(String foto_camara) {
        this.foto_camara = foto_camara;
    }

    public String getFoto_termograma() {
        return foto_termograma;
    }

    public void setFoto_termograma(String foto_termograma) {
        this.foto_termograma = foto_termograma;
    }

    public String getCondicion_termica() {
        return condicion_termica;
    }

    public void setCondicion_termica(String condicion_termica) {
        this.condicion_termica = condicion_termica;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }
}
