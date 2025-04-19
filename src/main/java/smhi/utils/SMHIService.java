package smhi.utils;

import java.io.IOException;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import smhi.data.Geo;

public class SMHIService {
    private final Geo geo;
    private final Client client;

    public SMHIService(Geo geo, Client client) {
        this.geo = geo;
        this.client = client;
    }

    public String getString(String url) throws IOException {
        WebTarget target = client.target(url);
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        
        int status = response.getStatus();
        if (status != 200) {
            throw new IOException("Error: " + status);
        }

        String string = response.readEntity(String.class);
        response.close();

        return string;
    }

    public Geo getGeo() {
        return geo;
    }
}
