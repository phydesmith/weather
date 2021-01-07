package io.javasmithy.net;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.javasmithy.forecast.ForecastPeriod;
import io.javasmithy.geo.Geopoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import java.io.InputStream;
import java.util.ArrayList;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Requester {
    private final String OPEN_DATASOFT_URL = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=us-zip-code-latitude-and-longitude&q=";
    private final String WEATHER_API_POINTS_URL = "https://api.weather.gov/points/";

    private HttpClient client;
    private Gson gson;

    public Requester(){
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public Geopoint getGeopoint(String zipcode){
        JsonObject fields = request(OPEN_DATASOFT_URL+zipcode)
                .getAsJsonArray("records")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("fields");
        return new Geopoint(fields.get("latitude").getAsDouble(), fields.get("longitude").getAsDouble());
    }

    public String getMetaData(Geopoint gp){
        JsonObject properties = request(WEATHER_API_POINTS_URL+gp.getTruncatedLatitude(4)+","+ gp.getTruncatedLongitude(4)).getAsJsonObject("properties");
        return properties.get("forecast").getAsString();
    }

    public ObservableList<ForecastPeriod> getForecastPeriods(String forecastURL){
        JsonObject forecastData = request(forecastURL);
        JsonArray forecastPeriodJsonArray = forecastData
                .getAsJsonObject("properties")
                .getAsJsonArray("periods");

        List<ForecastPeriod> forecasts = new ArrayList<>();

        for (JsonElement e:
             forecastPeriodJsonArray) {
            forecasts.add(this.gson.fromJson(e, ForecastPeriod.class));
        }
        return FXCollections.observableArrayList(forecasts);
    }

    public Image getForecastImage(String uri){
        return getImageOf(URI.create(uri));
    }


    private JsonObject request(String uriPath){
        return this.gson.fromJson(getBodyOf(URI.create(uriPath)), JsonObject.class);
    }
    private String getBodyOf(URI uri){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            return this.client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
            return "Error";
        }
    }
    private Image getImageOf(URI uri){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            return new Image(this.client.send(request, HttpResponse.BodyHandlers.ofInputStream()).body());
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
            return new Image("www.imageontfound.com");
        }
    }



}
