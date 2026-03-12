package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Animal;
import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;
import cz.wz.marysidy.island.model.Plant;

import java.util.ArrayList;
import java.util.List;

public class CleanupPhase extends AbstractPhase {
    @Override
    protected void executeOnLocation(Island island, Location location) {
        List<Animal> animalsCopy = new ArrayList<>(location.getAnimals());
        List<Plant> plantsCopy = new ArrayList<>(location.getPlants());
        for (Animal animal : animalsCopy) {
            if (!animal.isAlive()) {
                location.removeAnimal(animal);
            }
        }

        for (Plant plant : plantsCopy) {
            if (!plant.isAlive()) {
                location.removePlant(plant);
            }
        }
    }
}