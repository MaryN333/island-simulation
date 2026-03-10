package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.service.LocationService;

public class HungerPhase implements SimulationPhase {
    @Override
    public void execute(Island island) {
        LocationService locationService = new LocationService(island);
        locationService.forEachLocation(island::hungerPhase);
    }
}
