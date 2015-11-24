package com.salesianostriana.ad.instagramjsonparser.model;

import java.util.Arrays;

/**
 * Created by Luismi on 02/11/2015.
 */
public class Instagram {
    
    private MetaData meta;
    private ImageData[] data;

    public Instagram() {
    }

    public Instagram(ImageData[] data, MetaData meta) {
        this.data = data;
        this.meta = meta;
    }

    public ImageData[] getData() {
        return data;
    }

    public void setData(ImageData[] data) {
        this.data = data;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Instagram{" +
                "data=" + Arrays.toString(data) +
                ", meta=" + meta +
                '}';
    }
}
