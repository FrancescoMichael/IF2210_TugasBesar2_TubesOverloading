package oop.plugin;

import java.io.IOException;

public interface PluginInterface {
    void saveData() throws IOException;
    void loadData() throws IOException;
}