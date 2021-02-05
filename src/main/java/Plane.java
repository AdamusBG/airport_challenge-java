public class Plane {

    private String airport;

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

    // methods for changing the plane's airport

    public void land(String airport) throws Exception {
        if (this.atAirport()) {
            throw new Exception("This plane is already at an airport!");
        } else { // further errors to throw when airport class implemented
            this.setAirport(airport); // also include call to airport methods here
        }
    }

    public void takeOff(String airport) throws Exception {
        if (!this.atAirport()) {
            throw new Exception("This plane cannot take off as it is already in flight!");
        } else if (this.getAirport() != airport) {
            throw new Exception("The pane can't take off from an airport that it is not at");
        } else { // further errors to throw when airport class implemented
            this.setAirport("none"); // also include call to airport method here
        }
    }

    private void setAirport(String airport) { this.airport = airport; }

}
