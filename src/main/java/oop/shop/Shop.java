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
        this.stock = new HashMap<>(Map.of(
                "Sirip Hiu", 0,
                "Susu", 0,
                "Daging Domba", 0,
                "Daging Kuda", 0,
                "Telur", 0,
                "Daging Beruang", 0,
                "Jagung", 0,
                "Labu", 0,
                "Stroberi", 0));
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

    public void Buy(Player currentPlayer, String productName, Integer quantity) throws BaseException {
        Product selectedProduct = allHarvestedProduct.get(productName);
        Integer currentStock = stock.get(productName);

        // check gulden, check deck, check stock
        if (currentPlayer.getGulden() >= (selectedProduct.getPrice() * quantity)
                && (currentPlayer.getCardDeckLeft() >= quantity) && (currentStock >= quantity)) {
            // Decrease player's gulden
            currentPlayer.setGulden(currentPlayer.getGulden() - (selectedProduct.getPrice() * quantity));

            // Decrease stock
            stock.put(productName, currentStock - quantity);

            // Create a new product instance
            Product productTemp;
            if (selectedProduct instanceof CarnivoreFood) {
                productTemp = new CarnivoreFood((CarnivoreFood) selectedProduct);
            } else {
                productTemp = new HerbivoreFood((HerbivoreFood) selectedProduct);
            }
            productTemp.setOwner(currentPlayer);

            // Add product to player's active deck
            for (int i = 0; i < quantity; i++) {
                currentPlayer.addCardToActiveDeckFirstEmpty(productTemp);
            }
        } else {
            throw new BaseException("Stock habis atau uang tidak cukup");
        }
    }

    public void Sell(Player currentPlayer, Integer indexSelected) throws BaseException {
        Card selectedCard;
        try {
            selectedCard = currentPlayer.getCardActiveDeck(indexSelected);
        } catch (Exception e) {
            throw new BaseException();
        }
        System.out.println(indexSelected);

        if (selectedCard instanceof Product) {
            // pembelian bisa dilakukan
            try {
                currentPlayer.setGulden(currentPlayer.getGulden() + ((Product) selectedCard).getPrice());
                String cardName = selectedCard.getName();
                int currentStock = this.stock.get(cardName);
                this.stock.put(cardName, currentStock + 1);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                // TODO: handle exception
            }
        } else {
            // exception
            throw new InvalidSellException();
        }
        currentPlayer.removeCardAtActiveDeck(indexSelected);
    }

    public Map<String, Integer> getStock() {
        return this.stock;
    }
}
