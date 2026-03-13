package cz.wz.marysidy.island.model;

import java.util.Map;

public class Goat extends Herbivore {
    private static final double WEIGHT = 60;
    private static final int MAX_SPEED = 3;
    private static final double FOOD_REQUIRED = 10;
    private static final int MAX_COUNT_PER_LOCATION = 140;
    private static final double HUNGER_RATE = 0.12;
    private static final int REPRODUCE_PROBABILITY = 55;
    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP = Map.of(Grass.class, 100);

    public Goat() {
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
        return new Goat();
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
