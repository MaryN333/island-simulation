package cz.wz.marysidy.island.model;

public class Grass extends Plant {
    private static final double WEIGHT = 1.0;
    public static final int MAX_COUNT_PER_LOCATION = 200;

    public Grass() {
        super(WEIGHT);
    }

    @Override
    public int getMaxCountPerLocation() {
        return MAX_COUNT_PER_LOCATION;
    }
}
