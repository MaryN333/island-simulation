package cz.wz.marysidy.island;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;
import cz.wz.marysidy.island.model.Wolf;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(5, 5);
        System.out.println("Island created");

        
        Location location = new Location(0, 0);

        for (int i = 0; i < 35; i++) {
            boolean added = location.addAnimal(new Wolf());
            System.out.println((i + 1) + ". Wolf added: " + added);
        }
        System.out.println("The number of Wolves in one cell: " + location.getAnimals().stream().count());
    }
}
