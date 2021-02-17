import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaneTest {

    Plane inFlightPlane;
    Plane landedPlane;
    Airport airportMock1;
    Airport airportMock2;
    Airport badWeatherAirportMock;

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
    void unsuccessfulAlreadyLanded_land() {
        resetTestObjects();

        Exception exception = assertThrows(Exception.class, () -> landedPlane.land(airportMock2));

        String expectedMessage = "This plane is already at an airport!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling plane to land when already landed");
    }

    @Test
    void unsuccessfulBadWeather_land() {
        resetTestObjects();

        Exception exception = assertThrows(Exception.class, () -> inFlightPlane.land(badWeatherAirportMock));

        String expectedMessage = "The plane cannot land due to stormy weather!";
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

    @Test
    void unsuccessfulBadWeather_takeOff() {
        resetTestObjects();
        landPlaneAtBadWeatherAirport(); // lands 'inFlightPlane' at 'badWeatherAirportMock'

        Exception exception = assertThrows(Exception.class, () -> inFlightPlane.takeOff(badWeatherAirportMock));

        String expectedMessage = "The plane cannot take off due to stormy weather!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling a plane to take off from an airport that it is landed at in bad weather");
    }

    // prevents the state of the test objects being affected between tests
    private void resetTestObjects() {
        airportMock1 = mock(Airport.class);
        airportMock2 = mock(Airport.class);

        when(airportMock1.hasGoodWeather()).thenReturn(true);
        when(airportMock2.hasGoodWeather()).thenReturn(true);

        badWeatherAirportMock = mock(Airport.class);

        when(badWeatherAirportMock.hasGoodWeather()).thenReturn(false);

        inFlightPlane = new Plane();
        landedPlane = new Plane(airportMock1);
    }

    private void landPlaneAtBadWeatherAirport() {
        when(badWeatherAirportMock.hasGoodWeather()).thenReturn(true);
        try {
            inFlightPlane.land(badWeatherAirportMock);
        } catch (Exception e) {
            System.out.println("if you're seeing this then landing plane at the bad weather airport mock has been unsuccessful");
        }
        when(badWeatherAirportMock.hasGoodWeather()).thenReturn(false);
    }
}