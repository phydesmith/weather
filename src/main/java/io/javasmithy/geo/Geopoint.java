package io.javasmithy.geo;

public class Geopoint {
    String latitude;
    String longitude;

    public Geopoint(){}

    public Geopoint(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude(){
        return this.latitude;
    }

    public String getLongitude(){
        return this.longitude;
    }
}
