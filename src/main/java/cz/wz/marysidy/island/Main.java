package cz.wz.marysidy.island;

import cz.wz.marysidy.island.config.SimulationConfig;
import cz.wz.marysidy.island.controller.SimulationController;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SimulationController controller = new SimulationController(
                SimulationConfig.DEFAULT_WIDTH, SimulationConfig.DEFAULT_HEIGHT,
                SimulationConfig.DEFAULT_TICKS, true);

        controller.startSimulation();
    }
}
