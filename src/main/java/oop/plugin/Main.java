package oop.plugin;

import oop.plugin.PluginInterface;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            PluginLoader pluginLoader = new PluginLoader();
            List<PluginInterface> plugins = pluginLoader.loadPlugins("path/to/your/plugin.jar");

            SaveLoadTXT saveLoad = new SaveLoad();
            for (PluginInterface plugin : plugins) {
                saveLoad.addSaveLoader(plugin);
            }

            // Example usage
            saveLoad.saveData("gameData.dat", "gameType1");
            saveLoad.loadData("gameData.dat", "gameType1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
