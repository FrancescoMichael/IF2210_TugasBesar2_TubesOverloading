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
        System.out.println("SUBSCRIBING//////////////////////////////////////");
        System.out.println(plant);
        plants.add(plant);
        System.out.println("///////////////////////////////////////////////////");
    }

    public void unsubscribe(Plant plant) {
        System.out.println("UNSUBSCRIBING//////////////////////////////////////");
        System.out.println(plant);
        plants.remove(plant);
        System.out.println("////////////////////////////////////////////////////");
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void increaseAgeOfPlants() {
        System.out.println("ITERATE: " + num + " ====================");
        num += 1;
        plants.forEach(plant -> { 
            plant.increaseWeight(1);
            System.out.println(plant);

        } );
        System.out.println("========================================");
    }

    public List<Plant> getSubscribers() {
        return this.plants;
    }
}