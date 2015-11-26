package salesianostriana.com.ad.clienteaemet;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.xml.transform.ErrorListener;

/**
 * Created by flopez on 26/11/2015.
 */
public class XmlCustomRequest<T> extends Request<T> {

    private final Serializer serializer = new Persister();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public XmlCustomRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, ErrorListener errorListener) {
        super(Method.GET, url, (Response.ErrorListener) errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String xml = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    serializer.read(clazz, xml, false),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }


}
