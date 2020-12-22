package io.javasmithy;

import io.javasmithy.geo.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeopointRequesterTest {

    @Test
    public void requestGeopointWithZip(){
        GeopointRequester gpr = new GeopointRequester();
        Geopoint gp = gpr.request("53033");
        assertTrue(gp instanceof Geopoint);
        assertTrue(gp.getLatitude().equals("43.233282"));
        assertTrue(gp.getLongitude().equals("-88.2396"));
    }
}
