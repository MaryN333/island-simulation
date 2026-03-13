# Island Simulation Model

## Project Description
This project simulates an island ecosystem with plants and animals. The island consists of locations (cells) where animals and plants interact.

### Task Requirements
- Create an OOP-based hierarchy of animals (abstract class Animal)
- Implement 5 predator species and 10 herbivore species
- Animals can: eat, move, reproduce, die from hunger or being eaten
- Use multithreading for simulation performance

## Animal Species
### Predators
- Wolf
- Anaconda
- Fox
- Bear
- Eagle

### Herbivores
- Horse
- Deer
- Rabbit
- Mouse
- Goat
- Sheep
- Boar
- Buffalo
- Duck
- Caterpillar

### Plants
- Grass

## Animal Characteristics
Each animal has:
- Weight (kg)
- Max count per location
- Max speed (cells per tick)
- Food needed to be full (kg)

## Eating Probabilities
Each predator has a probability (%) to eat specific prey.
For example: Wolf eats Rabbit with 60% probability.

## Project Structure
```markdown
src/
├── main/
│ ├── java/
│ │ └── cz/wz/marysidy/island/
│ │ ├── config/ (SimulationConfig)
│ │ ├── controller/ (SimulationController)
│ │ ├── engine/ (Simulation, SimulationEngine)
│ │ ├── factory/ (OrganismFactory)
│ │ ├── model/
│ │ │ ├── Animal.java
│ │ │ ├── Bear.java
│ │ │ ├── Buffalo.java
│ │ │ ├── ... (other animal classes)
│ │ │ ├── Grass.java
│ │ │ ├── Herbivore.java
│ │ │ ├── Island.java
│ │ │ ├── Location.java
│ │ │ ├── MoveIntent.java
│ │ │ ├── Organism.java
│ │ │ ├── Plant.java
│ │ │ └── Predator.java
│ │ ├── phase/
│ │ │ ├── AbstractPhase.java
│ │ │ ├── CleanupPhase.java
│ │ │ ├── EatPhase.java
│ │ │ ├── HungerPhase.java
│ │ │ ├── MovePhase.java
│ │ │ ├── PlantGrowthPhase.java
│ │ │ ├── ReproducePhase.java
│ │ │ └── SimulationPhase.java
│ │ │── service/ (LocationService, StatisticsService)
│ │ └── util/ (EmojiRegistry)

```
## How It Works

### One Simulation Tick
1. **Plant Growth** - Grass grows in each location
2. **Move** - Animals decide to move to neighbor cells
3. **Eat** - Animals try to eat food from their location
4. **Reproduce** - Animals try to make children (if pair exists)
5. **Hunger** - Animals lose food (metabolism)
6. **Cleanup** - Dead animals and plants are removed

### Multithreading
The simulation uses two thread pools:

1. **Scheduled pool** (1 thread)
    - Starts new ticks every 200ms

2. **Fixed pool** (number of CPU cores)
    - Processes all locations in parallel during each phase
    - Each location is handled by a separate thread

### Requirements
- Java 17 or higher

▶️ How to Run

Run the main class:

Main.java

### Configuration
You can change simulation parameters in `SimulationConfig.java`:
```java
public class SimulationConfig {
    public static final int DEFAULT_TICKS = 30;
    public static final boolean DEFAULT_PARALLEL = true;
    public static final int TICK_DELAY_MS = 200;

    public static final int DEFAULT_WIDTH = 20;
    public static final int DEFAULT_HEIGHT = 20;
}
```
📊 Statistics

At every tick the simulation prints statistics for all species:

Example output:
```markdown
----- Tick 10 -----

🐍 Anaconda: 83
🐻 Bear: 104
🐗 Boar: 94
🐃 Buffalo: 103
🐛 Caterpillar: 2857
🦌 Deer: 96
🦆 Duck: 5878
🦅 Eagle: 113
🦊 Fox: 103
🐐 Goat: 2304
🌿 Grass: 1616
🐎 Horse: 100
🐭 Mouse: 5944
🐰 Rabbit: 2917
🐑 Sheep: 1815
🐺 Wolf: 78
```

