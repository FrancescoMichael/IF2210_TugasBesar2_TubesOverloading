// package oop.plugin;

// import org.yaml.snakeyaml.Yaml;

// import java.io.*;
// import java.util.ArrayList;
// import java.util.List;

// import java.util.*;

// public class SaveLoadYAML implements PluginInterface
// {
//     @Override
//     public void saveGame(String filename, int currentTurn, List<String> shopItems) throws IOException {
//         Map<String, Object> data = new LinkedHashMap<>();
//         data.put("CURRENT_TURN", currentTurn);
//         data.put("JUMLAH_ITEM_DI_SHOP", shopItems.size());

//         List<Map<String, Object>> shopItemsData = new ArrayList<>();
//         for (String item : shopItems) {
//             String[] parts = item.split(" ");
//             Map<String, Object> itemMap = new LinkedHashMap<>();
//             itemMap.put("NAME", parts[0]);
//             itemMap.put("QUANTITY", Integer.parseInt(parts[1]));
//             shopItemsData.add(itemMap);
//         }
//         data.put("SHOP_ITEMS", shopItemsData);

//         Yaml yaml = new Yaml();
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//             yaml.dump(data, writer);
//         }
//     }

//     @Override
//     public void savePlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString, List<String> gridString) throws IOException {
//         Map<String, Object> data = new LinkedHashMap<>();
//         data.put("JUMLAH_GULDEN", playerStatus.get(0));
//         data.put("JUMLAH_DECK", playerStatus.get(1));
//         data.put("JUMLAH_DECK_AKTIF", activeDeckString.size());

//         List<Map<String, Object>> activeDeckData = new ArrayList<>();
//         for (String card : activeDeckString) {
//             String[] parts = card.split(" ");
//             Map<String, Object> cardMap = new LinkedHashMap<>();
//             cardMap.put("LOKASI_KARTU", parts[0]);
//             cardMap.put("KARTU", parts[1]);
//             activeDeckData.add(cardMap);
//         }
//         data.put("DECK_AKTIF", activeDeckData);

//         data.put("JUMLAH_KARTU_LADANG", gridString.size());

//         List<Map<String, Object>> gridStringData = new ArrayList<>();
//         for (String card : gridString) {
//             String[] parts = card.split(" ");
//             Map<String, Object> cardMap = new LinkedHashMap<>();
//             cardMap.put("LOKASI_KARTU", parts[0]);
//             cardMap.put("KARTU", parts[1]);
//             cardMap.put("UMUR_BERAT", Integer.parseInt(parts[2]));
//             cardMap.put("JUMLAH_ITEM_AKTIF", Integer.parseInt(parts[3]));

//             if (parts.length > 4) {
//                 List<String> items = new ArrayList<>();
//                 for (int i = 4; i < parts.length; i++) {
//                     items.add(parts[i]);
//                 }
//                 cardMap.put("ITEMS", items);
//             }
//             gridStringData.add(cardMap);
//         }
//         data.put("KARTU_LADANG", gridStringData);

//         Yaml yaml = new Yaml();
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//             yaml.dump(data, writer);
//         }
//     }

//     @Override
//     public int loadGame(String filename, List<String> shopItems) throws IOException {
//         Yaml yaml = new Yaml();
//         try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//             Map<String, Object> data = yaml.load(reader);
//             int currentTurn = (int) data.get("CURRENT_TURN");
//             int itemCount = (int) data.get("JUMLAH_ITEM_DI_SHOP");

//             List<Map<String, Object>> shopItemsData = (List<Map<String, Object>>) data.get("SHOP_ITEMS");
//             for (Map<String, Object> item : shopItemsData) {
//                 String name = (String) item.get("NAME");
//                 int quantity = (int) item.get("QUANTITY");
//                 shopItems.add(name + " " + quantity);
//             }

//             return currentTurn;
//         }
//     }

//     @Override
//     public void loadPlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString, List<String> gridString) throws IOException {
//         Yaml yaml = new Yaml();
//         try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//             Map<String, Object> data = yaml.load(reader);

//             playerStatus.add((int) data.get("JUMLAH_GULDEN"));
//             playerStatus.add((int) data.get("JUMLAH_DECK"));

//             List<Map<String, Object>> activeDeckData = (List<Map<String, Object>>) data.get("DECK_AKTIF");
//             for (Map<String, Object> card : activeDeckData) {
//                 String location = (String) card.get("LOKASI_KARTU");
//                 String kartu = (String) card.get("KARTU");
//                 activeDeckString.add(location + " " + kartu);
//             }

//             List<Map<String, Object>> gridStringData = (List<Map<String, Object>>) data.get("KARTU_LADANG");
//             for (Map<String, Object> card : gridStringData) {
//                 String location = (String) card.get("LOKASI_KARTU");
//                 String kartu = (String) card.get("KARTU");
//                 int umurBerat = (int) card.get("UMUR_BERAT");
//                 int jumlahItemAktif = (int) card.get("JUMLAH_ITEM_AKTIF");

//                 StringBuilder gridEntry = new StringBuilder(location + " " + kartu + " " + umurBerat + " " + jumlahItemAktif);
//                 if (jumlahItemAktif > 0) {
//                     List<String> items = (List<String>) card.get("ITEMS");
//                     for (String item : items) {
//                         gridEntry.append(" ").append(item);
//                     }
//                 }
//                 gridString.add(gridEntry.toString());
//             }
//         }
//     }
//     @Override
//     public String getType() {
//         return "yaml";
//     }

// //    public static void main(String[] args) {
// //        try {
// //            // Load player 1
// //            List<Integer> playerStatus1 = new ArrayList<>();
// //            List<String> activeDeckString1 = new ArrayList<>();
// //            List<String> gridString1 = new ArrayList<>();
// //            loadPlayer("config/saved_player1.yaml", playerStatus1, activeDeckString1, gridString1);
// //            System.out.println(playerStatus1);
// //            System.out.println(activeDeckString1);
// //            System.out.println(gridString1);
// //
// //            // Load player 2
// //            List<Integer> playerStatus2 = new ArrayList<>();
// //            List<String> activeDeckString2 = new ArrayList<>();
// //            List<String> gridString2 = new ArrayList<>();
// //            loadPlayer("config/saved_player2.yaml", playerStatus2, activeDeckString2, gridString2);
// //            System.out.println(playerStatus2);
// //            System.out.println(activeDeckString2);
// //            System.out.println(gridString2);
// //
// //            // Load game
// //            List<String> shopItems = new ArrayList<>();
// //            int turn = loadGame("config/saved_gamestate.yaml", shopItems);
// //            System.out.println(turn);
// //            System.out.println(shopItems);
// //
// //            // Save player 1
// //            savePlayer("config/saved_player12.yaml", playerStatus1, activeDeckString1, gridString1);
// //
// //            // Save player 2
// //            savePlayer("config/saved_player22.yaml", playerStatus2, activeDeckString2, gridString2);
// //
// //            // Save game state
// //            saveGame( "config/saved_gamestate2.yaml", turn, shopItems);
// //        } catch (IOException e) {
// //            System.err.println("Failed to load data: " + e.getMessage());
// //        }
// //    }
// }
