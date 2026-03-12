package cz.wz.marysidy.island.service;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class LocationService {
    private final Island island;
    private static final int THREADS = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executor = Executors.newFixedThreadPool(THREADS);

    public LocationService(Island island) {
        this.island = island;
    }

    public void forEachLocation(Consumer<Location> action) {
        island.forEachLocation(action);
    }

    public void parallelForEachLocation(Consumer<Location> action) {
        List<Future<?>> futures = new ArrayList<>();
        island.forEachLocation(location ->
                futures.add(executor.submit(() -> action.accept(location)))
        );

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
