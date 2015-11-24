package com.ad.javier.ejercicio1xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Order;

import java.util.List;

/**
 * Created by Javier on 22/11/2015.
 */
@Root(strict = false, name = "root")
public class Tiempo {

    @Element(name="nombre")
    private String  poblacion;


    @Element
    private String provincia;


    @ElementList
    protected List<Dia> prediccion;


    public Tiempo() {
    }

    public Tiempo(String nombre, String provincia, List<Dia> prediccion) {
        this.poblacion = nombre;
        this.provincia = provincia;
        this.prediccion = prediccion;
    }

    public String getNombre() {
        return poblacion;
    }

    public void setNombre(String nombre) {
        this.poblacion = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Dia> getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(List<Dia> prediccion) {
        this.prediccion = prediccion;
    }

    @Override
    public String toString() {
        return "Root{" +
                "poblacion='" + poblacion + '\'' +
                ", provincia='" + provincia + '\'' +
                ", prediccion=" + prediccion +
                '}';
    }
}
