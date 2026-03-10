package cz.wz.marysidy.island;

import cz.wz.marysidy.island.controller.SimulationController;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SimulationController controller = new SimulationController(10, 10, 30, true);

        controller.startSimulation();
    }
}
