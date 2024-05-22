package oop.saveload;

import oop.plugin.PluginInterface;
import oop.plugin.SaveLoadTXT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oop.player.*;

public class SaveLoad {
    private List<PluginInterface> saveLoaders;

    public SaveLoad() {
        this.saveLoaders = new ArrayList<>();
        this.saveLoaders.add(new SaveLoadTXT());
    }

    public void addSaveLoader(PluginInterface saveLoader) {
        this.saveLoaders.add(saveLoader);
    }

    public List<PluginInterface> getSaveLoaders() {
        return this.saveLoaders;
    }

    public void saveData(String filename, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.saveGame(filename);
            }

        }
    }

    // INI YANG PENTING
    public void loadDataGame(String filename, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            int currTurn;
            List<String> currentShopItems = new ArrayList<>();
            if (type.equals(plugin.getType())) {
                currTurn = plugin.loadGame(filename, currentShopItems);
            }
        }
    }

    public void savePlayer(String filename, Player player, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.savePlayer(filename, player);
            }
        }
    }

    public void loadPlayer(String filename, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            List<Integer> playerStatus = new ArrayList<>();
            List<String> activeDeckString = new ArrayList<>();
            List<String> gridString = new ArrayList<>();
            if (type.equals(plugin.getType())) {
                plugin.loadPlayer(filename, playerStatus, activeDeckString, gridString);
            }
        }
    }
}