package cz.wz.marysidy.island.model;

import java.util.Map;

public class Boar extends Predator {
    private static final double WEIGHT = 400;
    private static final int MAX_SPEED = 2;
    private static final double FOOD_REQUIRED = 50;
    private static final int MAX_COUNT_PER_LOCATION = 50;
    private static final double HUNGER_RATE = 0.09;
    private static final int REPRODUCE_PROBABILITY = 30;
    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP =
            Map.of(Mouse.class, 50, Caterpillar.class, 90, Grass.class, 100);

    public Boar() {
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
        return new Boar();
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
