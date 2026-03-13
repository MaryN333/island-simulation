package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;
import cz.wz.marysidy.island.service.LocationService;

public abstract class AbstractPhase implements SimulationPhase {
    protected abstract void executeOnLocation(Island island, Location location);

    @Override
    public void execute(Island island, LocationService locationService, boolean parallel) {
        locationService.forEachLocation(
                location -> executeOnLocation(island, location),
                parallel
        );
    }
}
