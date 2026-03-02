package cz.wz.marysidy.island;

import cz.wz.marysidy.island.model.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Testing of Island`s movePhase()");
        Island island = new Island(5, 5);
        Location start = island.getLocation(2, 2);
        Wolf wolf = new Wolf();
        start.addAnimal(wolf);

        System.out.println("Wolf #1. Before movePhase():");
        printIsland(island);

        System.out.println("Wolf #1 location: " +
                wolf.getLocation().getX() + ", " +
                wolf.getLocation().getY());

        island.movePhase();

        System.out.println("\nWolf #1. After movePhase():");
        printIsland(island);

        System.out.println("Wolf location: " +
                wolf.getLocation().getX() + ", " +
                wolf.getLocation().getY());

        System.out.println("*-".repeat(30));


        System.out.println("Testing of Island`s eatPhase()");
        Location location2 = island.getLocation(1, 1);
        Wolf wolf2 = new Wolf();
        // for testing, temporarily change the access modifier in the decreaseFood() method of Animal abstr. class
        // from protected to public
//        wolf2.decreaseFood(5);
        Rabbit rabbit2 = new Rabbit();
        location2.addAnimal(wolf2);
        location2.addAnimal(rabbit2);

        System.out.println("eatPhase(). Before eating:");
        printLocation(location2);
        System.out.println("\nWolf #2, food level: " + wolf2.getCurrentFood());

        island.eatPhase();

        System.out.println("\neatPhase(). After eating:");
        printLocation(location2);

        System.out.println("\nWolf #2 food level: " + wolf2.getCurrentFood());
        System.out.println("*-".repeat(30));


        System.out.println("Testing of Island`s hungerPhase()");
        Location location3 = island.getLocation(1, 1);
        Wolf wolf3 = new Wolf();
        location3.addAnimal(wolf3);

        System.out.println("Initial food: " + wolf3.getCurrentFood());
        for (int i = 1; i <= 10; i++) {
            island.hungerPhase();
            System.out.println("After tick " + i +
                    " food=" + wolf3.getCurrentFood() +
                    " alive=" + wolf3.isAlive());
        }
        System.out.println("*-".repeat(30));



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
