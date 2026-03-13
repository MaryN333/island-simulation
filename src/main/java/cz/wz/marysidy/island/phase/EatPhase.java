package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Animal;
import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EatPhase extends AbstractPhase {
    @Override
    protected void executeOnLocation(Island island, Location location) {
        List<Animal> animalsCopy = new ArrayList<>(location.getAnimals());
        Collections.shuffle(animalsCopy);

        for (Animal animal : animalsCopy) {
            if (!animal.isAlive()) continue;
            animal.eat(location);
        }
    }
}