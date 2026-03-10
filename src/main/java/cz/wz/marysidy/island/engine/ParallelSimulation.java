package cz.wz.marysidy.island.engine;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.phase.*;
import cz.wz.marysidy.island.service.LocationService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelSimulation implements SimulationEngine {
    private static final int CORE_POOL_SIZE = 1;

    private final Island island;
    private final int totalTicks;
    private final LocationService locationService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

    private final List<SimulationPhase> phases = List.of(
            new PlantGrowthPhase(),
            new MovePhase(),
            new EatPhase(),
            new ReproducePhase(),
            new HungerPhase(),
            new CleanupPhase()
    );

    public ParallelSimulation(Island island, int totalTicks) {
        this.island = island;
        this.totalTicks = totalTicks;
        this.locationService = new LocationService(island);
    }

    @Override
    public void runSimulation() {
        AtomicInteger tick = new AtomicInteger(1);

        scheduler.scheduleAtFixedRate(() -> {
            int currentTick = tick.getAndIncrement();

            if (currentTick > totalTicks) {
                scheduler.shutdown();
                return;
            }

            for (SimulationPhase phase : phases) {
                phase.execute(island, locationService, true);
            }

            island.printStatistics(currentTick);

            if (island.collectStatistics().isEmpty()) {
                System.out.println("Simulation ended.");
                scheduler.shutdown();
            }

        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isParallel() {
        return true;
    }
}
