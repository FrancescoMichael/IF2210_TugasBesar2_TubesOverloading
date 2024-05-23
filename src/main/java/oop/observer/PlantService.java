package oop.observer;

import oop.card.creature.*;

import java.util.*;

public class PlantService {
    private List<Plant> plants;

    private static int num = 0;
    public PlantService() {
        plants = new ArrayList<>();
    }

    public void subscribe(Plant plant) {
        plants.add(plant);
    }

    public void unsubscribe(Plant plant) {
        plants.remove(plant);
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void increaseAgeOfPlants() {
        System.out.println("ITERATE: " + num);
        num += 1;
        plants.forEach(plant -> { 
            plant.increaseWeight(1);
            System.out.println(plant);

        } );
    }

    public List<Plant> getSubscribers() {
        return this.plants;
    }
}