package com.example.tpinmobiliaria.model;

public class Pago {

    private int id;
    private int nro_pago;
    private String fecha;
    private double importe;
    private Contrato contrato;

    public Pago() {

    }

    public Pago(int id, int nro_pago, String fecha, double importe, Contrato contrato) {
        this.id = id;
        this.nro_pago = nro_pago;
        this.fecha = fecha;
        this.importe = importe;
        this.contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNro_pago() {
        return nro_pago;
    }

    public void setNro_pago(int nro_pago) {
        this.nro_pago = nro_pago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
