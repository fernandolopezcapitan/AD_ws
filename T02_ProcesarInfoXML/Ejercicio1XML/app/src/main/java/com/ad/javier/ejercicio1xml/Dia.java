package com.ad.javier.ejercicio1xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Javier on 22/11/2015.
 */
public class Dia {

    @Attribute
    private String fecha;


    @Element
    private Temperatura temperatura;

    public Dia() {
    }

    public Dia(String fecha, Temperatura temperatura) {
        this.fecha = fecha;
        this.temperatura = temperatura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Dia{" +
                "fecha='" + fecha + '\'' +
                ", temperatura=" + temperatura +
                '}';
    }
}
