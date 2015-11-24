package salesianostriana.com.ad.def2weather.modelPronostico;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author flopez on 12/11/2015.
 */
public class Sys {
    @SerializedName("population")
    @Expose
    private long population;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sys() {
    }

    /**
     *
     * @param population
     */
    public Sys(long population) {
        this.population = population;
    }

    /**
     *
     * @return
     * The population
     */
    public long getPopulation() {
        return population;
    }

    /**
     *
     * @param population
     * The population
     */
    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
