package salesianostriana.com.ad.clienteaemet;

/**
 * Created by flopez on 28/11/2015.
 */
public class ItemPronostico {

    String fecha, precipitacion_media, cota_nieve_minima, estado_cielo, temperatura_max, temperatura_min, sensacion_max, sensacion_min;

    public ItemPronostico(String fecha, String precipitacion_media, String cota_nieve_minima, String estado_cielo, String temperatura_max, String temperatura_min, String sensacion_max, String sensacion_min) {
        this.fecha = fecha;
        this.precipitacion_media = precipitacion_media;
        this.cota_nieve_minima = cota_nieve_minima;
        this.estado_cielo = estado_cielo;
        this.temperatura_max = temperatura_max;
        this.temperatura_min = temperatura_min;
        this.sensacion_max = sensacion_max;
        this.sensacion_min = sensacion_min;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrecipitacion_media() {
        return precipitacion_media;
    }

    public void setPrecipitacion_media(String precipitacion_media) {
        this.precipitacion_media = precipitacion_media;
    }

    public String getCota_nieve_minima() {
        return cota_nieve_minima;
    }

    public void setCota_nieve_minima(String cota_nieve_minima) {
        this.cota_nieve_minima = cota_nieve_minima;
    }

    public String getEstado_cielo() {
        return estado_cielo;
    }

    public void setEstado_cielo(String estado_cielo) {
        this.estado_cielo = estado_cielo;
    }

    public String getTemperatura_max() {
        return temperatura_max;
    }

    public void setTemperatura_max(String temperatura_max) {
        this.temperatura_max = temperatura_max;
    }

    public String getTemperatura_min() {
        return temperatura_min;
    }

    public void setTemperatura_min(String temperatura_min) {
        this.temperatura_min = temperatura_min;
    }

    public String getSensacion_max() {
        return sensacion_max;
    }

    public void setSensacion_max(String sensacion_max) {
        this.sensacion_max = sensacion_max;
    }

    public String getSensacion_min() {
        return sensacion_min;
    }

    public void setSensacion_min(String sensacion_min) {
        this.sensacion_min = sensacion_min;
    }
}
