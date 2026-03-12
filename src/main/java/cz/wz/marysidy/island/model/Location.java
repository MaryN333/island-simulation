package cz.wz.marysidy.island.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private final int x;
    private final int y;

    private final List<Animal> animals;
    private final List<Plant> plants;
    private final Map<Class<? extends Animal>, Integer> animalCounts = new HashMap<>();
    private final Map<Class<? extends Plant>, Integer> plantCounts = new HashMap<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public boolean addAnimal(Animal animal) {
        Class<? extends Animal> type = animal.getClass();
        int count = animalCounts.getOrDefault(type, 0);

        if (count >= animal.getMaxCountPerLocation()) {
            return false;
        }
        animals.add(animal);
        animalCounts.put(type, count + 1);
        animal.moveTo(this);
        return true;
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        Class<? extends Animal> type = animal.getClass();
        int count = animalCounts.getOrDefault(type, 0);

        if (count > 1) {
            animalCounts.put(type, count - 1);
        } else {
            animalCounts.remove(type);
        }
    }

    public boolean addPlant(Plant plant) {
        Class<? extends Plant> type = plant.getClass();
        int count = plantCounts.getOrDefault(type, 0);

        if (count >= plant.getMaxCountPerLocation()) {
            return false;
        }
        plants.add(plant);
        plantCounts.put(type, count + 1);
        plant.setLocation(this);
        return true;
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
        Class<? extends Plant> type = plant.getClass();
        int count = plantCounts.getOrDefault(type, 0);
//        plantCounts.put(type, count - 1);
        if (count > 1) {
            plantCounts.put(type, count - 1);
        } else {
            plantCounts.remove(type);
        }
    }
}
