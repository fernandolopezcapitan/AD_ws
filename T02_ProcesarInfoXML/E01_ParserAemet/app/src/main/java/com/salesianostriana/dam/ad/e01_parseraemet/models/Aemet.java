package com.salesianostriana.dam.ad.e01_parseraemet.models;

/**
 * Created by flopez on 22/11/2015.
 */
public class Aemet {

    private Root root;

    public Root getRoot ()
    {
        return root;
    }

    public void setRoot (Root root)
    {
        this.root = root;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [root = "+root+"]";
    }

}
