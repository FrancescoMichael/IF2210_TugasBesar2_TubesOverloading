package shop;

import java.util.*;

import card.Card;
import card.creature.Creature;
import card.product.Product;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import player.Player;

public class Shop {
    public Map<String, Integer> stock;
    public Map<String, Product> allHarvestedProduct;
    public Shop() {
        // Constructor
        this.stock = new HashMap<String, Integer>();
        this.allHarvestedProduct = Map.of(
            "Sirip Hiu", new CarnivoreFood("Sirip Hiu", 500, "dummy.img", "Carnivore", 12),
            "Susu", new CarnivoreFood("Susu", 100, "dummy.img", "Carnivore", 4),
            "Daging Domba", new CarnivoreFood("Daging Domba", 120, "dummy.img", "Carnivore", 6),
            "Daging Kuda", new CarnivoreFood("Daging Kuda", 150, "dummy.img", "Carnivore", 8),
            "Telur", new CarnivoreFood("Telur", 50, "dummy.img", "Carnivore", 2),
            "Daging Beruang", new CarnivoreFood("Daging Beruang", 500, "dummy.img", "Carnivore", 12),
            "Jagung", new HerbivoreFood("Jagung", 150, "dummy.img", "Herbivore", 3),
            "Labu", new HerbivoreFood("Labu", 500, "dummy.img", "Herbivore", 10),
            "Stroberi", new HerbivoreFood("Stroberi", 350, "dummy.img", "Herbivore", 5));
    }

    public void Buy(Player currentPlayer, Card selectedCard) {
        if(selectedCard instanceof Product) {
            if(currentPlayer.getGulden() >= selectedCard.getPrice() && !currentPlayer.isFullActiveDeck()) {
                // pembalian bisa dilakukan
                // stock.get(selectedCard.getName());
                String cardName = selectedCard.getName();
                int currentStock = stock.get(cardName);
                stock.put(cardName, currentStock - 1);

                currentPlayer.setGulden(currentPlayer.getGulden() - selectedCard.getPrice());

                Product pTemp = this.allHarvestedProduct.get(selectedCard.getName());
                if (pTemp instanceof CarnivoreFood){
                    pTemp = new CarnivoreFood( (CarnivoreFood)pTemp);

                } else if (pTemp instanceof HerbivoreFood){
                    pTemp = new HerbivoreFood( (HerbivoreFood)pTemp);
                }
                pTemp.setOwner(currentPlayer);
                
                currentPlayer.addCardToActiveDeck(pTemp);  
            } else {
                System.out.println("Stok habis atau uang tidak cukup");
            }
        } else {
            // pembelisan bukan product
        }
    }

    public void Sell(Player currentPlayer, Card selectedCard) {
        if(selectedCard instanceof Product) {
            // pembelian bisa dilakukan
            currentPlayer.setGulden(currentPlayer.getGulden() + selectedCard.getPrice());
            String cardName = selectedCard.getName();
            int currentStock = stock.get(cardName);
            stock.put(cardName, currentStock + 1);
        } else {
            // exception
        }
    }
}
