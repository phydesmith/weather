package io.javasmithy;

import io.javasmithy.geo.Geopoint;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeopointTest {

    @Test
    public void testLatLong(){
        Geopoint gp = new Geopoint("53033");
        assertTrue(gp.getLatitude().equals("43.233282"));
        assertTrue(gp.getLongitude().equals("-88.2396"));
        System.out.println(gp);
    }
}
