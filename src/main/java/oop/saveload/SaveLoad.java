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

    public void saveData(String filename, Map<String, Integer> stock, int currentTurn, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.saveGame(filename, stock, currentTurn);
            }
        }
    }

    public void loadData(String filename, Map<String, Integer> stock, String type) throws Exception {
        for (PluginInterface plugin : this.saveLoaders) {
            if (type.equals(plugin.getType())) {
                plugin.loadGame(filename, stock);
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
            if (type.equals(plugin.getType())) {
                plugin.loadPlayer(filename);
            }
        }
    }
}