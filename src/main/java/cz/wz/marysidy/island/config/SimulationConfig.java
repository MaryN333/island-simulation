package cz.wz.marysidy.island.config;

public class SimulationConfig {
    public static final int DEFAULT_TICKS = 30;
    public static final boolean DEFAULT_PARALLEL = true;
    public static final int TICK_DELAY_MS = 200;

    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;

    public static final double INITIAL_MIN_PERCENT = 0.02;
    public static final double INITIAL_MAX_PERCENT = 0.05;

    public static final int THREADS = Runtime.getRuntime().availableProcessors();

    private SimulationConfig() {}
}
