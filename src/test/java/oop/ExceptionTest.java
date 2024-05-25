package oop;

import static org.junit.jupiter.api.Assertions.assertTrue;
import oop.player.*;
import oop.saveload.SaveLoad;
import oop.shop.Shop;

import org.junit.jupiter.api.Test;

import oop.card.Card;
import oop.card.creature.Carnivore;
import oop.card.creature.Creature;
import oop.card.creature.Herbivore;
import oop.card.item.Item;
import oop.card.product.CarnivoreFood;
import oop.exceptionkerajaan.*;

public class ExceptionTest {
    private void removeAll(Player player) throws BaseException {
        for (int i = 0; i < 6; i++) {
            player.removeCardAtActiveDeck(i);
        }
    }

    @Test
    public void test() {
        assertTrue(true);
        Player player = new Player("Player1");
        Player player2 = new Player("Player2");
        try {
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
            player.addCardToActiveDeckFirstEmpty(new Card("Accelerate"));
        } catch (BaseException e) {
            assertTrue(e instanceof ActiveDeckFullException);
        }
        try {
            removeAll(player);
            player.removeCardAtActiveDeck(100);

        } catch (BaseException e) {
            assertTrue(e instanceof ActiveDeckOutOfBoundsException);

        }

        try {
            removeAll(player);
            player.addCardToActiveDeckFirstEmpty(new Carnivore("Hiu Darat"));
            player.invokeCard(0, 1, 1, player);
        } catch (BaseException e) {
            assertTrue(e instanceof BlankCardException);

        }

        SaveLoad testSave = new SaveLoad();

        try {
            testSave.Save("damn", "dann", 0, null, null, null, null, null, null, null);

        } catch (Exception e) {
            assertTrue(e instanceof FolderNotFoundException);
        }

        try {
            testSave.Load("", null, null, null, null, null, null, null, null);
        } catch (BaseException e) {
            assertTrue(e instanceof FolderNotFoundException);

        }
        try {
            testSave.Load("src", "", null, null, null, null, null, null, null);
        } catch (BaseException e) {
            assertTrue(e instanceof FileNotFoundException);
        }

        try {
            new CarnivoreFood("Sirip Hiu", 500, "Carnivore", 12).beEaten(new Herbivore("Sapi"));
        } catch (BaseException e) {
            assertTrue(e instanceof FoodException);
        }

        try {
            removeAll(player);
            player.addCardToGrid(new Creature("Beruang"), 0, 0);
            player.addCardToActiveDeck(new Creature(), 0);
            player.invokeCard(0, 0, 0, player);
        } catch (BaseException e) {
            assertTrue(e instanceof InvalidCardPlacementException);

        }

        try {
            removeAll(player);
            player.addCardToActiveDeckFirstEmpty(new Item("Protect"));
            player.invokeCard(0, 0, 0, player);
            player2.addCardToActiveDeckFirstEmpty(new Item("Delay"));
            player2.invokeCard(0, 0, 0, player);
        } catch (BaseException e) {
            assertTrue(e instanceof InvalidProtected);
        }
        Shop shop = new Shop();
        try{
            removeAll(player);
            shop.Sell(player, 0);
        }catch (BaseException e){
            assertTrue(e instanceof InvalidSellException);
        }
        try {
            testSave.Save("src/test", "/AppTest.java", 0, null, null, null, null, null, null, null);
        } catch (Exception e){
            assertTrue(e instanceof SaveFailException);            
        }
    }
}
