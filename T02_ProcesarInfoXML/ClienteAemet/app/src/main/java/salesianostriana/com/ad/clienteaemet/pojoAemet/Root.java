package salesianostriana.com.ad.clienteaemet.pojoAemet;

import org.simpleframework.xml.Element;

/**
 * Created by flopez on 26/11/2015.
 */
@org.simpleframework.xml.Root
public class Root {

    @Element
    private String elaborado;

    @Element
    private String nombre;

    @Element
    private String provincia;

    @Element
    private Prediccion prediccion;


    public Root() {
    }

    public Root(String elaborado, String nombre, String provincia, Prediccion prediccion) {
        this.elaborado = elaborado;
        this.nombre = nombre;
        this.provincia = provincia;
        this.prediccion = prediccion;
    }

    public String getElaborado() {
        return elaborado;
    }

    public void setElaborado(String elaborado) {
        this.elaborado = elaborado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Prediccion getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(Prediccion prediccion) {
        this.prediccion = prediccion;
    }

    @Override
    public String toString() {
        return "Root{" +
                "elaborado='" + elaborado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", provincia='" + provincia + '\'' +
                ", prediccion=" + prediccion +
                '}';
    }
}
