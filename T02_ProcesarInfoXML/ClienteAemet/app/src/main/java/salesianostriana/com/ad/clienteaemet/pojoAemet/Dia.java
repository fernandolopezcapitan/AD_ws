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

    @ElementList
    private List<Estado_cielo> estado_cielo;

    @Element
    private Temperatura temperatura;

    @Element
    private Sens_termica sens_termica;


}
