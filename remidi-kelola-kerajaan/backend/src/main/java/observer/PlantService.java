package observer;

import card.creature.*;

import java.util.*;

public class PlantService {
    private List<Plant> plants;

    public PlantService() {
        plants = new ArrayList<>();
    }

    public void subscribe(Plant plant) {
        plants.add(plant);
    }

    public void unsubscribe(Plant plant) {
        plants.remove(plant);
    }

    public void increaseAgeOfPlants() {
        plants.forEach(plant -> plant.increaseWeight(1));
    }
}
