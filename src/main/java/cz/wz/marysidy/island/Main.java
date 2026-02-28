package cz.wz.marysidy.island;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;
import cz.wz.marysidy.island.model.Wolf;

public class Main {

    public static void main(String[] args) {

        Island island = new Island(5, 5);

        Location start = island.getLocation(2, 2);
        Wolf wolf = new Wolf();
        start.addAnimal(wolf);

        System.out.println("Before move:");
        printIsland(island);

        System.out.println("Wolf location: " +
                wolf.getLocation().getX() + ", " +
                wolf.getLocation().getY());




        island.movePhase();

        System.out.println("\nAfter move:");
        printIsland(island);



        System.out.println("Wolf location: " +
                wolf.getLocation().getX() + ", " +
                wolf.getLocation().getY());
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
