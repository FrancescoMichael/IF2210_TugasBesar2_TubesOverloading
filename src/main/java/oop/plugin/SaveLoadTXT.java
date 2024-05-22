package oop.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oop.player.*;
import oop.card.*;

public class SaveLoadTXT {

    public static Player loadPlayer(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        int gold = Integer.parseInt(reader.readLine().trim());
        int totalDeck = Integer.parseInt(reader.readLine().trim());
        int activeDeck = Integer.parseInt(reader.readLine().trim());

        List<Card> deckCards = new ArrayList<>();
        for (int i = 0; i < activeDeck; i++) {
            String[] parts = reader.readLine().split(" ");
            deckCards.add(new DeckCard(parts[0], parts[1]));
        }

        int totalField = Integer.parseInt(reader.readLine().trim());

        List<Card> fieldCards = new ArrayList<>();
        for (int i = 0; i < totalField; i++) {
            String[] parts = reader.readLine().split(" ");
            String location = parts[0];
            String cardName = parts[1];
            int ageOrWeight = Integer.parseInt(parts[2]);
            List<String> activeItems = new ArrayList<>();
            for (int j = 0; j < Integer.parseInt(parts[3]); j++) {
                activeItems.add(parts[4 + j]);
            }
            fieldCards.add(new FieldCard(location, cardName, ageOrWeight, activeItems));
        }

        reader.close();

        return new Player(gold, totalDeck, activeDeck, deckCards, totalField, fieldCards);
    }

    public static int loadGame(String filename, List<String> shopItems) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        int turn = Integer.parseInt(reader.readLine().trim());
        int numItemsInShop = Integer.parseInt(reader.readLine().trim());

        for (int i = 0; i < numItemsInShop; i++) {
            shopItems.add(reader.readLine().trim());
        }

        reader.close();

        System.out.println("Current turn: " + turn);
        System.out.println("Shop items: " + shopItems);

        return turn;
    }

    public static void savePlayer(Player player, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write(String.valueOf(player.getGold()));
        writer.newLine();
        writer.write(String.valueOf(player.getTotalDeck()));
        writer.newLine();
        writer.write(String.valueOf(player.getActiveDeck()));
        writer.newLine();

        for (DeckCard card : player.getDeckCards()) {
            writer.write(card.getLocation() + " " + card.getCardName());
            writer.newLine();
        }

        writer.write(String.valueOf(player.getTotalField()));
        writer.newLine();

        for (FieldCard card : player.getFieldCards()) {
            writer.write(card.getLocation() + " " + card.getCardName() + " " + card.getAgeOrWeight() + " "
                    + card.getActiveItems().size());
            for (String item : card.getActiveItems()) {
                writer.write(" " + item);
            }
            writer.newLine();
        }

        writer.close();
    }

    public static void saveGame(int currentTurn, List<String> shopItems, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write(String.valueOf(currentTurn));
        writer.newLine();
        writer.write(String.valueOf(shopItems.size()));
        writer.newLine();

        for (String item : shopItems) {
            writer.write(item);
            writer.newLine();
        }

        writer.close();
    }

    public static void main(String[] args) {
        try {
            // Load player 1 and player 2 data
            Player player1 = loadPlayer("../player1.txt");
            Player player2 = loadPlayer("../player2.txt");

            // Load game
            List<String> shopItems = new ArrayList<>();
            int turn = loadGame("../gamestate.txt", shopItems);

            // Save player 1 and player 2 data
            savePlayer(player1, "../saved_player1.txt");
            savePlayer(player2, "../saved_player2.txt");

            // Save game state
            saveGame(turn, shopItems, "../saved_gamestate.txt");
        } catch (IOException e) {
            System.err.println("Failed to load data: " + e.getMessage());
        }
    }
}