package cz.wz.marysidy.island.model;

import java.util.Map;

public class Wolf extends Predator {
    private static final double WEIGHT = 50;
    private static final int MAX_SPEED = 3;
    private static final double FOOD_REQUIRED = 8;
    private static final int MAX_COUNT_PER_LOCATION = 30;
    private static final double HUNGER_RATE = 0.13;
    private static final int REPRODUCE_PROBABILITY = 25;

    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP =
            Map.of(Rabbit.class, 60, Mouse.class, 80);

    public Wolf() {
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
        return new Wolf();
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
