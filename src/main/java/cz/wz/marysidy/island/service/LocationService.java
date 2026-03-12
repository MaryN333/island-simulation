package cz.wz.marysidy.island.service;

import cz.wz.marysidy.island.config.SimulationConfig;
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
    private final ExecutorService executor = Executors.newFixedThreadPool(SimulationConfig.THREADS);

    public LocationService(Island island) {
        this.island = island;
    }

    public void forEachLocation(Consumer<Location> action, boolean parallel) {
        if (!parallel) {
            island.forEachLocation(action);
            return;
        }

        int height = island.getHeight();
        int chunk = (int) Math.ceil((double) height / SimulationConfig.THREADS);

        List<Future<?>> futures = new ArrayList<>();

        for (int t = 0; t < SimulationConfig.THREADS; t++) {
            int startY = t * chunk;
            int endY = Math.min(startY + chunk, height);

            if (startY >= height) break;

            futures.add(executor.submit(() -> {
                Location[][] locations = island.getLocations();

                for (int y = startY; y < endY; y++) {
                    for (int x = 0; x < island.getWidth(); x++) {
                        action.accept(locations[y][x]);
                    }
                }
            }));
        }
        waitFutures(futures);
    }

    private void waitFutures(List<Future<?>> futures) {
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
