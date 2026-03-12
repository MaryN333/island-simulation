package cz.wz.marysidy.island.model;

import java.util.Map;

public class Eagle extends Predator{
    private static final double WEIGHT = 6;
    private static final int MAX_SPEED = 3;
    private static final double FOOD_REQUIRED = 1;
    private static final int MAX_COUNT_PER_LOCATION = 20;
    private static final double HUNGER_RATE = 0.15;
    private static final int REPRODUCE_PROBABILITY = 12;

    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP =
            Map.of(Fox.class, 10, Rabbit.class, 90, Mouse.class, 90, Duck.class, 10);

    public Eagle() {
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
    public Animal createChild() {
        return new Eagle();
    }

    @Override
    protected double getHungerRate() {
        return HUNGER_RATE;
    }

    @Override
    public int getReproduceProbability() {
        return REPRODUCE_PROBABILITY;
    }
}
