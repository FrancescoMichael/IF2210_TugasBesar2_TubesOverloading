package oop.plugin;

import java.io.IOException;
import java.util.*;

public interface PluginInterface {
    public void loadPlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
            List<String> gridString) throws IOException;

    public int loadGame(String filename, List<String> shopItems) throws IOException;

    public void savePlayer(String filename, List<Integer> playerStatus, List<String> activeDeckString,
            List<String> gridString) throws IOException;

    public void saveGame(String filename, int currentTurn, List<String> shopItems) throws IOException;

    public String getType();
}