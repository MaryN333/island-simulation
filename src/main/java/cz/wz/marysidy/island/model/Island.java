package cz.wz.marysidy.island.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location(x, y);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Location[][] getLocations() {
        return locations;
    }

    public Location getLocation(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            throw new IllegalArgumentException("Invalid coordinates: " + x + ", " + y);
        }
        return locations[y][x];
    }

    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void movePhase() {
        // Stage 1 - intentions
        Map<Animal, int[]> moveIntents = new HashMap<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location location = locations[y][x];
                List<Animal> animals = location.getAnimals();

                for (Animal animal : animals) {
                    if (!animal.isAlive()) continue;

                    MoveIntent intent = animal.decideMove();
                    int targetX = x + intent.getDx();
                    int targetY = y + intent.getDy();

                    moveIntents.put(animal, new int[]{targetX, targetY});
                }
            }
        }

        // Stage 2 — mooving
        for (Map.Entry<Animal, int[]> entry : moveIntents.entrySet()) {
            Animal animal = entry.getKey();
            int targetX = entry.getValue()[0];
            int targetY = entry.getValue()[1];

            if (!isValidCoordinate(targetX, targetY)) {
                continue; // doesn`t move
            }

            Location currentLocation = animal.getLocation();
            Location targetLocation = getLocation(targetX, targetY);

            boolean added = targetLocation.addAnimal(animal);
            if (added) {
                currentLocation.removeAnimal(animal);
            }
        }
    }

    public void eatPhase() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Location location = locations[y][x];

//                List<Animal> animalsCopy = new ArrayList<>(location.getAnimals());
                List<Animal> animalsCopy = location.getAnimals();

                for (Animal animal : animalsCopy) {
                    if (!animal.isAlive()) {
                        continue;
                    }
                    animal.eat(location);
                }
            }
        }
    }

    public void hungerPhase() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location location = locations[y][x];
//                List<Animal> animalsCopy = new ArrayList<>(location.getAnimals());
                List<Animal> animalsCopy = location.getAnimals();

                for (Animal animal : animalsCopy) {
                    animal.applyMetabolism();
                }
            }
        }
    }

    public void cleanupPhase() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location location = locations[y][x];
                location.getAnimals().removeIf(animal -> !animal.isAlive());
            }
        }
    }

    public void reproducePhase() {
    }


}
