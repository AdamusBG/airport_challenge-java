import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    Airport defaultAirport;
    Airport zeroCapacityAirport;

    @Test
    void emptyHangar() {
        resetTestObjects();
        assertEquals(0, defaultAirport.getPlanesInHangar().size(), "airport should have no planes in hangar after instantiation");
        assertEquals(0, zeroCapacityAirport.getPlanesInHangar().size(), "airport should have no planes in hangar after instantiation");
    }

    @Test
    void successful_landPlane() {
        resetTestObjects();
        String plane = "Real Plane";
        try {
            defaultAirport.landPane(plane);
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }
        assertEquals(1, defaultAirport.getPlanesInHangar().size(), "airport should have one plane in hangar after one landed");
        assertEquals(plane, defaultAirport.getPlanesInHangar().get(0), "correct plane is added to hangar after landing");
    }

    @Test
    void unsuccessfulAtCapacity_landPlane() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> {
            zeroCapacityAirport.landPane("Normal plane");
        });

        String expectedMessage = "The pane can't land as the airport is at capacity";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling trying to land plane in at capacity airport");
    }

    @Test
    void successful_takeOffPlane() {
        resetTestObjects();
        String plane = "Real Plane";
        try {
            defaultAirport.landPane(plane); // behaviour of this has already been tested above
            defaultAirport.takeOffPlane(plane);
        } catch (Exception e) {
            System.out.println("if you're seeing this something is really wrong");
        }
        assertEquals(0, defaultAirport.getPlanesInHangar().size(), "airport should have 0 planes in hangar after one landed and then taken off");
    }

    @Test
    void unsuccessfulPlaneNotAtAirport_takeOffPlane() {
        resetTestObjects();
        Exception exception = assertThrows(Exception.class, () -> {
            defaultAirport.takeOffPlane("Normal plane");
        });

        String expectedMessage = "The pane can't take off from an airport that it is not landed at";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "correct error message is given when telling airport to take off a plane that is not in its hangar");
    }

    // prevents the state of the test objects being affected between tests
    private void resetTestObjects() {

        class FakeRandom extends Random {
            @Override
            public int nextInt(int number) {
                return 1; // this means that when the good weather method is called it will always return true
            }
        }

        defaultAirport = new Airport(new FakeRandom());
        zeroCapacityAirport = new Airport(0, new FakeRandom());
    }

}
