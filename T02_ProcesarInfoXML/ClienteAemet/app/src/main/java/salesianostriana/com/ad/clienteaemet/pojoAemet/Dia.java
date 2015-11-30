package salesianostriana.com.ad.clienteaemet.pojoAemet;

import org.simpleframework.xml.*;

import java.util.List;
import java.util.Map;

/**
 * Created by flopez on 27/11/2015.
 */
@org.simpleframework.xml.Root(strict = false)
public class Dia {

    @Attribute
    private String fecha;

    @ElementMap(entry = "prob_precipitacion", key = "periodo", attribute=true, inline=true)
    private Map<String,String> prob_precipitacion;

    @ElementMap(entry = "cota_nieve_prov", key = "periodo", attribute=true, inline=true)
    private Map<String,String> cota_nieve_prov;

    @ElementList(inline = true)
    private List<Estado_cielo> estado_cielo;

    @Element
    private Temperatura temperatura;

    @Element
    private Sens_termica sens_termica;

    public Dia() {
    }

    public Dia(String fecha, Map<String, String> prob_precipitacion, Map<String, String> cota_nieve_prov, List<Estado_cielo> estado_cielo, Temperatura temperatura, Sens_termica sens_termica) {
        this.fecha = fecha;
        this.prob_precipitacion = prob_precipitacion;
        this.cota_nieve_prov = cota_nieve_prov;
        this.estado_cielo = estado_cielo;
        this.temperatura = temperatura;
        this.sens_termica = sens_termica;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Map<String, String> getProb_precipitacion() {
        return prob_precipitacion;
    }

    public void setProb_precipitacion(Map<String, String> prob_precipitacion) {
        this.prob_precipitacion = prob_precipitacion;
    }

    public Map<String, String> getCota_nieve_prov() {
        return cota_nieve_prov;
    }

    public void setCota_nieve_prov(Map<String, String> cota_nieve_prov) {
        this.cota_nieve_prov = cota_nieve_prov;
    }

    public List<Estado_cielo> getEstado_cielo() {
        return estado_cielo;
    }

    public void setEstado_cielo(List<Estado_cielo> estado_cielo) {
        this.estado_cielo = estado_cielo;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public Sens_termica getSens_termica() {
        return sens_termica;
    }

    public void setSens_termica(Sens_termica sens_termica) {
        this.sens_termica = sens_termica;
    }

    @Override
    public String toString() {
        return "Dia{" +
                "fecha='" + fecha + '\'' +
                ", prob_precipitacion=" + prob_precipitacion +
                ", cota_nieve_prov=" + cota_nieve_prov +
                ", estado_cielo=" + estado_cielo +
                ", temperatura=" + temperatura +
                ", sens_termica=" + sens_termica +
                '}';
    }
}
