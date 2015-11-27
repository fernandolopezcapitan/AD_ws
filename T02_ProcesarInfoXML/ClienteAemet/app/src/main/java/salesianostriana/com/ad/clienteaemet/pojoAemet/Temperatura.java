package salesianostriana.com.ad.clienteaemet.pojoAemet;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;

import java.util.Map;

/**
 * Created by flopez on 27/11/2015.
 */
public class Temperatura {

    @Element
    private String maxima;

    @Element
    private String minima;

    @ElementMap(entry = "dato", key = "hora", attribute=true, inline=true)
    private Map<String,String> dato;

    public Temperatura() {
    }

    public Temperatura(String maxima, String minima, Map<String, String> dato) {
        this.maxima = maxima;
        this.minima = minima;
        this.dato = dato;
    }

    public String getMaxima() {
        return maxima;
    }

    public void setMaxima(String maxima) {
        this.maxima = maxima;
    }

    public String getMinima() {
        return minima;
    }

    public void setMinima(String minima) {
        this.minima = minima;
    }

    public Map<String, String> getDato() {
        return dato;
    }

    public void setDato(Map<String, String> dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "maxima='" + maxima + '\'' +
                ", minima='" + minima + '\'' +
                ", dato=" + dato +
                '}';
    }
}
