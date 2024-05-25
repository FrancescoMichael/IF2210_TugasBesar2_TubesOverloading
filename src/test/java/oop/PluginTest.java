package oop;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import oop.plugin.*;
import oop.saveload.SaveLoad;


public class PluginTest {

    @Test
    public void test() {
        SaveLoadTXT plug = new SaveLoadTXT();
        List<Integer> playerStatus = new ArrayList<>();
        List<String> activeDeckString = new ArrayList<>();
        List<String> gridStrin = new ArrayList<>();
        SaveLoad saveLoad = new SaveLoad();
        try{
            plug.loadPlayer("src/test/resources/player1.txt", playerStatus, activeDeckString, gridStrin);
            plug.loadGame("src/test/resources/gamestate.txt", gridStrin);
            plug.savePlayer("src/test/resources/player2.txt", playerStatus, activeDeckString, gridStrin);
            saveLoad.loadGame("src/test/resources/gamestate.txt", ".txt",gridStrin);
            saveLoad.loadPlayer("src/test/resources/player1.txt", playerStatus, activeDeckString, gridStrin, ".txt");
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    

    }
    
}
