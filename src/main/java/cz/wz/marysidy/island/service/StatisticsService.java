package cz.wz.marysidy.island.service;

import cz.wz.marysidy.island.model.Animal;
import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Plant;
import cz.wz.marysidy.island.util.EmojiRegistry;

import java.util.Map;
import java.util.TreeMap;

public class StatisticsService {
    public Map<String, Integer> collectStatistics(Island island) {
        Map<String, Integer> stats = new TreeMap<>();

        island.forEachLocation(location -> {
            for (Animal animal : location.getAnimals()) {
                if (!animal.isAlive()) continue;

                String name = animal.getClass().getSimpleName();
                stats.put(name, stats.getOrDefault(name, 0) + 1);
            }

            for (Plant plant : location.getPlants()) {
                if (!plant.isAlive()) continue;

                String name = plant.getClass().getSimpleName();
                stats.put(name, stats.getOrDefault(name, 0) + 1);
            }
        });
        return stats;
    }

    public void printStatistics(Island island, int tick) {
        System.out.println("----- Tick " + tick + " -----");
        collectStatistics(island).forEach((name, count) -> {
            String emoji = EmojiRegistry.get(name);
            System.out.println(emoji + " " + name + ": " + count);
        });
    }
}
