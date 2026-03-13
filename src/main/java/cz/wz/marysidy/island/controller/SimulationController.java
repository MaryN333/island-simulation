package cz.wz.marysidy.island.controller;

import cz.wz.marysidy.island.engine.Simulation;
import cz.wz.marysidy.island.engine.SimulationEngine;
import cz.wz.marysidy.island.model.Island;

public class SimulationController {
    private final Island island;
    private final SimulationEngine engine;

    public SimulationController(int width, int height, int ticks, boolean parallel) {
        this.island = new Island(width, height);
        island.populate();
        this.engine = new Simulation(island, ticks, parallel);
    }

    public void startSimulation() {
        engine.runSimulation();
    }
}
