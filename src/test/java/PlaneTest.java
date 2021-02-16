import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaneTest {

    Plane inFlightPlane;
    Plane landedPlane;
    Airport airportMock1;
    Airport airportMock2;

    @Test
    void getAirport() {
        resetTestObjects();
        assertEquals(null, inFlightPlane.getAirport(),"plane in flight asked its airport should return null");
        assertEquals(airportMock1, landedPlane.getAirport(), "plane at an airport should correctly return its airport when asked");
    }

    @Test
    void atAirport() {
        resetTestObjects();
        assertFalse(inFlightPlane.atAirport(), "when plane in flight is asked if it is at an airport, it should return false");
        assertTrue(landedPlane.atAirport(), "plane at an airport should return true when asked if it's at an airport");
    }

    @Test
    void successful_land() {
        resetTestObjects();
        try {
            inFlightPlane.land(airportMock1);
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }
        assertEquals(airportMock1, inFlightPlane.getAirport(), "an in-flight plane should successfully land and then correctly report its airport");
    }

    @Test
    void unsuccessful_land() {
        resetTestObjects();

        Exception exception = assertThrows(Exception.class, () -> landedPlane.land(airportMock2));

        String expectedMessage = "This plane is already at an airport!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling plane to land when already landed");
    }

    @Test
    void successful_takeOff() {
        resetTestObjects();
        try {
            landedPlane.takeOff(airportMock1);
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }
        assertEquals(null, inFlightPlane.getAirport(), "a landed plane should take off when told to do so from the correct airport");
    }

    @Test
    void unsuccessfulInFlight_takeOff() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> inFlightPlane.takeOff(airportMock1));

        String expectedMessage = "This plane cannot take off as it is already in flight!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling a plane that is already in flight to take off");
    }

    @Test
    void unsuccessfulWrongAirport_takeOff() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> landedPlane.takeOff(airportMock2));

        String expectedMessage = "The pane can't take off from an airport that it is not at";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling a plane to take off from an airport that it is not landed at");
    }

    // prevents the state of the test objects being affected between tests
    private void resetTestObjects() {
        airportMock1 = mock(Airport.class);
        airportMock2 = mock(Airport.class);

        inFlightPlane = new Plane();
        landedPlane = new Plane(airportMock1);
    }
}