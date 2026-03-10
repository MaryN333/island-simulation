package cz.wz.marysidy.island.factory;

import cz.wz.marysidy.island.model.*;

import java.util.List;
import java.util.function.Supplier;

public class OrganismFactory {
    public static List<Supplier<? extends Organism>> getOrganismSuppliers() {
        return List.of(
                Grass::new,
                Mouse::new,
                Rabbit::new,
                Fox::new,
                Wolf::new
        );
    }
}
