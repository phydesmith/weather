package io.javasmithy;

import io.javasmithy.geo.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeopointRequesterTest {

    @Test
    public void requestGeopointWithZip(){
        GeopointRequester gpr = new GeopointRequester();

        assertEquals(43.233282, gpr.request("53033").getLatitude(), 0);
        assertEquals(-88.2396, gpr.request("53033").getLongitude(), 0);

        assertEquals(43.313361, gpr.request("53027").getLatitude(), 0);
        assertEquals(-88.37332, gpr.request("53027").getLongitude(), 0);

    }
}
