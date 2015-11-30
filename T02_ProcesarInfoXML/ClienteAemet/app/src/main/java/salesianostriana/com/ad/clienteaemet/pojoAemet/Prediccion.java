package salesianostriana.com.ad.clienteaemet.pojoAemet;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by flopez on 27/11/2015.
 */

public class Prediccion {

    @ElementList(inline = true)
    private List<Dia> dias;

    public Prediccion() {
    }

    public Prediccion(List<Dia> dias) {
        this.dias = dias;
    }

    public List<Dia> getDias() {
        return dias;
    }

    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }

    @Override
    public String toString() {
        return "Prediccion{" +
                "dias=" + dias +
                '}';
    }
}
