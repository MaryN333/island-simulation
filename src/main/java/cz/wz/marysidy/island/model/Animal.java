package cz.wz.marysidy.island.model;

public abstract class Animal implements Organism{
    private final double weight;
    private final int maxSpeed;
    private final double foodRequired;

    private double currentFood;
    private boolean alive;
    private Location location;

    public Animal(double weight, int maxSpeed, double foodRequired) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.foodRequired = foodRequired;

        this.currentFood = foodRequired;
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
        alive = false;
    }

    public Location getLocation() {
        return location;
    }

    public void restoreFood(double amount) {
        currentFood = Math.min(foodRequired, currentFood + amount);
    }

    public boolean isHungry() {
        return currentFood < foodRequired;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    void moveTo(Location newLocation) {
        this.location = newLocation;
    }

    public abstract MoveIntent decideMove();
}
