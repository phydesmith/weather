package io.javasmithy.geo;

import java.util.Objects;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class Geopoint {
    double latitude;
    double longitude;

    public Geopoint(){
        this(0,0);
    }

    public Geopoint(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public double getTruncatedLatitude(int places){
        return truncate(this.latitude, places);
    }
    public double getTruncatedLongitude(int places){
        return truncate(this.longitude, places);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geopoint geopoint = (Geopoint) o;
        return Double.compare(geopoint.latitude, latitude) == 0 && Double.compare(geopoint.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Geopoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    private double truncate(double d, int places){
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(places, RoundingMode.DOWN).doubleValue();
    }
}
