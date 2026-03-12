package cz.wz.marysidy.island.engine;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.phase.*;
import cz.wz.marysidy.island.service.LocationService;

import java.util.List;

public class Simulation implements SimulationEngine {
    private final Island island;
    private final int totalTicks;
    private final boolean parallel;
    private final LocationService locationService;

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
    }

    @Override
    public void runSimulation() {
        for (int tick = 1; tick <= totalTicks; tick++) {
            for (SimulationPhase phase : phases) {
                phase.execute(island, locationService, parallel);
            }

            island.printStatistics(tick);

            if (island.collectStatistics().isEmpty()) {
                System.out.println("Simulation ended.");
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        locationService.shutdown();
    }
}
