package com.dam.salesianostriana.ad.parse;

/**
 * Created by flopez on 25/01/2016.
 */
public class Notas {

    private String fecha,concepto;

    public Notas(String fecha, String concepto) {
        this.fecha = fecha;
        this.concepto = concepto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
