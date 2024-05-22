package oop.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oop.player.*;
import oop.card.*;

public class SaveLoadTXT implements PluginInterface {
    @Override
    public void saveGame(String filename, Map<String, Integer> stock, int currentTurn) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write(String.valueOf(currentTurn));
        writer.newLine();
        int numItemsInShop = 0;
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() > 0) {
                numItemsInShop++;
            }
        }
        writer.write(String.valueOf(numItemsInShop));
        writer.newLine();

        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() > 0) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }

        writer.close();
    }

    @Override
    public void saveplayer(String filename, Player player) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write(String.valueOf(player.getGulden()));
        writer.newLine();
        writer.write(String.valueOf(player.getCardDeckLeft()));
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

    @Override
    public int loadGame(String filename, Map<String, Integer> stock) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        int turn = Integer.parseInt(reader.readLine().trim());
        int numItemsInShop = Integer.parseInt(reader.readLine().trim());

        for (int i = 0; i < numItemsInShop; i++) {
            String[] parts = reader.readLine().split(" ");
            stock.put(parts[0], Integer.parseInt(parts[1]));
        }

        reader.close();

        return turn;
    }

    @Override
    public Player loadPlayer(String filename) throws IOException {
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

    @Override
    public String getType() {
        return "txt";
    }
}