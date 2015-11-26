package salesianostriana.com.ad.clienteaemet.pojoMunicipios;

import org.simpleframework.xml.Element;

/**
 * Created by flopez on 26/11/2015.
 */
public class Municipio {

    @Element
    private String dc;

    @Element
    private String nombre;

    @Element
    private String cpro;

    @Element
    private String cmun;

    //Métodos
    public String getDc ()
    {
        return dc;
    }

    public void setDc (String dc)
    {
        this.dc = dc;
    }

    public String getNombre ()
    {
        return nombre;
    }

    public void setNombre (String nombre)
    {
        this.nombre = nombre;
    }

    public String getCpro ()
    {
        return cpro;
    }

    public void setCpro (String cpro)
    {
        this.cpro = cpro;
    }

    public String getCmun ()
    {
        return cmun;
    }

    public void setCmun (String cmun)
    {
        this.cmun = cmun;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dc = "+dc+", nombre = "+nombre+", cpro = "+cpro+", cmun = "+cmun+"]";
    }
}
