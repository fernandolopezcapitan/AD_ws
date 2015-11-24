package com.salesianostriana.dam.ad.e01_parseraemet;

/**
 * Created by flopez on 22/11/2015.
 */
public class ItemPronostico {

    String fecha, max, min;

    public ItemPronostico() {
    }

    public ItemPronostico(String fecha, String max, String min) {
        this.fecha = fecha;
        this.max = max;
        this.min = min;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "ItemPronostico{" +
                "fecha='" + fecha + '\'' +
                ", max='" + max + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
