package cz.wz.marysidy.island;

import cz.wz.marysidy.island.model.*;

public class Main {
    public static void main(String[] args) {
        int totalTicks = 100;
        Island island = new Island(5, 5);

        Location startLocation = island.getLocation(2, 2);
        for (int i = 0; i < 10; i++) {
            startLocation.addPlant(new Grass());
        }

        for (int i = 0; i < 5; i++) {
            startLocation.addAnimal(new Wolf());
        }

        for (int i = 0; i < 20; i++) {
            startLocation.addAnimal(new Rabbit());
        }

        for (int tick = 1; tick <= totalTicks; tick++) {
            island.lifeCycle();
            island.printStatistics(tick);

            if (island.collectStatistics().isEmpty()) {
                System.out.println("Simulation ended.");
                break;
            }
        }
    }

    private static void printLocation(Location location) {
        System.out.println("Animals in location:");
        for (Animal animal : location.getAnimals()) {
            System.out.println(" - " + animal.getClass().getSimpleName()
                    + " alive=" + animal.isAlive());
        }
    }

    private static void printIsland(Island island) {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location loc = island.getLocation(x, y);
                System.out.print(loc.getAnimals().size() + " ");
            }
            System.out.println();
        }
    }
}
