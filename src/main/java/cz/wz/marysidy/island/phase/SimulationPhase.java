package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.service.LocationService;

public interface SimulationPhase {
    void execute(Island island, LocationService locationService);
}
