package com.manco.app.Model;

import java.util.Date;

/**
 * Created by marcosantonioaguilerely on 6/5/15.
 */
public class Termograma {

    private int     id_termograma;
    private String  ubicación;
    private String  foto_camara;
    private String  foto_termograma;
    private String  condicion_termica; //listado simple que se captura desde un listView
    private int     id_equipo;         //esta es una tabla o listado que trae el listado de equipos
    private int     id_instalacion;
    private int     id_usuario;
    private Date    creado;
    private Date    actualizado;


    public Termograma(int id_termograma, String ubicación, String foto_camara, String foto_termograma, String condicion_termica, int id_equipo, int id_instalacion, int id_usuario) {
        this.id_termograma = id_termograma;
        this.ubicación = ubicación;
        this.foto_camara = foto_camara;
        this.foto_termograma = foto_termograma;
        this.condicion_termica = condicion_termica;
        this.id_equipo = id_equipo;
        this.id_instalacion = id_instalacion;
        this.id_usuario = id_usuario;
    }

    public int getId_termograma() {
        return id_termograma;
    }

    public void setId_termograma(int id_termograma) {
        this.id_termograma = id_termograma;
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

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public int getId_instalacion() {
        return id_instalacion;
    }

    public void setId_instalacion(int id_instalacion) {
        this.id_instalacion = id_instalacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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
