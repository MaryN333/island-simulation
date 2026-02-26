package cz.wz.marysidy.island.model;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator {
    private static final double WEIGHT = 50;
    private static final int MAX_SPEED = 3;
    private static final double FOOD_REQUIRED = 8;
    private static final int MAX_COUNT_PER_LOCATION = 30;

    public Wolf() {
        super(WEIGHT, MAX_SPEED, FOOD_REQUIRED);
    }

    public int getMaxCountPerLocation() {
        return MAX_COUNT_PER_LOCATION;
    }

    @Override
    public MoveIntent decideMove() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int dx = random.nextInt(-MAX_SPEED, MAX_SPEED + 1);
        int dy = random.nextInt(-MAX_SPEED, MAX_SPEED + 1);

        return new MoveIntent(dx, dy);
    }

    @Override
    public void eat(Location location) {
    }

    @Override
    protected Animal createChild() {
        return new Wolf();
    }
}
