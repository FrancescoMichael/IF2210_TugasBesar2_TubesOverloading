package oop.plugin;

import oop.saveload.SaveLoad;

public class PluginTest {
    public static void main(String[] args) {
        try {
            // Path to the plugin JAR file
            String pluginPath = "../path/to/your/plugin.jar";

            PluginLoader pluginLoader = new PluginLoader();
            SaveLoad saveLoad = new SaveLoad();
            pluginLoader.loadPlugin(pluginPath, saveLoad);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}