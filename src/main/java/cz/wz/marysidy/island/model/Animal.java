package cz.wz.marysidy.island.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Organism{
    private final double weight;
    private final int maxSpeed;
    private final double foodRequired;

    private double currentFood;
    private volatile boolean alive = true;
    private Location location;

    private static final double EPSILON = 1e-9;

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
    public synchronized boolean tryDie() {
        if(!alive) return false;

        alive = false;
        return true;
    }

    protected void decreaseFood(double amount) {
        currentFood -= amount;
        if (currentFood <= EPSILON) {
            currentFood = 0;
            tryDie();
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
            default -> {}
        }
        return new MoveIntent(dx, dy);
    }

    public void applyMetabolism() {
        decreaseFood(foodRequired * getHungerRate());
    }

    protected boolean tryToEat(int probability) {
        return ThreadLocalRandom.current().nextInt(100) < probability;
    }

    public void eat(Location location) {
        if (!isHungry()) return;

        List<Organism> foodSources = new ArrayList<>(getFoodSources(location));

        if (foodSources.isEmpty()) return;

        for (Organism food : foodSources) {
            if (!food.isAlive()) continue;
            if (food == this) continue;

            int probability = getFoodMap().getOrDefault(food.getClass(), 0);

            if (probability > 0 && tryToEat(probability)) {
                food.tryDie();
                restoreFood(food.getWeight());
                break;
            }
        }
    }

    protected abstract Animal createChild();
    protected abstract double getHungerRate();
    protected abstract int getReproduceProbability();
    protected abstract Map<Class<? extends Organism>, Integer> getFoodMap();
    protected abstract List<? extends Organism> getFoodSources(Location location);
}
