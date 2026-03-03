package cz.wz.marysidy.island.model;

public interface Organism {
    double getWeight();
    boolean isAlive();
    void die();
    int getMaxCountPerLocation();
}
