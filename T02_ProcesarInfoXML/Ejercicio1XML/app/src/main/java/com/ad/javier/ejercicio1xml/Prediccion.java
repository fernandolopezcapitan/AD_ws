package com.ad.javier.ejercicio1xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Created by Javier on 22/11/2015.
 */
@ElementList
public class Prediccion {

    @Attribute
    private String dia;

    public Prediccion() {
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }



    @Override
    public String toString() {
        return "Prediccion{" +
                "dia='" + dia + '\'' +
                '}';
    }
}
