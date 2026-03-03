package cz.wz.marysidy.island.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

    private void plantGrowthPhase() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location location = locations[y][x];
                int newPlants = random.nextInt(0, 3);

                for (int i = 0; i < newPlants; i++) {
                    location.addPlant(new Grass());
                }
            }
        }
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

        // Stage 2 — moving
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
                List<Animal> animalsCopy = location.getAnimals();

                for (Animal animal : animalsCopy) {
                    if (!animal.isAlive()) continue;
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
                location.getPlants().removeIf(p -> !p.isAlive());
            }
        }
    }

    public void reproducePhase() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location location = locations[y][x];
                Map<Class<? extends Animal>, List<Animal>> groups = new HashMap<>();

                for (Animal animal : location.getAnimals()) {
                    if (!animal.isAlive()) continue;
                    if (animal.isHungry()) continue;

                    groups.computeIfAbsent(animal.getClass(), k -> new ArrayList<>()).add(animal);
                }

                for (List<Animal> sameTypeAnimals : groups.values()) {
                    int pairs = sameTypeAnimals.size() / 2;
                    if (pairs == 0) continue;

                    Animal parent = sameTypeAnimals.get(0);
                    for (int i = 0; i < pairs; i++) {
                        int probability = parent.getReproduceProbability();
                        if (random.nextInt(100) < probability) {
                            Animal child = parent.createChild();
                            location.addAnimal(child);
                        }
                    }
                }
            }
        }
    }

    public void lifeCycle() {
        plantGrowthPhase();
        movePhase();
        eatPhase();
        reproducePhase();
        hungerPhase();
        cleanupPhase();
    }

    public Map<String, Integer> collectStatistics() {
        Map<String, Integer> stats = new HashMap<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location location = locations[y][x];

                for (Animal animal : location.getAnimals()) {
                    if (!animal.isAlive()) continue;

                    String name = animal.getClass().getSimpleName();
                    stats.put(name, stats.getOrDefault(name, 0) + 1);
                }

                for (Plant plant : location.getPlants()) {
                    if (!plant.isAlive()) continue;

                    String name = plant.getClass().getSimpleName();
                    stats.put(name, stats.getOrDefault(name, 0) + 1);
                }
            }
        }
        return stats;
    }

    public void printStatistics(int tickNumber) {
        Map<String, Integer> stats = collectStatistics();

        System.out.println("----- Tick " + tickNumber + " -----");
        if (stats.isEmpty()) {
            System.out.println("All animals are dead.");
            return;
        }

        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
