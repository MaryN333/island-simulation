package cz.wz.marysidy.island.model;

import java.util.Map;

public class Fox extends Predator{
    private static final double WEIGHT = 8;
    private static final int MAX_SPEED = 2;
    private static final double FOOD_REQUIRED = 2;
    private static final int MAX_COUNT_PER_LOCATION = 30;
    private static final double HUNGER_RATE = 0.12;
    private static final int REPRODUCE_PROBABILITY = 35;
    private static final Map<Class<? extends Organism>, Integer> FOOD_MAP =
            Map.of(Rabbit.class, 70, Mouse.class, 90, Caterpillar.class, 40);

    public Fox() {
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
        return new Fox();
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
