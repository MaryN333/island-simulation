package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.service.LocationService;

public class CleanupPhase implements SimulationPhase {
    @Override
    public void execute(Island island, LocationService locationService, boolean parallel) {
        if (parallel) {
            locationService.parallelForEachLocation(island::cleanupPhase);
        } else {
            locationService.forEachLocation(island::cleanupPhase);
        }
    }
}
