package cz.wz.marysidy.island.engine;

import cz.wz.marysidy.island.model.Island;

public class SingleThreadSimulation implements SimulationEngine {
    private final Island island;
    private final int totalTicks;

    public SingleThreadSimulation(Island island, int totalTicks) {
        this.island = island;
        this.totalTicks = totalTicks;
    }

    @Override
    public void runSimulation() {
        for (int tick = 1; tick <= totalTicks; tick++) {
            island.lifeCycle();
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

    }
}
