import java.util.ArrayList;
import java.util.Random;

public class Airport {

    private final ArrayList<String> planesInHangar;
    private final int capacity;
    private final Random myRandom;

    // constructor methods, one for custom capacity and one for default capacity and then copies of the same methods allowing for dependency injection in tests

    public Airport(int capacity) {
        this.planesInHangar = new ArrayList<>();
        this.capacity = capacity;
        this.myRandom = new Random();
    }

    public Airport() {
        this.planesInHangar = new ArrayList<>();
        this.capacity = 20;
        this.myRandom = new Random();
    }

    public Airport(int capacity, Random injected) {
        this.planesInHangar = new ArrayList<>();
        this.capacity = capacity;
        this.myRandom = injected;
    }

    public Airport(Random injected) {
        this.planesInHangar = new ArrayList<>();
        this.capacity = 20;
        this.myRandom = injected;
    }

    // methods for telling the airport how to interact with planes

    public void landPane(String plane) throws Exception {
        if (!this.hasCapacity()) {
            throw new Exception("The pane can't land as the airport is at capacity");
        } else if (!this.hasGoodWeather()) {
            throw new Exception("The pane can't land at this airport due to stormy weather");
        } else {
            this.planesInHangar.add(plane);
        }
    }

    public void takeOffPlane(String plane) throws Exception {
        if (!this.planesInHangar.contains(plane)) {
            throw new Exception("The pane can't take off from an airport that it is not landed at");
        } else if (!this.hasGoodWeather()) {
            throw new Exception("The pane can't take off from the airport due to stormy weather");
        } else {
            this.planesInHangar.remove(plane);
        }
    }

    // method returning list of planes

    public ArrayList<String> getPlanesInHangar() {
        return this.planesInHangar;
    }

    // private methods

    private boolean hasCapacity() {
        return this.planesInHangar.size() < this.capacity;
    }

    private boolean hasGoodWeather() {
        int weatherRoll = myRandom.nextInt(20) + 1;
        return 1 != weatherRoll; // gives a 1 in 20 chance for bad weather
    }

}
