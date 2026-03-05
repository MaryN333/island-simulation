package cz.wz.marysidy.island.model;

public abstract class Plant implements Organism {

    private final double weight;
    private volatile boolean alive;
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
    public synchronized boolean tryDie() {
        if(!alive) return false;

        alive = false;
        return true;
    }

    public Location getLocation() {
        return location;
    }

    void setLocation(Location location) {
        this.location = location;
    }
}
