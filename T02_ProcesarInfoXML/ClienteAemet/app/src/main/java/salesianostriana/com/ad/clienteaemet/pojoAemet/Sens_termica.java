package salesianostriana.com.ad.clienteaemet.pojoAemet;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by flopez on 27/11/2015.
 */
@Root(strict = false)
public class Sens_termica {

    @Element
    private String maxima;

    @Element
    private String minima;


    public Sens_termica() {
    }

    public Sens_termica(String maxima, String minima) {
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
        return "Sens_termica{" +
                "maxima='" + maxima + '\'' +
                ", minima='" + minima + '\'' +
                '}';
    }
}
