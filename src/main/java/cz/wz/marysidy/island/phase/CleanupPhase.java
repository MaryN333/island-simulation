package cz.wz.marysidy.island.phase;

import cz.wz.marysidy.island.model.Island;

public class CleanupPhase implements SimulationPhase {
    @Override
    public void execute(Island island) {
        island.forEachLocation(island::cleanupPhase);
    }
}
