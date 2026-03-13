package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Animal;
import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;
import cz.wz.marysidy.island.model.MoveIntent;
import cz.wz.marysidy.island.service.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MovePhase implements SimulationPhase {
    @Override
    public void execute(Island island, LocationService locationService, boolean parallel) {
        // Stage 1 - intentions
        Map<Animal, MoveIntent> moveIntents = new ConcurrentHashMap<>();
        locationService.forEachLocation(location -> {
            List<Animal> animals = new ArrayList<>(location.getAnimals());

            for (Animal animal : animals) {
                if (!animal.isAlive()) continue;

                MoveIntent intent = animal.decideMove();
                moveIntents.put(animal, intent);
            }
        }, parallel);

        // Stage 2 — moving
        for (Map.Entry<Animal, MoveIntent> entry : moveIntents.entrySet()) {
            Animal animal = entry.getKey();
            MoveIntent intent = entry.getValue();
            Location currentLocation = animal.getLocation();
            int currentX = currentLocation.getX();
            int currentY = currentLocation.getY();

            int targetX = currentX + intent.getDx();
            int targetY = currentY + intent.getDy();

            if (!island.isValidCoordinate(targetX, targetY)) {
                continue; // doesn`t move
            }

            Location targetLocation = island.getLocation(targetX, targetY);

            boolean added = targetLocation.addAnimal(animal);
            if (added) {
                currentLocation.removeAnimal(animal);
            }
        }
    }
}
