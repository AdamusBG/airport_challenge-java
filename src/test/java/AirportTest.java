import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirportTest {

    Airport defaultAirport;
    Airport zeroCapacityAirport;
    Airport badWeatherAirport;
    Plane planeMock;

    @Test
    void emptyHangar() {
        resetTestObjects();
        assertEquals(0, defaultAirport.getPlanesInHangar().size(), "airport should have no planes in hangar after instantiation");
        assertEquals(0, zeroCapacityAirport.getPlanesInHangar().size(), "airport should have no planes in hangar after instantiation");
    }

    @Test
    void successful_landPlane() {
        resetTestObjects();

        try {
            defaultAirport.landPane(planeMock);
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }

        assertEquals(1, defaultAirport.getPlanesInHangar().size(), "airport should have one plane in hangar after one landed");
        assertEquals(planeMock, defaultAirport.getPlanesInHangar().get(0), "correct plane is added to hangar after landing");
    }

    @Test
    void unsuccessfulAtCapacity_landPlane() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> zeroCapacityAirport.landPane(planeMock));

        String expectedMessage = "The pane can't land as the airport is at capacity";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling trying to land plane in at capacity airport");
    }

    @Test
    void unsuccessfulBadWeather_landPlane() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> badWeatherAirport.landPane(planeMock));

        String expectedMessage = "The pane can't land at this airport due to stormy weather";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling trying to land plane in poor weather");
    }

    @Test
    void successful_takeOffPlane() {
        resetTestObjects();

        try {
            defaultAirport.landPane(planeMock); // behaviour of this has already been tested above
            defaultAirport.takeOffPlane(planeMock);
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }

        assertEquals(0, defaultAirport.getPlanesInHangar().size(), "airport should have 0 planes in hangar after one landed and then taken off");
    }

    @Test
    void unsuccessfulPlaneNotAtAirport_takeOffPlane() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> defaultAirport.takeOffPlane(planeMock));

        String expectedMessage = "The pane can't take off from an airport that it is not landed at";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling airport to take off a plane that is not in its hangar");
    }

    @Test
    void unsuccessfulBadWeather_takeOffPlane() {
        resetTestObjects();

        landPlaneAtBadWeatherAirport();

        Exception exception = assertThrows(Exception.class, () -> badWeatherAirport.takeOffPlane(planeMock));

        String expectedMessage = "The pane can't take off from the airport due to stormy weather";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling airport to take off a plane that is in its hangar but during stormy weather");
    }

    // prevents the state of the test objects being affected between tests
    private void resetTestObjects() {

        Random fakeRandomAlwaysGood = mock(Random.class);
        when(fakeRandomAlwaysGood.nextInt(20)).thenReturn(2);

        defaultAirport = new Airport(fakeRandomAlwaysGood);
        zeroCapacityAirport = new Airport(0, fakeRandomAlwaysGood);

        Random fakeRandomAlwaysBad = mock(Random.class);
        when(fakeRandomAlwaysBad.nextInt(20)).thenReturn(0);

        badWeatherAirport = new Airport(fakeRandomAlwaysBad);

        planeMock = mock(Plane.class);
    }

    private void landPlaneAtBadWeatherAirport() {
        Random spyRandom = spy(new Random());
        badWeatherAirport = new Airport(spyRandom);

        when(spyRandom.nextInt(20)).thenReturn(1);

        try {
            badWeatherAirport.landPane(planeMock);
        } catch (Exception e) {
            System.out.println("if you're seeing this then the spy allowing a plane to land hasn't worked correctly");
        }

        when(spyRandom.nextInt(20)).thenReturn(0);
    }

}
