package com.example.matemovil;

public class Ejercicio {
    String id;
    String enunciado, urlImagen,correcta;

    public Ejercicio(String id, String enunciado, String urlImagen, String correcta) {
        this.id = id;
        this.enunciado = enunciado;
        this.urlImagen = urlImagen;
        this.correcta = correcta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

}
