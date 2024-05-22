package oop.plugin;

import java.io.IOException;
import java.util.*;

import oop.player.*;

public interface PluginInterface {
    void saveGame(String filename, int currTurn, List<String> shopItems) throws IOException;

    void savePlayer(String filename, int currGulden, int cardDeckLeft) throws IOException;

    int loadGame(String filename, List<String> shopItems) throws IOException;

    List<Integer> loadPlayer(String filename, List<String> playerStatus, List<String> activeDeckString,
            List<String> gridString) throws IOException;

    String getType();
}