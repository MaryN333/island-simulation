package cz.wz.marysidy.island.model;

import java.util.Map;

public class Caterpillar extends Herbivore {
    private static final double WEIGHT = 0.01;
    private static final int MAX_SPEED = 0;
    private static final double FOOD_REQUIRED = 0.001;
    private static final int MAX_COUNT_PER_LOCATION = 1000;
    private static final double HUNGER_RATE = 0.02;
    private static final int REPRODUCE_PROBABILITY = 60;
    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP = Map.of(Grass.class, 100);

    public Caterpillar() {
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
        return new Caterpillar();
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
