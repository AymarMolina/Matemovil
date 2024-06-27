package com.example.matemovil;

public class Tema {
    String id;
    String nombre, urlImagen,grado;

    public Tema(String id, String nombre, String urlImagen, String grado) {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        this.grado = grado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}
