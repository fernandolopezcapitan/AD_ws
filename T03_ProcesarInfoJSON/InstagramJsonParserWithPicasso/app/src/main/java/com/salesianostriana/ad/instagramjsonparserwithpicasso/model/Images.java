package com.salesianostriana.ad.instagramjsonparserwithpicasso.model;

/**
 * Created by Luismi on 02/11/2015.
 */
public class Images {

    private Image low_resolution;
    private Image thumbnail;
    private Image standard_resolution;

    public Images() {
    }

    public Images(Image low_resolution, Image standard_resolution, Image thumbnail) {
        this.low_resolution = low_resolution;
        this.standard_resolution = standard_resolution;
        this.thumbnail = thumbnail;
    }

    public Image getLow_resolution() {
        return low_resolution;
    }

    public void setLow_resolution(Image low_resolution) {
        this.low_resolution = low_resolution;
    }

    public Image getStandard_resolution() {
        return standard_resolution;
    }

    public void setStandard_resolution(Image standard_resolution) {
        this.standard_resolution = standard_resolution;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Images{" +
                "low_resolution=" + low_resolution +
                ", thumbnail=" + thumbnail +
                ", standard_resolution=" + standard_resolution +
                '}';
    }
}
