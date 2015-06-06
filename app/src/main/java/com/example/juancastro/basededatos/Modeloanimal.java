package com.example.juancastro.basededatos;

public class Modeloanimal {

    private String id;
    private String nombre_comun;
    private String nombre_cient;
    private String habitat;
    private String caracteristicas;

    public String getId_animal() {
        return id;
    }

    public String getNombre_comun() {
        return nombre_comun;
    }

    public String getNombre_cient() {
        return nombre_cient;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public Modeloanimal(String id, String nombre_comun , String nombre_cient, String habitat, String caracteristicas){
        this.id = id;
        this.nombre_comun=nombre_comun;
        this.nombre_cient=nombre_cient;
        this.habitat=habitat;
        this.caracteristicas=caracteristicas;
    }

}

