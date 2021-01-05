package io.javasmithy.weather;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.javasmithy.geo.Geopoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ForecastRequester {
    public final String apiURL = "https://api.weather.gov/points/";

    public String request(Geopoint gp){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(prepareURL(gp.getTruncatedLatitude(4), gp.getTruncatedLongitude(4))))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //Gson gson = new Gson();
            return response.body();
        } catch (InterruptedException | IOException e){
            e.printStackTrace();
            return null;
        }
    }
    private String prepareURL(Double latitude, Double longitude){
        StringBuilder sb = new StringBuilder();
        sb.append(apiURL);
        sb.append(latitude);
        sb.append(",");
        sb.append(longitude);
        return sb.toString();
    }
}
