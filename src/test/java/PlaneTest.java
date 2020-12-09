import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    Plane inFlightPlane = new Plane();
    Plane landedPlane = new Plane("airport");

    @Test
    void getAirport() {
        assertEquals("none", inFlightPlane.getAirport(),"plane in flight asked its airport should return \"none\"");
        assertEquals("airport", landedPlane.getAirport(), "plane at an airport should correctly return its airport when asked");
    }

    @Test
    void atAirport() {
        assertEquals(false, inFlightPlane.atAirport(), "when plane in flight is asked if it is at an airport, it should return false");
        assertEquals(true, landedPlane.atAirport(), "plane at an airport should return true when asked if it's at an airport");
    }
}