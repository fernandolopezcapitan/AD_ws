package salesianostriana.com.ad.def2weather.modelPronostico;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

/**
 * @author flopez on 12/11/2015.
 */
public class OpenWeatherPronostico {
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private long cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<salesianostriana.com.ad.def2weather.modelPronostico.List> list = new ArrayList<salesianostriana.com.ad.def2weather.modelPronostico.List>();

    /**
     * No args constructor for use in serialization
     *
     */
    public OpenWeatherPronostico() {
    }

    /**
     *
     * @param message
     * @param cnt
     * @param cod
     * @param list
     * @param city
     */
    public OpenWeatherPronostico(City city, String cod, double message, long cnt, java.util.List<salesianostriana.com.ad.def2weather.modelPronostico.List> list) {
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
    }

    /**
     *
     * @return
     * The city
     */
    public City getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     *
     * @param cod
     * The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     *
     * @return
     * The message
     */
    public double getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(double message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The cnt
     */
    public long getCnt() {
        return cnt;
    }

    /**
     *
     * @param cnt
     * The cnt
     */
    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    /**
     *
     * @return
     * The list
     */
    public java.util.List<salesianostriana.com.ad.def2weather.modelPronostico.List> getList() {
        return list;
    }

    /**
     *
     * @param list
     * The list
     */
    public void setList(java.util.List<salesianostriana.com.ad.def2weather.modelPronostico.List> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
