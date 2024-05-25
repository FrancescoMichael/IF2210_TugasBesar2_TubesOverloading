package oop.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveLoadTXT implements PluginInterface {

    @Override
    public void loadPlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
            List<String> gridString) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        // player status
        int gold = Integer.parseInt(reader.readLine().trim());
        int totalDeck = Integer.parseInt(reader.readLine().trim());
        playerStatus.add(gold);
        playerStatus.add(totalDeck);
        // active deck
        int activeDeck = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < activeDeck; i++) {
            activeDeckString.add(reader.readLine());
        }

        // grid
        int totalField = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < totalField; i++) {
            gridString.add(reader.readLine());
        }

        reader.close();
    }

    @Override
    public int loadGame(String filename, List<String> shopItems) throws IOException {
        System.out.println("Start game");
        System.out.println("Ini file : " +  filename);
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        System.out.println("Start game");
        int turn = Integer.parseInt(reader.readLine().trim());
        int numItemsInShop = Integer.parseInt(reader.readLine().trim());
        System.out.println("Start shop");
        for (int i = 0; i < numItemsInShop; i++) {
            shopItems.add(reader.readLine().trim());
        }
        System.out.println("End Shop");
        reader.close();

        return turn;
    }

    @Override
    public void savePlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
            List<String> gridString) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write(String.valueOf(playerStatus.get(0)));
        writer.newLine();
        writer.write(String.valueOf(playerStatus.get(1)));
        writer.newLine();
        writer.write(String.valueOf(activeDeckString.size()));
        writer.newLine();

        for (int i = 0; i < activeDeckString.size(); i++) {
            writer.write(activeDeckString.get(i));

            if (i != activeDeckString.size() - 1)
                writer.newLine();
        }

        if(activeDeckString.size() != 0) {
            writer.newLine();
        }

        writer.write(String.valueOf(gridString.size()));
        writer.newLine();

        for (int i = 0; i < gridString.size(); i++) {
            writer.write(gridString.get(i));

            if (i != gridString.size() - 1)
                writer.newLine();
        }

        writer.close();
    }

    @Override
    public void saveGame(String filename, int currentTurn, List<String> shopItems) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write(String.valueOf(currentTurn));
        writer.newLine();
        writer.write(String.valueOf(shopItems.size()));
        writer.newLine();

        for (int i = 0; i < shopItems.size(); i++) {
            writer.write(shopItems.get(i));

            if (i != shopItems.size() - 1)
                writer.newLine();
        }

        writer.close();
    }

    @Override
    public String getType() {
        return "txt";
    }
}