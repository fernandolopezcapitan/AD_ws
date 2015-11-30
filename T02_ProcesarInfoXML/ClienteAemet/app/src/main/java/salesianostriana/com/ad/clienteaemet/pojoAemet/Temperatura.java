package salesianostriana.com.ad.clienteaemet.pojoAemet;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by flopez on 27/11/2015.
 */
@Root(strict = false)
public class Temperatura {

    @Element
    private String maxima;

    @Element
    private String minima;

    public Temperatura() {
    }

    public Temperatura(String maxima, String minima) {
        this.maxima = maxima;
        this.minima = minima;
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

    @Override
    public String toString() {
        return "Temperatura{" +
                "maxima='" + maxima + '\'' +
                ", minima='" + minima + '\'' +
                '}';
    }
}
