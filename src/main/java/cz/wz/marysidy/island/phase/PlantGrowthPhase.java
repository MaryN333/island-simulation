package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Grass;
import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;

public class PlantGrowthPhase extends AbstractPhase {
    @Override
    protected void executeOnLocation(Island island, Location location) {
        int current = location.getPlants().size();
        int max = Grass.MAX_COUNT_PER_LOCATION;
        int freeSpace = max - current;

        if (freeSpace <= 0) return;

        for (int i = 0; i < freeSpace; i++) {
            location.addPlant(new Grass());
        }
    }
}
