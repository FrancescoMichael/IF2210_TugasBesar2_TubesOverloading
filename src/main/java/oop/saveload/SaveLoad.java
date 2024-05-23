package oop.saveload;

import oop.plugin.PluginInterface;
import oop.plugin.SaveLoadTXT;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

import oop.exceptionkerajaan.BaseException;
import oop.exceptionkerajaan.FileNotFoundException;
import oop.exceptionkerajaan.FolderNotFoundException;
import oop.exceptionkerajaan.LoadFailException;
import oop.exceptionkerajaan.SaveFailException;

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

        // check if folder exist
        if (folder.exists() && folder.isDirectory()) {
            File gameStateFile = new File(folderPath, "gamestate." + typeFile);
            File player1File = new File(folderPath, "player1." + typeFile);
            File player2File = new File(folderPath, "player2." + typeFile);

            // check if file exist
            if (gameStateFile.exists() && player1File.exists() && player2File.exists()) {
                try {
                    System.out.println("Start load");
                    // load gamestate
                    currTurn = loadGame(folderPath + "/gamestate." + typeFile, typeFile, currentShopItems);

                    System.out.println("Load Game");
                    // load player1
                    loadPlayer(folderPath + "/player1." + typeFile, playerStatus1, activeDeckString1, gridString1,
                            typeFile);

                            System.out.println("Load Player1");
                    // load player 2
                    loadPlayer(folderPath + "/player2." + typeFile, playerStatus2, activeDeckString2, gridString2,
                            typeFile);  
                            System.out.println("Load Player2");

                    return currTurn;
                } catch (Exception e) {
                    throw new LoadFailException();
                }
            } else {
                throw new FileNotFoundException();
            }
        } else {
            throw new FolderNotFoundException();
        }
    }

    public void Save(String folderPath, String typeFile, int currTurn, 
            List<String> currentShopItems,
            List<Integer> playerStatus1,
            List<String> activeDeckString1,
            List<String> gridString1,
            List<Integer> playerStatus2, List<String> activeDeckString2,
            List<String> gridString2) throws Exception {
        try {
            // save gamestate
            saveGame(folderPath + "/gamestate." + typeFile, currTurn, currentShopItems, typeFile);

            // save player1
            savePlayer(folderPath + "/player1." + typeFile, playerStatus1, activeDeckString1, gridString1, typeFile);

            // save player 2
            savePlayer(folderPath + "/player2." + typeFile, playerStatus2, activeDeckString2, gridString2, typeFile);

        } catch (Exception e) {
            throw new SaveFailException();
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

    public int loadGame(String filename, String type, List<String> currentShopItems) throws Exception {
        System.out.println("Load game");
        int currTurn = -1;
        for (PluginInterface plugin : this.saveLoaders) {
            System.out.println("Plugin start");
            if (type.equals(plugin.getType())) {
                System.out.println("Found plugin");
                currTurn = plugin.loadGame(filename, currentShopItems);
            }
        }
        System.out.println("Return");
        return currTurn;
    }

    public void savePlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
    List<String> gridString, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.savePlayer(filename, playerStatus, activeDeckString, gridString);
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