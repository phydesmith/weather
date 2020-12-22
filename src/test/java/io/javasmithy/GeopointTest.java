package io.javasmithy;

import io.javasmithy.geo.Geopoint;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeopointTest {
    @Test
    public void testLatLong(){
        String latitude = "43.233282";
        String longitude = "-88.2396";
        Geopoint gp = new Geopoint(latitude, longitude);
        assertTrue(gp.getLatitude().equals("43.233282"));
        assertTrue(gp.getLongitude().equals("-88.2396"));
    }
}
