package com.ad.javier.ejercicio1xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Created by Javier on 22/11/2015.
 */
public class Temperatura {

    @Element
    private int maxima;

    @Element
    private int minima;

    public Temperatura() {
    }

    public Temperatura(int maxima, int minima) {
        this.maxima = maxima;
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "maxima=" + maxima +
                ", minima=" + minima +
                '}';
    }
}
