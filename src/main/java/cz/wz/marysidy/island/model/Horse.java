package cz.wz.marysidy.island.model;

import java.util.Map;

public class Horse extends Herbivore {
    private static final double WEIGHT = 400;
    private static final int MAX_SPEED = 4;
    private static final double FOOD_REQUIRED = 60;
    private static final int MAX_COUNT_PER_LOCATION = 20;
    private static final double HUNGER_RATE = 0.07;
    private static final int REPRODUCE_PROBABILITY = 40;
    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP = Map.of(Grass.class, 100);

    public Horse() {
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
        return new Horse();
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
