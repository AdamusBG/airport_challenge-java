public class Plane {

    private Airport airport;

    // constructor methods, one making a plane in flight and another creating a plane at an airport

    public Plane(Airport airport) {
        this.airport = airport;
    }

    public Plane() {
        this.airport = null;
    }

    // methods for asking the plane about its airport

    public Airport getAirport() {
        return this.airport;
    }

    public boolean atAirport() {
        return this.airport != null;
    }

    // methods for changing the plane's airport

    public void land(Airport airport) throws Exception {
        if (this.atAirport()) {
            throw new Exception("This plane is already at an airport!");
        } else { // further errors to throw when airport class implemented
            this.setAirport(airport); // also include call to airport methods here
        }
    }

    public void takeOff(Airport airport) throws Exception {
        if (!this.atAirport()) {
            throw new Exception("This plane cannot take off as it is already in flight!");
        } else if (!this.getAirport().equals(airport)) {
            throw new Exception("The pane can't take off from an airport that it is not at");
        } else { // further errors to throw when airport class implemented
            this.setAirport(null); // also include call to airport method here
        }
    }

    private void setAirport(Airport airport) { this.airport = airport; }

}
