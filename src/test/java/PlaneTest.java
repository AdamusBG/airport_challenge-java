import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    Plane inFlightPlane;
    Plane landedPlane;

    @Test
    void getAirport() {
        resetTestObjects();
        assertEquals("none", inFlightPlane.getAirport(),"plane in flight asked its airport should return \"none\"");
        assertEquals("airport", landedPlane.getAirport(), "plane at an airport should correctly return its airport when asked");
    }

    @Test
    void atAirport() {
        resetTestObjects();
        assertEquals(false, inFlightPlane.atAirport(), "when plane in flight is asked if it is at an airport, it should return false");
        assertEquals(true, landedPlane.atAirport(), "plane at an airport should return true when asked if it's at an airport");
    }

    @Test
    void successful_land() {
        resetTestObjects();
        try {
            inFlightPlane.land("frenchAirport");
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }
        assertEquals("frenchAirport", inFlightPlane.getAirport(), "an in-flight plane should successfully land and then correctly report its airport");
    }

    @Test
    void unsuccessful_land() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> {
            landedPlane.land("frenchAirport");
        });

        String expectedMessage = "This plane is already at an airport!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling a plane that is already at an airport to land");
    }

    @Test
    void successful_takeOff() {
        resetTestObjects();
        try {
            landedPlane.takeOff("airport");
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }
        assertEquals("none", inFlightPlane.getAirport(), "a landed plane should take off when told to do so from the correct airport");
    }

    @Test
    void unsuccessfulInFlight_takeOff() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> {
            inFlightPlane.takeOff("airport");
        });

        String expectedMessage = "This plane cannot take off as it is already in flight!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling a plane that is already in flight to take off");
    }

    @Test
    void unsuccessfulWrongAirport_takeOff() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> {
            landedPlane.takeOff("frenchAirport");
        });

        String expectedMessage = "The pane can't take off from an airport that it is not at";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling a plane that is already in flight to take off");
    }

    // prevents the state of the test objects being affected between tests
    private void resetTestObjects() {
        inFlightPlane = new Plane();
        landedPlane = new Plane("airport");
    }
}