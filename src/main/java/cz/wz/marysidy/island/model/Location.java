package cz.wz.marysidy.island.model;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final int x;
    private final int y;

    private final List<Animal> animals;
    private final List<Plant> plants;

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

    public boolean addAnimal(Animal animal){
        long count = animals.stream()
                .filter(a -> a.getClass() == animal.getClass())
                .count();

        if (count >= animal.getMaxCountPerLocation()) {
            return false;
        }

        animals.add(animal);
        animal.moveTo(this);
        return true;
    }

    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }

    public void addPlant(Plant plant){
        plants.add(plant);
        plant.setLocation(this);
    }
    public void removePlant(Plant plant){
        plants.remove(plant);
    }

}
