package cz.wz.marysidy.island.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public void populate() {
        forEachLocation(location -> {
            addRandomOrganisms(location, Grass::new);
            addRandomOrganisms(location, Mouse::new);
            addRandomOrganisms(location, Rabbit::new);
            addRandomOrganisms(location, Fox::new);
            addRandomOrganisms(location, Wolf::new);
        });
    }

//    public void lifeCycle() {
//        runPhase(this::plantGrowthPhase);
//        movePhase();
//        runPhase(this::eatPhase);
//        runPhase(this::reproducePhase);
//        runPhase(this::hungerPhase);
//        runPhase(this::cleanupPhase);
//    }

    private void runPhase(Consumer<Location> action) {
        forEachLocation(action);
    }

    public void forEachLocation(Consumer<Location> action) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                action.accept(locations[y][x]);
            }
        }
    }

    private <T extends Organism> void addRandomOrganisms(Location location, Supplier<T> factory) {
        T sample = factory.get();
        int max = sample.getMaxCountPerLocation();
        // 2–5%
        int min = Math.max(1, (int) (max * 0.02));
        int upper = Math.max(min + 1, (int) (max * 0.05));

        int count = ThreadLocalRandom.current().nextInt(min, upper);

        for (int i = 0; i < count; i++) {
            T organism = factory.get();

            if (organism instanceof Plant plant) {
                location.addPlant(plant);
            } else if (organism instanceof Animal animal) {
                location.addAnimal(animal);
            }
        }
    }

    public void plantGrowthPhase(Location location) {
        int newPlants = ThreadLocalRandom.current().nextInt(0, 3);

        for (int i = 0; i < newPlants; i++) {
            location.addPlant(new Grass());
        }
    }

    public void movePhase() {
        // Stage 1 - intentions
        Map<Animal, MoveIntent> moveIntents = new HashMap<>();
        forEachLocation(location -> {
            List<Animal> animals = location.getAnimals();

            for (Animal animal : animals) {
                if (!animal.isAlive()) continue;

                MoveIntent intent = animal.decideMove();
                moveIntents.put(animal, intent);
            }
        });

        // Stage 2 — moving
        for (Map.Entry<Animal, MoveIntent> entry : moveIntents.entrySet()) {
            Animal animal = entry.getKey();
            MoveIntent intent = entry.getValue();
            Location currentLocation = animal.getLocation();
            int currentX = currentLocation.getX();
            int currentY = currentLocation.getY();

            int targetX = currentX + intent.getDx();
            int targetY = currentY + intent.getDy();

            if (!isValidCoordinate(targetX, targetY)) {
                continue; // doesn`t move
            }

            Location targetLocation = getLocation(targetX, targetY);

            boolean added = targetLocation.addAnimal(animal);
            if (added) {
                currentLocation.removeAnimal(animal);
            }
        }
    }

    public void eatPhase(Location location) {
        List<Animal> animalsCopy = new ArrayList<>(location.getAnimals());
        Collections.shuffle(animalsCopy);

        for (Animal animal : animalsCopy) {
            if (!animal.isAlive()) continue;
            animal.eat(location);
        }
    }

    public void reproducePhase(Location location) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Map<Class<? extends Animal>, List<Animal>> groups = new HashMap<>();

        for (Animal animal : location.getAnimals()) {
            if (!animal.isAlive()) continue;
            if (animal.isHungry()) continue;

            groups.computeIfAbsent(animal.getClass(), k -> new ArrayList<>()).add(animal);
        }

        for (List<Animal> sameTypeAnimals : groups.values()) {
            Collections.shuffle(sameTypeAnimals);
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

    public void hungerPhase(Location location) {
        List<Animal> animalsCopy = new ArrayList<>(location.getAnimals());

        for (Animal animal : animalsCopy) {
            if (!animal.isAlive()) continue;
            animal.applyMetabolism();
        }
    }

    public void cleanupPhase(Location location) {
        location.getAnimals().removeIf(animal -> !animal.isAlive());
        location.getPlants().removeIf(plant -> !plant.isAlive());
    }

    public Map<String, Integer> collectStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        forEachLocation(location -> {
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
        });
        return stats;
    }

    public void printStatistics(int tick) {
        System.out.println("----- Tick " + tick + " -----");
        collectStatistics().forEach((k, v) ->
                System.out.println(k + ": " + v));
    }
}
