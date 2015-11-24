package com.salesianostriana.dam.ad.e01_parseraemet.models;

/**
 * Created by flopez on 22/11/2015.
 */

public class Temperatura {

    private String maxima;

    private String minima;

    public Temperatura() {
    }

    public Temperatura(String maxima, String minima) {
        this.maxima = maxima;
        this.minima = minima;
    }

    public String getMaxima ()
    {
        return maxima;
    }

    public void setMaxima (String maxima)
    {
        this.maxima = maxima;
    }

    public String getMinima ()
    {
        return minima;
    }

    public void setMinima (String minima)
    {
        this.minima = minima;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [maxima = "+maxima+", minima = "+minima+"]";
    }
}
