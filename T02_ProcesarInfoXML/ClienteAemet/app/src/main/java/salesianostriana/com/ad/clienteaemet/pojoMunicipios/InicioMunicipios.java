package salesianostriana.com.ad.clienteaemet.pojoMunicipios;

/**
 * Created by flopez on 26/11/2015.
 */
public class InicioMunicipios {

    private Municipios municipios;

    public Municipios getMunicipios ()
    {
        return municipios;
    }

    public void setMunicipios (Municipios municipios)
    {
        this.municipios = municipios;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [municipios = "+municipios+"]";
    }
}
