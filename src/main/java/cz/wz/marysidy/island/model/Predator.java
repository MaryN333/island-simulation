package cz.wz.marysidy.island.model;

import java.util.List;

public abstract class Predator extends Animal {
    protected Predator(double weight, int maxSpeed, double foodRequired) {
        super(weight, maxSpeed, foodRequired);
    }

    @Override
    protected List<? extends Organism> getFoodSources(Location location) {
        return location.getAnimals();
    }
}
