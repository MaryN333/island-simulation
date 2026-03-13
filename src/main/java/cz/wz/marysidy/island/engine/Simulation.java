package cz.wz.marysidy.island.engine;

import cz.wz.marysidy.island.config.SimulationConfig;
import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.phase.*;
import cz.wz.marysidy.island.service.LocationService;
import cz.wz.marysidy.island.service.StatisticsService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation implements SimulationEngine {
    private final Island island;
    private final int totalTicks;
    private final boolean parallel;
    private final LocationService locationService;
    private final StatisticsService statisticsService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> simulationTask;
    private final AtomicInteger tickCounter = new AtomicInteger(0);

    private final List<SimulationPhase> phases = List.of(
            new PlantGrowthPhase(),
            new MovePhase(),
            new EatPhase(),
            new ReproducePhase(),
            new HungerPhase(),
            new CleanupPhase()
    );

    public Simulation(Island island, int totalTicks, boolean parallel) {
        this.island = island;
        this.totalTicks = totalTicks;
        this.parallel = parallel;
        this.locationService = new LocationService(island);
        this.statisticsService = new StatisticsService(island);
    }

    @Override
    public void runSimulation() {
        simulationTask = scheduler.scheduleAtFixedRate(() -> {
            int tick = tickCounter.incrementAndGet();

            if (tick > totalTicks) {
                shutdown();
                return;
            }

            for (SimulationPhase phase : phases) {
                phase.execute(island, locationService, parallel);
            }

            statisticsService.printStatistics(tick);

            if (statisticsService.collectStatistics().isEmpty()) {
                System.out.println("Simulation ended.");
                shutdown();
            }
        }, 0, SimulationConfig.TICK_DELAY_MS, TimeUnit.MILLISECONDS);
    }

    private void shutdown() {
        if (simulationTask != null) {
            simulationTask.cancel(false);
        }
        scheduler.shutdown();
        locationService.shutdown();
    }
}
