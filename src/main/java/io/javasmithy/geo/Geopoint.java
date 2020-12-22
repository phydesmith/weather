package io.javasmithy.geo;

public class Geopoint {
    String zipcode;
    String latitude;
    String longitude;

    public Geopoint(String zipcode){
        this.zipcode = zipcode;
        this.latitude = "0";
        this.longitude = "0";
    }

    public String getLatitude(){
        return this.latitude;
    }

    public String getLongitude(){
        return this.longitude;
    }
}
