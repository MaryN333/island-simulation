package cz.wz.marysidy.island.model;

import java.util.List;

public abstract class Herbivore extends Animal {
    protected Herbivore(double weight, int maxSpeed, double foodRequired) {
        super(weight, maxSpeed, foodRequired);
    }

    @Override
    protected List<? extends Organism> getFoodSources(Location location) {
        return location.getPlants();
    }
}
