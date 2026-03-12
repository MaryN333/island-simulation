package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Island;
import cz.wz.marysidy.island.model.Location;

public class CleanupPhase extends AbstractPhase {
    @Override
    protected void executeOnLocation(Island island, Location location) {
        island.cleanupPhase(location);
    }
}
