package com.example.matemovil;

public class Alumno {
    String nombre,correo,contrasena;
    int grado,edad;

    public Alumno(String nombre, String correo, String contrasena, int grado, int edad) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.grado = grado;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
