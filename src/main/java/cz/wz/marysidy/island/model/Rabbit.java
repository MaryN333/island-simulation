package cz.wz.marysidy.island.model;

public class Rabbit extends Herbivore {
    private static final double WEIGHT = 2;
    private static final int MAX_SPEED = 2;
    private static final double FOOD_REQUIRED = 0.45;
    private static final int MAX_COUNT_PER_LOCATION = 150;
    private static final double HUNGER_RATE = 0.15;

    public Rabbit() {
        super(WEIGHT, MAX_SPEED, FOOD_REQUIRED);
    }

    public int getMaxCountPerLocation() {
        return MAX_COUNT_PER_LOCATION;
    }

    @Override
    protected Animal createChild() {
        return new Rabbit();
    }


    @Override
    protected double getHungerRate() {
        return HUNGER_RATE;
    }

}
