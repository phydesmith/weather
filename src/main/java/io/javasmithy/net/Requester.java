package io.javasmithy.net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.javasmithy.geo.Geopoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requester {
    private final String OPEN_DATASOFT_URL = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=us-zip-code-latitude-and-longitude&q=";
    private final String WEATHER_API_POINTS_URL = "https://api.weather.gov/points/";

    private HttpClient client;
    private Gson gson;

    public Requester(){
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public Geopoint request(String zipcode){
        JsonObject fields = gson.fromJson(getBodyOf(OPEN_DATASOFT_URL+zipcode), JsonObject.class)
                .getAsJsonArray("records")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("fields");
        return new Geopoint(fields.get("latitude").getAsDouble(), fields.get("longitude").getAsDouble());
    }

    public String request(Geopoint gp){
        return getBodyOf(WEATHER_API_POINTS_URL+gp.getTruncatedLatitude(4)+","+gp.getTruncatedLongitude(4));
    }

    private String getBodyOf(String url){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            return this.client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
            return "Error";
        }
    }



}
