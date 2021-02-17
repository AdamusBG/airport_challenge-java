import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeatureTest {

    Plane plane1;
    Plane plane2;
    Plane plane3;
    Plane plane4;
    Plane plane5;

    Airport airport1;
    Airport airport2;
    Airport airport3;

    @Test
    void fivePlanesAndThreeAirports() {
        resetTestObjects();

        try {
            plane1.land(airport1);
            plane2.land(airport1);
            plane3.land(airport2);
            plane4.land(airport2);
            plane5.land(airport2);

            plane1.takeOff(airport1);
            plane2.takeOff(airport1);
            plane3.takeOff(airport2);
            plane4.takeOff(airport2);
            plane5.takeOff(airport2);

            plane1.land(airport3);
            plane2.land(airport3);
            plane3.land(airport3);
            plane4.land(airport3);
            plane5.land(airport3);
        } catch (Exception e) {
            System.out.println("This will never be triggered");
        }

        assertEquals(0, airport1.getPlanesInHangar().size(), "airport1 should have no planes landed");
        assertEquals(0, airport2.getPlanesInHangar().size(), "airport2 should have no planes landed");
        assertEquals(5, airport3.getPlanesInHangar().size(), "airport3 should have five planes landed");

        assertEquals(airport3, plane1.getAirport(), "plane1 should be landed at airport3");
        assertEquals(airport3, plane2.getAirport(), "plane2 should be landed at airport3");
        assertEquals(airport3, plane3.getAirport(), "plane3 should be landed at airport3");
        assertEquals(airport3, plane4.getAirport(), "plane4 should be landed at airport3");
        assertEquals(airport3, plane5.getAirport(), "plane5 should be landed at airport3");
    }

    // prevents the state of the test objects being affected between tests
    private void resetTestObjects() {
        plane1 = new Plane();
        plane2 = new Plane();
        plane3 = new Plane();
        plane4 = new Plane();
        plane5 = new Plane();

        Random spyRandom = spy(new Random());
        when(spyRandom.nextInt(20)).thenReturn(1);

        airport1 = new Airport(spyRandom);
        airport2 = new Airport(spyRandom);
        airport3 = new Airport(spyRandom);
    }
}