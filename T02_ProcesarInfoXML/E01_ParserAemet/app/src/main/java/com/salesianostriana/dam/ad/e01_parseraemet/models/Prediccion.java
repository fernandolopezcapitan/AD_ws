package com.salesianostriana.dam.ad.e01_parseraemet.models;

import org.simpleframework.xml.*;

/**
 * Created by flopez on 22/11/2015.
 */
@org.simpleframework.xml.Root
public class Prediccion {

    @ElementList
    private Dia[] dia;

    public Prediccion() {
    }

    public Prediccion(Dia[] dia) {
        this.dia = dia;
    }

    public Dia[] getDia ()
    {
        return dia;
    }

    public void setDia (Dia[] dia)
    {
        this.dia = dia;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dia = "+dia+"]";
    }
}
