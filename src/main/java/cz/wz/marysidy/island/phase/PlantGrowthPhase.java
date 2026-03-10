package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.service.LocationService;

public class PlantGrowthPhase implements SimulationPhase{
    @Override
    public void execute(Island island, LocationService locationService) {
        locationService.forEachLocation(island::plantGrowthPhase);
    }
}
