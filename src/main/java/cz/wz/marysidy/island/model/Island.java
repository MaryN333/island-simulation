package cz.wz.marysidy.island.model;

import cz.wz.marysidy.island.config.SimulationConfig;
import cz.wz.marysidy.island.factory.OrganismFactory;

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
            for (Supplier<? extends Organism> supplier : OrganismFactory.getOrganismSuppliers()) {
                addRandomOrganisms(location, supplier);
            }
        });
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
        int min = Math.max(1, (int) (max * SimulationConfig.INITIAL_MIN_PERCENT));
        int upper = Math.max(min + 1, (int) (max * SimulationConfig.INITIAL_MAX_PERCENT));

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
}
