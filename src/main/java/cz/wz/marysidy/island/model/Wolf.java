package cz.wz.marysidy.island.model;

import java.util.HashMap;
import java.util.Map;

public class Wolf extends Predator {
    private static final double WEIGHT = 50;
    private static final int MAX_SPEED = 3;
    private static final double FOOD_REQUIRED = 8;
    private static final int MAX_COUNT_PER_LOCATION = 30;
    private static final double HUNGER_RATE = 0.2;

    private static final Map<Class<? extends Animal>, Integer> FOOD_MAP = new HashMap<>();

    static {
        FOOD_MAP.put(Rabbit.class, 60);
//        FOOD_MAP.put(Mouse.class, 60);
    }

    public Wolf() {
        super(WEIGHT, MAX_SPEED, FOOD_REQUIRED);
    }

    public int getMaxCountPerLocation() {
        return MAX_COUNT_PER_LOCATION;
    }

    @Override
    protected Animal createChild() {
        return new Wolf();
    }

    @Override
    protected int getEatProbability(Animal animal) {
        return FOOD_MAP.getOrDefault(animal.getClass(), 0);
        //for test eatPhase() in Island
//        if (animal instanceof Rabbit) {
//            return 100;
//        }
//        return 0;
    }

    @Override
    protected double getHungerRate() {
        return HUNGER_RATE;
    }

    @Override
    protected int getReproduceProbability() {
        return 20;
    }
}
