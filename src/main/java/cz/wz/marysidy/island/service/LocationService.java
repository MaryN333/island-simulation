package cz.wz.marysidy.island.service;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class LocationService {
    private final Island island;
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    public LocationService(Island island) {
        this.island = island;
    }

    public void forEachLocation(Consumer<Location> action) {
        island.forEachLocation(action);
    }

    public void parallelForEachLocation(Consumer<Location> action) {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        island.forEachLocation(location -> executor.submit(() -> action.accept(location)));
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
