package cz.wz.marysidy.island.model;

import java.util.Map;

public class Bear extends Predator {
    private static final double WEIGHT = 500;
    private static final int MAX_SPEED = 2;
    private static final double FOOD_REQUIRED = 80;
    private static final int MAX_COUNT_PER_LOCATION = 5;
    private static final double HUNGER_RATE = 0.08;
    private static final int REPRODUCE_PROBABILITY = 6;

    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP =
            Map.of(Anaconda.class, 80, Horse.class, 40, Deer.class, 80,
                    Rabbit.class, 80, Mouse.class, 90, Goat.class, 70,
                    Sheep.class, 70, Boar.class, 50, Buffalo.class, 20, Duck.class, 10);

    public Bear() {
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
        return new Bear();
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
