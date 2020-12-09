public class Plane {

    public String airport;

    // constructor methods, one making a plane in flight and another creating a plane at an airport

    public Plane(String airport) {
        this.airport = airport;
    }

    public Plane() {
        this.airport = "none";
    }

    // methods for asking the plane about its airport

    public String getAirport() {
        return this.airport;
    }

    public boolean atAirport() {
        if (this.airport == "none") {
            return false;
        } else {
            return true;
        }
    }

}
