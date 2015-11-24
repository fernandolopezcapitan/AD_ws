package com.salesianostriana.dam.ad.e02_instagramrss.pojos;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;


/**
 * Created by flopez on 24/11/2015.
 */
@ElementList(name = "item")
public class Item {

    @Element(name = "author")
    private String autor;
    @Element(name = "pubdate")
    private String pubdate;
    @Element(name = "description")
    private String descripcion;

    public Item() {
    }

    public Item(String autor, String pubDate, String descripcion) {
        this.autor = autor;
        this.pubdate = pubDate;
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Item{" +
                "autor='" + autor + '\'' +
                ", pubdate=" + pubdate +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
