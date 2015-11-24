package com.salesianostriana.ad.instagramjsonparserwithpicasso.model;

/**
 * Created by Luismi on 02/11/2015.
 */
public class Image {

    private String url;
    private int width;
    private int height;

    public Image() {
    }

    public Image(int height, String url, int width) {
        this.height = height;
        this.url = url;
        this.width = width;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Image{" +
                "height=" + height +
                ", url='" + url + '\'' +
                ", width=" + width +
                '}';
    }
}
