package com.example.tpinmobiliaria.model;

import java.io.Serializable;

public class Contrato implements Serializable {

    private int id;
    private String fecha_alta;
    private String fecha_baja;
    private double monto;
    private String descripcion;
    private Inquilino inquilino;
    private Inmueble inmueble;
    private Propietario propietario;

    public Contrato() {

    }

    public Contrato(int id, String fecha_alta, String fecha_baja, double monto, String descripcion, Inquilino inquilino, Inmueble inmueble, Propietario propietario) {
        this.id = id;
        this.fecha_alta = fecha_alta;
        this.fecha_baja = fecha_baja;
        this.monto = monto;
        this.descripcion = descripcion;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(String fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}
