package com.salesianostriana.ad.instagramjsonparser.model;

/**
 * Created by Luismi on 02/11/2015.
 */
public class User {

    private String username;
    private String profile_picture;
    private String id;
    private String full_name;

    public User() {
    }

    public User(String full_name, String id, String profile_picture, String username) {
        this.full_name = full_name;
        this.id = id;
        this.profile_picture = profile_picture;
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "full_name='" + full_name + '\'' +
                ", username='" + username + '\'' +
                ", profile_picture='" + profile_picture + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
