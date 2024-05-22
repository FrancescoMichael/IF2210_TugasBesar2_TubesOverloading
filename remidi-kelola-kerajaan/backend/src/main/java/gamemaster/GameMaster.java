package gamemaster;

import java.util.*;

import player.*;

import observer.*;

public class GameMaster {
    private final Random random = new Random();
    private List<Player> listPlayers;
    private int currentTurn;
    private PlantService plantService;

    private static Shop shop;

    public GameMaster() {
        // constructor 
        
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public Player getCurrentPlayer() {
        return this.listPlayers.get(this.currentTurn % 2);
    }

    public void next() {
        this.currentTurn++;
        this.plantService.increaseAgeOfPlants();

        // bear attack
        if(random.nextBoolean()) {
            bearAttack();
        } else {
            // no bear attack
        }
    }

    public void bearAttack() {
        int duration = 30 + random.nextInt(31);
        boolean bearAttackInProgress = true;
        int[] subgrid = field.selectSubgrid(random);

        System.out.println("Bear attack! Subgrid: " + Arrays.toString(subgrid) + " Duration: " + duration + " seconds.");

        new Thread(new BearAttackTimer(duration, subgrid)).start();
    }

    public synchronized void moveEntity(int fromRow, int fromCol, int toRow, int toCol) {
        if (bearAttackInProgress) {
            field.moveEntity(fromRow, fromCol, toRow, toCol);
        }
    }

    public synchronized void placeTrap(int row, int col) {
        if (bearAttackInProgress && field.hasEntity(row, col)) {
            bearAttackInProgress = false;
            System.out.println("Bear has been trapped!");
        }
    }

    private class BearAttackTimer implements Runnable {
        private final int duration;
        private final int[] subgrid;

        BearAttackTimer(int duration, int[] subgrid) {
            this.duration = duration;
            this.subgrid = subgrid;
        }

        @Override
        public void run() {
            try {
                int remaining = duration * 10; // Convert to deciseconds (0.1 seconds)
                while (remaining > 0) {
                    System.out.println("Time left: " + remaining / 10.0 + " seconds");
                    Thread.sleep(100); // Sleep for 0.1 second
                    remaining--;
                }

                synchronized (lock) {
                    if (bearAttackInProgress) {
                        field.clearSubgrid(subgrid);
                        bearAttackInProgress = false;
                        System.out.println("Bear attack ended. Plants/animals in the subgrid are dead.");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
