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

    public int Load(String folderPath, String typeFile, List<String> currentShopItems,
            List<Integer> playerStatus1,
            List<String> activeDeckString1,
            List<String> gridString1,
            List<Integer> playerStatus2, List<String> activeDeckString2,
            List<String> gridString2) throws BaseException {

        File folder = new File(folderPath);

        int currTurn = -1;

        if (folder.exists() && folder.isDirectory()) {
            File gameStateFile = new File(folderPath, "gamestate." + typeFile);
            File player1File = new File(folderPath, "player1." + typeFile);
            File player2File = new File(folderPath, "player2." + typeFile);

            if (gameStateFile.exists() && player1File.exists() && player2File.exists()) {
                try {
                    // load gamestate
                    currTurn = loadGame(folderPath + "gamestate." + typeFile, typeFile, currentShopItems);

                    // load player1
                    loadPlayer(folderPath + "player1." + typeFile, playerStatus1, activeDeckString1, gridString1,
                            typeFile);

                    // load player 2
                    loadPlayer(folderPath + "player2." + typeFile, playerStatus2, activeDeckString2, gridString2,
                            typeFile);

                    return currTurn;
                } catch (Exception e) {
                    // TODO: handle exception
                }

        } else {
            throw new FileNotFoundException();
        }
    }

    public void Save(String folderPath, String typeFile, int currTurn, 
            List<String> currentShopItems,
            List<Integer> playerStatus1,
            List<String> activeDeckString1,
            List<String> gridString1,
            List<Integer> playerStatus2, List<String> activeDeckString2,
            List<String> gridString2) {
        try {
            // load gamestate
            saveGame(folderPath + "gamestate." + typeFile, typeFile, currentShopItems);

            // load player1
            loadPlayer(folderPath + "player1." + typeFile, playerStatus1, activeDeckString1, gridString1,
                    typeFile);

            // load player 2
            loadPlayer(folderPath + "player2." + typeFile, playerStatus2, activeDeckString2, gridString2,
                    typeFile);

            return currTurn;
        } catch (Exception e) {
            // TODO: handle exception
        }
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
        int currTurn = -1;
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
                plugin.loadPlayer(filename, playerStatus, activeDeckString, gridString);
            }
        }
    }
}