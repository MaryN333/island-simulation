package cz.wz.marysidy.island.model;

import java.util.Map;

public class Anaconda extends Predator {
    private static final double WEIGHT = 15;
    private static final int MAX_SPEED = 1;
    private static final double FOOD_REQUIRED = 3;
    private static final int MAX_COUNT_PER_LOCATION = 30;
    private static final double HUNGER_RATE = 0.08;
    private static final int REPRODUCE_PROBABILITY = 15;
    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP =
            Map.of(Fox.class, 15, Rabbit.class, 20, Mouse.class, 40, Duck.class, 10);

    public Anaconda() {
        super(WEIGHT, MAX_SPEED, FOOD_REQUIRED);
    }

    @Override
    public int getMaxCountPerLocation() {
        return MAX_COUNT_PER_LOCATION;
    }

    @Override
    protected Map<Class<? extends Organism>, Integer> getFoodMap() {
        return FOOD_MAP;
    }

    @Override
    protected Animal createChild() {
        return new Anaconda();
    }

    @Override
    protected double getHungerRate() {
        return HUNGER_RATE;
    }

    @Override
    protected int getReproduceProbability() {
        return REPRODUCE_PROBABILITY;
    }
}
