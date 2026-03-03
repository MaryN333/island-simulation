package cz.wz.marysidy.island.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Predator extends Animal {

    protected Predator(double weight, int maxSpeed, double foodRequired) {
        super(weight, maxSpeed, foodRequired);
    }

    @Override
    public void eat(Location location) {
        if (!isHungry()) return;

        List<Animal> animalsCopy = new ArrayList<>(location.getAnimals());

        for (Animal candidate : animalsCopy) {
            if (candidate == this) continue;
            if (!candidate.isAlive()) continue;

            int probability = getFoodMap().getOrDefault(candidate.getClass(), 0);

            if (probability > 0 && tryToEat(probability)) {
                candidate.die();
                restoreFood(candidate.getWeight());
                break;
            }
        }
    }
}
