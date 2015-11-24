package com.salesianostriana.ad.instagramjsonparser.model;

/**
 * Created by Luismi on 02/11/2015.
 */
public class ImageData {


    private String created_time;
    private String link;
    private Images images;
    private User user;

    public ImageData() {
    }

    public ImageData(String created_time, Images images, String link, User user) {
        this.created_time = created_time;
        this.images = images;
        this.link = link;
        this.user = user;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ImageData{" +
                "created_time='" + created_time + '\'' +
                ", link='" + link + '\'' +
                ", images=" + images +
                ", user=" + user +
                '}';
    }
}
