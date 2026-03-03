package cz.wz.marysidy.island.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Herbivore extends Animal {

    protected Herbivore(double weight, int maxSpeed, double foodRequired) {
        super(weight, maxSpeed, foodRequired);
    }

    @Override
    public void eat(Location location) {
        if (!isHungry()) return;

        List<Plant> plantsCopy = new ArrayList<>(location.getPlants());
        if (plantsCopy.isEmpty()) return;

        for (Plant plant : plantsCopy) {
            if (!plant.isAlive()) continue;

            int probability = getFoodMap().getOrDefault(plant.getClass(), 0);
            if (probability > 0 && tryToEat(probability)) {
                plant.die();
                restoreFood(plant.getWeight());
                break;
            }
        }
    }
}
