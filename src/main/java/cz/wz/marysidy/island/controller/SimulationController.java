package cz.wz.marysidy.island.controller;

import cz.wz.marysidy.island.engine.ParallelSimulation;
import cz.wz.marysidy.island.engine.SimulationEngine;
import cz.wz.marysidy.island.engine.SingleThreadSimulation;
import cz.wz.marysidy.island.model.Island;

public class SimulationController {
    private final Island island;
    private final SimulationEngine engine;

    public SimulationController(int width, int height, int ticks, boolean parallel) {
        this.island = new Island(width, height);

        island.populate();

        if (parallel) {
            this.engine = new ParallelSimulation(island, ticks);
        } else {
            this.engine = new SingleThreadSimulation(island, ticks);
        }
    }

    public void startSimulation() {
        engine.runSimulation();
    }
}
