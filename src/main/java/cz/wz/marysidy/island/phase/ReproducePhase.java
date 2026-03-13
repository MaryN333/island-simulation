package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Animal;
import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ReproducePhase extends AbstractPhase {
    @Override
    protected void executeOnLocation(Island island, Location location) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Map<Class<? extends Animal>, List<Animal>> groups = new HashMap<>();

        for (Animal animal : location.getAnimals()) {
            if (!animal.isAlive()) continue;
            if (animal.isHungry()) continue;

            groups.computeIfAbsent(animal.getClass(), k -> new ArrayList<>()).add(animal);
        }

        for (List<Animal> sameTypeAnimals : groups.values()) {
            Collections.shuffle(sameTypeAnimals);
            int pairs = sameTypeAnimals.size() / 2;
            if (pairs == 0) continue;


            for (int i = 0; i < pairs; i++) {
                Animal parent = sameTypeAnimals.get(i * 2);
                int probability = parent.getReproduceProbability();

                if (random.nextInt(100) < probability) {
                    Animal child = parent.createChild();
                    location.addAnimal(child);
                }
            }
        }
    }
}