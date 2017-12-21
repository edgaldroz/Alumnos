package com.ar.alumnos.modelo;

/**
 * Created by Adalberto on 08/12/2017.
 */

public class Alumno
{
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    //region ..:: VARIABLES ::..
    private String Codigo;
    private String Nombre;
    private int Edad;
    //endregion


}