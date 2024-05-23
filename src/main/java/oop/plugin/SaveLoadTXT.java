// package oop.plugin;

// import oop.plugin.PluginInterface;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class SaveLoadTXT implements PluginInterface {

//     @Override
//     public static void loadPlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
//             List<String> gridString) throws IOException {
//         BufferedReader reader = new BufferedReader(new FileReader(filename));

//         // player status
//         int gold = Integer.parseInt(reader.readLine().trim());
//         int totalDeck = Integer.parseInt(reader.readLine().trim());
//         playerStatus.add(gold);
//         playerStatus.add(totalDeck);

//         // active deck
//         int activeDeck = Integer.parseInt(reader.readLine().trim());
//         for (int i = 0; i < activeDeck; i++) {
//             activeDeckString.add(reader.readLine());
//         }

//         // grid
//         int totalField = Integer.parseInt(reader.readLine().trim());
//         for (int i = 0; i < totalField; i++) {
//             gridString.add(reader.readLine());
//         }

//         reader.close();
//     }

//     @Override
//     public static int loadGame(String filename, List<String> shopItems) throws IOException {
//         BufferedReader reader = new BufferedReader(new FileReader(filename));

//         int turn = Integer.parseInt(reader.readLine().trim());
//         int numItemsInShop = Integer.parseInt(reader.readLine().trim());

//         for (int i = 0; i < numItemsInShop; i++) {
//             shopItems.add(reader.readLine().trim());
//         }

//         reader.close();

//         return turn;
//     }

//     @Override
//     public static void savePlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
//             List<String> gridString) throws IOException {
//         BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

//         writer.write(String.valueOf(playerStatus.get(0)));
//         writer.newLine();
//         writer.write(String.valueOf(playerStatus.get(1)));
//         writer.newLine();
//         writer.write(String.valueOf(activeDeckString.size()));
//         writer.newLine();

//         for (int i = 0; i < activeDeckString.size(); i++) {
//             writer.write(activeDeckString.get(i));

//             if (i != activeDeckString.size() - 1)
//                 writer.newLine();
//         }

//         writer.newLine();

//         writer.write(String.valueOf(gridString.size()));
//         writer.newLine();

//         for (int i = 0; i < gridString.size(); i++) {
//             writer.write(gridString.get(i));

//             if (i != gridString.size() - 1)
//                 writer.newLine();
//         }

//         writer.close();
//     }

//     @Override
//     public static void saveGame(String filename, int currentTurn, List<String> shopItems) throws IOException {
//         BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

//         writer.write(String.valueOf(currentTurn));
//         writer.newLine();
//         writer.write(String.valueOf(shopItems.size()));
//         writer.newLine();

//         for (int i = 0; i < shopItems.size(); i++) {
//             writer.write(shopItems.get(i));

//             if (i != shopItems.size() - 1)
//                 writer.newLine();
//         }

//         writer.close();
//     }

//     @Override
//     public String getType() {
//         return "txt";
//     }

//     // public static void main(String[] args) {
//     // try {
//     // // Load player 1
//     // List<Integer> playerStatus1 = new ArrayList<>();
//     // List<String> activeDeckString1 = new ArrayList<>();
//     // List<String> gridString1 = new ArrayList<>();
//     // loadPlayer("../config/player1.txt", playerStatus1, activeDeckString1,
//     // gridString1);
//     // System.out.println(playerStatus1);
//     // System.out.println(activeDeckString1);
//     // System.out.println(gridString1);
//     //
//     // // Load player 2
//     // List<Integer> playerStatus2 = new ArrayList<>();
//     // List<String> activeDeckString2 = new ArrayList<>();
//     // List<String> gridString2 = new ArrayList<>();
//     // loadPlayer("../config/player2.txt", playerStatus2, activeDeckString2,
//     // gridString2);
//     // System.out.println(playerStatus2);
//     // System.out.println(activeDeckString2);
//     // System.out.println(gridString2);
//     //
//     // // Load game
//     // List<String> shopItems = new ArrayList<>();
//     // int turn = loadGame("../config/gamestate.txt", shopItems);
//     // System.out.println(turn);
//     // System.out.println(shopItems);
//     //
//     // // Save player 1
//     // savePlayer("../saved_player1.txt", playerStatus1, activeDeckString1,
//     // gridString1);
//     //
//     // // Save player 2
//     // savePlayer("../saved_player2.txt", playerStatus2, activeDeckString2,
//     // gridString2);
//     //
//     // // Save game state
//     // saveGame( "../saved_gamestate.txt", turn, shopItems);
//     // } catch (IOException e) {
//     // System.err.println("Failed to load data: " + e.getMessage());
//     // }
//     // }
// }