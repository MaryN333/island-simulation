package cz.wz.marysidy.island;

import cz.wz.marysidy.island.model.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int totalTicks = 100;
        Island island = new Island(5, 5);
        island.populate();

        for (int tick = 1; tick <= totalTicks; tick++) {
            island.lifeCycle();
            island.printStatistics(tick);

            if (island.collectStatistics().isEmpty()) {
                System.out.println("Simulation ended.");
                break;
            }

            Thread.sleep(200);
        }
    }
}
