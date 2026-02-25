package cz.wz.marysidy.island.model;

public abstract class Plant implements Organism {

    private final double weight;
    private boolean alive;
    private Location location;

    protected Plant(double weight) {
        this.weight = weight;
        this.alive = true;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void die() {
        this.alive = false;
    }

    public Location getLocation() {
        return location;
    }

    void setLocation(Location location) {
        this.location = location;
    }
}
