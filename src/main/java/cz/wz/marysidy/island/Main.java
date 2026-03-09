package cz.wz.marysidy.island;

import cz.wz.marysidy.island.engine.SingleThreadSimulation;
import cz.wz.marysidy.island.model.Island;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Island island = new Island(5, 5);
        island.populate();

        SingleThreadSimulation simulation = new SingleThreadSimulation(island, 100);
        simulation.runSimulation();
    }
}
