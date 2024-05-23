package oop.shop;

import java.util.*;

import oop.card.Card;
import oop.card.product.Product;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.player.Player;
import oop.exceptionkerajaan.*;

public class Shop {
    public Map<String, Integer> stock;
    public Map<String, Product> allHarvestedProduct;

    public Shop() {
        // Constructor
        this.stock = new HashMap<String, Integer>();
        this.allHarvestedProduct = Map.of(
                "Sirip Hiu", new CarnivoreFood("Sirip Hiu", 500, "Carnivore", 12),
                "Susu", new CarnivoreFood("Susu", 100, "Carnivore", 4),
                "Daging Domba", new CarnivoreFood("Daging Domba", 120, "Carnivore", 6),
                "Daging Kuda", new CarnivoreFood("Daging Kuda", 150, "Carnivore", 8),
                "Telur", new CarnivoreFood("Telur", 50, "Carnivore", 2),
                "Daging Beruang", new CarnivoreFood("Daging Beruang", 500, "Carnivore", 12),
                "Jagung", new HerbivoreFood("Jagung", 150, "Herbivore", 3),
                "Labu", new HerbivoreFood("Labu", 500, "Herbivore", 10),
                "Stroberi", new HerbivoreFood("Stroberi", 350, "Herbivore", 5));
    }

    public void Buy(Player currentPlayer, String productName) throws BaseException {
        Product selectedProduct = allHarvestedProduct.get(productName);

        if (currentPlayer.getGulden() >= selectedProduct.getPrice() && !currentPlayer.isActiveDeckFull()) {
            // Check stock
            Integer currentStock = stock.get(productName);
            if (currentStock == null || currentStock <= 0) {
                throw new BaseException("Stock habis atau uang tidak cukup");
            }

            // Decrease player's gulden
            currentPlayer.setGulden(currentPlayer.getGulden() - selectedProduct.getPrice());

            // Decrease stock
            stock.put(productName, currentStock - 1);

            // Create a new product instance
            Product productTemp;
            if (selectedProduct instanceof CarnivoreFood) {
                productTemp = new CarnivoreFood((CarnivoreFood) selectedProduct);
            } else {
                productTemp = new HerbivoreFood((HerbivoreFood) selectedProduct);
            }
            productTemp.setOwner(currentPlayer);

            // Add product to player's active deck
            currentPlayer.addCardToActiveDeckFirstEmpty(productTemp);
        } else {
            throw new BaseException("Stock habis atau uang tidak cukup");
        }
    }

    public void Sell(Player currentPlayer, Integer indexSelected) throws BaseException {
        Card selectedCard;
        try {
            selectedCard = currentPlayer.getCardActiveDeck(indexSelected);
        } catch(Exception e) {
            throw new BaseException();
        }

        if (selectedCard instanceof Product) {
            // pembelian bisa dilakukan
            currentPlayer.setGulden(currentPlayer.getGulden() + ((Product) selectedCard).getPrice());
            String cardName = selectedCard.getName();
            int currentStock = stock.get(cardName);
            stock.put(cardName, currentStock + 1);
        } else {
            // exception
        }

        currentPlayer.removeCardAtActiveDeck(indexSelected);
    }
}
