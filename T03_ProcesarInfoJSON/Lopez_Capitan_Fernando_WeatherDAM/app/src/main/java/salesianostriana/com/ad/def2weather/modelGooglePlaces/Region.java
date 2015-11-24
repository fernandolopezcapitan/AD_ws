package salesianostriana.com.ad.def2weather.modelGooglePlaces;

/**
 * Created by flopez on 12/11/2015.
 */
public class Region {

    private Predictions[] predictions;

    private String status;

    public Predictions[] getPredictions ()
    {
        return predictions;
    }

    public void setPredictions (Predictions[] predictions)
    {
        this.predictions = predictions;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [predictions = "+predictions+", status = "+status+"]";
    }
}
