package com.salesianostriana.ad.instagramjsonparser.model;

/**
 * Created by Luismi on 02/11/2015.
 */
public class MetaData {

    private int code;

    public MetaData() {
    }

    public MetaData(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "code=" + code +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
