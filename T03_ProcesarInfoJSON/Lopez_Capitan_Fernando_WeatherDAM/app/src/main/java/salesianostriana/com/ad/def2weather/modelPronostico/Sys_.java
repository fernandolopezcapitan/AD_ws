package salesianostriana.com.ad.def2weather.modelPronostico;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author flopez on 12/11/2015.
 */
public class Sys_ {
    @SerializedName("pod")
    @Expose
    private String pod;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sys_() {
    }

    /**
     *
     * @param pod
     */
    public Sys_(String pod) {
        this.pod = pod;
    }

    /**
     *
     * @return
     * The pod
     */
    public String getPod() {
        return pod;
    }

    /**
     *
     * @param pod
     * The pod
     */
    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
