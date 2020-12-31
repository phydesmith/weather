package io.javasmithy.geo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.*;

public class GeopointRequester {
    private final String openDataSoftUrl = "https://public.opendatasoft.com/api/records/1.0/search/?";
    private final String dataSet = "dataset=us-zip-code-latitude-and-longitude";
    private final String qPrefix = "&q=";

    public GeopointRequester(){}

    public Geopoint request(String zipcode){
        try {
            String url = prepareURL(zipcode);
            System.out.println("DEBUG - URL: " + url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();

            JsonObject fields = gson.fromJson(response.body(), JsonObject.class)
                                    .getAsJsonArray("records")
                                    .get(0)
                                    .getAsJsonObject()
                                    .getAsJsonObject("fields");

            Geopoint gp = new Geopoint(fields.get("latitude").getAsDouble(), fields.get("longitude").getAsDouble());
            return gp;
        } catch (InterruptedException | IOException e){
            e.printStackTrace();
            return null;
        }
    }
    private String prepareURL(String zipcode){
        StringBuilder sb = new StringBuilder();
        sb.append(openDataSoftUrl);
        sb.append(dataSet);
        sb.append(qPrefix);
        sb.append(zipcode);
        return sb.toString();
    }
}
