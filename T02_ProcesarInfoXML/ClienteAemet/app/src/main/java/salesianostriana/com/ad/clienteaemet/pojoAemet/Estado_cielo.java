package salesianostriana.com.ad.clienteaemet.pojoAemet;

import org.simpleframework.xml.Attribute;

/**
 * Created by flopez on 27/11/2015.
 */
public class Estado_cielo {

    @Attribute
    private String periodo;

    @Attribute
    private String descripcion;

    private String content;

    public Estado_cielo() {
    }

    public Estado_cielo(String periodo, String descripcion, String content) {
        this.periodo = periodo;
        this.descripcion = descripcion;
        this.content = content;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Estado_cielo{" +
                "periodo='" + periodo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
