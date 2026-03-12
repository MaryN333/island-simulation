package cz.wz.marysidy.island.model;

import java.util.Map;

public class Mouse extends Herbivore {
    private static final double WEIGHT = 0.05;
    private static final int MAX_SPEED = 1;
    private static final double FOOD_REQUIRED = 0.01;
    private static final int MAX_COUNT_PER_LOCATION = 500;
    private static final double HUNGER_RATE = 0.2;
    private static final int REPRODUCE_PROBABILITY = 65;
    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP =
            Map.of(Grass.class, 100, Caterpillar.class, 90);

    public Mouse() {
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
        return new Mouse();
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
