package cz.wz.marysidy.island.model;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Organism{
    private final double weight;
    private final int maxSpeed;
    private final double foodRequired;

    private double currentFood;
    private boolean alive = true;
    private Location location;

    protected Animal(double weight, int maxSpeed, double foodRequired) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.foodRequired = foodRequired;
        this.currentFood = foodRequired;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getFoodRequired() {
        return foodRequired;
    }

    public double getCurrentFood() {
        return currentFood;
    }

    public Location getLocation() {
        return location;
    }

    void moveTo(Location newLocation) {
        this.location = newLocation;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void die() {
        alive = false;
    }

    public void decreaseFood(double amount) {
        currentFood -= amount;
        if (currentFood <= 0) {
            die();
        }
    }

    protected void restoreFood(double amount) {
        currentFood = Math.min(foodRequired, currentFood + amount);
    }

    public boolean isHungry() {
        return currentFood < foodRequired;
    }


    public MoveIntent decideMove() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int steps = random.nextInt(0, maxSpeed + 1);
        int direction = random.nextInt(4);

        int dx = 0;
        int dy = 0;

        switch (direction) {
            case 0 -> dx = steps;
            case 1 -> dx = -steps;
            case 2 -> dy = steps;
            case 3 -> dy = -steps;
        }
        return new MoveIntent(dx, dy);
    }

    public abstract void eat(Location location);

    protected abstract Animal createChild();

    public abstract int getMaxCountPerLocation();
}
