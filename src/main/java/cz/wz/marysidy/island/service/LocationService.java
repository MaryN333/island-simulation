package cz.wz.marysidy.island.service;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;

import java.util.function.Consumer;

public class LocationService {
    private final Island island;

    public LocationService(Island island) {
        this.island = island;
    }

    public void forEachLocation(Consumer<Location> action) {
        Location[][] locations = island.getLocations();
        int height = island.getHeight();
        int width = island.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                action.accept(locations[y][x]);
            }
        }
    }
}
