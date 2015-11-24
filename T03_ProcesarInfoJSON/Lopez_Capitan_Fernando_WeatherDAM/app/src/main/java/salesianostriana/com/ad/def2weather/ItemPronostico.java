package salesianostriana.com.ad.def2weather;

/**
 * Created by flopez on 12/11/2015.
 */
public class ItemPronostico {

    String diaSemana, ciudad, temperatura, maxima, minima;
    int sol;

    public ItemPronostico(String diaSemana, String ciudad, int sol, String temperatura, String maxima, String minima) {
        this.diaSemana = diaSemana;
        this.ciudad = ciudad;
        this.sol = sol;
        this.temperatura = temperatura;
        this.maxima = maxima;
        this.minima = minima;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
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
}
