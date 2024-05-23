package oop.saveload;

import oop.plugin.PluginInterface;
import oop.plugin.SaveLoadTXT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;

import oop.exceptionkerajaan.BaseException;
import oop.exceptionkerajaan.FileNotFoundException;
import oop.player.*;

public class SaveLoad {
    private List<PluginInterface> saveLoaders;

    public SaveLoad() {
        this.saveLoaders = new ArrayList<>();
        this.saveLoaders.add(new SaveLoadTXT());
    }

    public void Load(String folderPath, String typeFile) throws BaseException {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File gameStateFile = new File(folderPath, "gamestate." + typeFile);
            File player1File = new File(folderPath, "player1." + typeFile);
            File player2File = new File(folderPath, "player2." + typeFile);

            if (gameStateFile.exists() && player1File.exists() && player2File.exists()) {
                System.out.println("All required files are present.");
            } else {
                System.out.println("One or more required files are missing:");
                if (!gameStateFile.exists()) {
                    System.out.println("Missing: gamestate.txt");
                }
                if (!player1File.exists()) {
                    System.out.println("Missing: player1.txt");
                }
                if (!player2File.exists()) {
                    System.out.println("Missing: player2.txt");
                }
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public void Save(String folderPath, String typeFile) {
        
    }

    public void addSaveLoader(PluginInterface saveLoader) {
        this.saveLoaders.add(saveLoader);
    }

    public List<PluginInterface> getSaveLoaders() {
        return this.saveLoaders;
    }

    public void saveGame(String filename, int turn, List<String> shopItems, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.saveGame(filename, turn, shopItems);
            }
        }
    }

    // INI YANG PENTING
    public int loadGame(String filename, String type, List<String> currentShopItems) throws Exception {
        int currTurn;
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                currTurn = plugin.loadGame(filename, currentShopItems);
            }
        }
        return currTurn;
    }

    public void savePlayer(String filename, Player player, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.savePlayer(filename, player.getGulden(), player.getCardDeckLeft());
            }
        }
    }

    public void loadPlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
            List<String> gridString, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.loadPlayer(filename, playerStatus, activeDeckString, gridString, type);
            }
        }
    }
}