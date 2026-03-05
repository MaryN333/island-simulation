package cz.wz.marysidy.island.model;

public interface Organism {
    double getWeight();
    boolean isAlive();
    boolean tryDie();
    int getMaxCountPerLocation();
}
