package com.salesianostriana.dam.ad.e01_parseraemet.models;


/**
 * Created by flopez on 22/11/2015.
 */
@org.simpleframework.xml.Root
public class Root {

    private Prediccion prediccion;

    public Prediccion getPrediccion ()
    {
        return prediccion;
    }

    public void setPrediccion (Prediccion prediccion)
    {
        this.prediccion = prediccion;
    }

    @Override
    public String toString() {
        return "Root{" +
                "prediccion=" + prediccion +
                '}';
    }
}
