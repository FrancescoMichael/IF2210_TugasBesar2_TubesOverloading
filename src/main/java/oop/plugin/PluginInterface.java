package oop.plugin;

import java.io.IOException;
import java.util.Map;

import oop.player.*;

public interface PluginInterface {
    void saveGame(String filename, Map<String, Integer> stocks, int currentTurn) throws IOException;

    void savePlayer(String filename, Player player) throws IOException;

    int loadGame(String filename, Map<String, Integer> stock) throws IOException;

    Player loadPlayer(String filename) throws IOException;

    String getType();
}