package oop.plugin;

import java.io.*;
import java.util.*;

import org.json.*;

public class SaveLoadJSON implements PluginInterface {

    @Override
    public void saveGame(String filename, int currentTurn, List<String> shopItems) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CURRENT_TURN", currentTurn);
        jsonObject.put("JUMLAH_ITEM_DI_SHOP", shopItems.size());

        JSONArray shopItemsArray = new JSONArray();
        for (String item : shopItems) {
            String[] parts = item.split(" ");
            JSONObject itemObject = new JSONObject();
            itemObject.put("NAME", parts[0]);
            itemObject.put("QUANTITY", Integer.parseInt(parts[1]));
            shopItemsArray.put(itemObject);
        }
        jsonObject.put("SHOP_ITEMS", shopItemsArray);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(jsonObject.toString(2));
        }
    }

    @Override
    public void savePlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString, List<String> gridString) throws IOException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("JUMLAH_GULDEN", playerStatus.get(0));
        jsonObject.put("JUMLAH_DECK", playerStatus.get(1));
        jsonObject.put("JUMLAH_DECK_AKTIF", activeDeckString.size());

        JSONArray activeDeckArray = new JSONArray();
        for (String card : activeDeckString) {
            JSONObject cardObject = new JSONObject();
            String[] parts = card.split(" ");
            cardObject.put("LOKASI_KARTU", parts[0]);
            cardObject.put("KARTU", parts[1]);
            activeDeckArray.put(cardObject);
        }
        jsonObject.put("DECK_AKTIF", activeDeckArray);

        jsonObject.put("JUMLAH_KARTU_LADANG", gridString.size());

        JSONArray gridArray = new JSONArray();
        for (String card : gridString) {
            JSONObject cardObject = new JSONObject();
            String[] parts = card.split(" ");
            cardObject.put("LOKASI_KARTU", parts[0]);
            cardObject.put("KARTU", parts[1]);
            cardObject.put("UMUR_BERAT", Integer.parseInt(parts[2]));
            int itemCount = Integer.parseInt(parts[3]);
            JSONArray itemArray = new JSONArray();
            for (int i = 0; i < itemCount; i++) {
                itemArray.put(parts[4 + i]);
            }
            cardObject.put("JUMLAH_ITEM_AKTIF", itemCount);
            cardObject.put("ITEMS", itemArray);
            gridArray.put(cardObject);
        }
        jsonObject.put("KARTU_LADANG", gridArray);

//        System.out.println(jsonObject);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(jsonObject.toString(2));
        }
    }

    @Override
    public int loadGame(String filename, List<String> shopItems) throws IOException {
        JSONObject jsonObject;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            jsonObject = new JSONObject(new JSONTokener(reader));
        }

        int currTurn = jsonObject.getInt("CURRENT_TURN");
        JSONArray shopItemsArray = jsonObject.getJSONArray("SHOP_ITEMS");
        for (int i = 0; i < shopItemsArray.length(); i++) {
            JSONObject itemObject = shopItemsArray.getJSONObject(i);
            shopItems.add(itemObject.getString("NAME") + " " + itemObject.getInt("QUANTITY"));
        }
        return currTurn;
    }

    @Override
    public void loadPlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString, List<String> gridString) throws IOException {
        JSONObject jsonObject;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            jsonObject = new JSONObject(new JSONTokener(reader));
        }

        playerStatus.add(jsonObject.getInt("JUMLAH_GULDEN"));
        playerStatus.add(jsonObject.getInt("JUMLAH_DECK"));

        JSONArray activeDeckArray = jsonObject.getJSONArray("DECK_AKTIF");
        for (int i = 0; i < activeDeckArray.length(); i++) {
            JSONObject cardObject = activeDeckArray.getJSONObject(i);
            String location = cardObject.getString("LOKASI_KARTU");
            String card = cardObject.getString("KARTU");
            activeDeckString.add(location + " " + card);
        }

        JSONArray gridArray = jsonObject.getJSONArray("KARTU_LADANG");
        for (int i = 0; i < gridArray.length(); i++) {
            JSONObject cardObject = gridArray.getJSONObject(i);
            StringBuilder cardInfo = new StringBuilder();
            cardInfo.append(cardObject.getString("LOKASI_KARTU")).append(" ");
            cardInfo.append(cardObject.getString("KARTU")).append(" ");
            cardInfo.append(cardObject.getInt("UMUR_BERAT")).append(" ");
            cardInfo.append(cardObject.getInt("JUMLAH_ITEM_AKTIF")).append(" ");
            JSONArray itemArray = cardObject.getJSONArray("ITEMS");
            for (int j = 0; j < itemArray.length(); j++) {
                cardInfo.append(itemArray.getString(j)).append(" ");
            }
            gridString.add(cardInfo.toString().trim());
        }
    }

    @Override
    public String getType() {
        return "json";
    }

//    public static void main(String[] args) {
//        try {
//            // Load player 1
//            List<Integer> playerStatus1 = new ArrayList<>();
//            List<String> activeDeckString1 = new ArrayList<>();
//            List<String> gridString1 = new ArrayList<>();
//            loadPlayer("config/player1.json", playerStatus1, activeDeckString1, gridString1);
//            System.out.println(playerStatus1);
//            System.out.println(activeDeckString1);
//            System.out.println(gridString1);
//
//            // Load player 2
//            List<Integer> playerStatus2 = new ArrayList<>();
//            List<String> activeDeckString2 = new ArrayList<>();
//            List<String> gridString2 = new ArrayList<>();
//            loadPlayer("config/player2.json", playerStatus2, activeDeckString2, gridString2);
//            System.out.println(playerStatus2);
//            System.out.println(activeDeckString2);
//            System.out.println(gridString2);
//
//            // Load game
//            List<String> shopItems = new ArrayList<>();
//            int turn = loadGame("config/gamestate.json", shopItems);
//            System.out.println(turn);
//            System.out.println(shopItems);
//
//            // Save player 1
//            savePlayer("config/saved_player1.json", playerStatus1, activeDeckString1, gridString1);
//
//            // Save player 2
//            savePlayer("config/saved_player2.json", playerStatus2, activeDeckString2, gridString2);
//
//            // Save game state
//            saveGame( "config/saved_gamestate.json", turn, shopItems);
//        } catch (IOException e) {
//            System.err.println("Failed to load data: " + e.getMessage());
//        }
//    }
}
