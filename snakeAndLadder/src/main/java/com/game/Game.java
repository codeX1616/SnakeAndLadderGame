package com.game;

import com.gameEntities.Board;
import com.gameEntities.Dice;
import com.gameEntities.Player;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
    private Board board;
    private Dice dice;
    private Deque<Player> playerList = new LinkedList<>();
    private Player winner;


    public Game() {
        intializeGame();
    }

    private void intializeGame() {
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        initializePlayers();
    }

    private void initializePlayers() {
        Player p1 = new Player("p1", 0);
        Player p2 = new Player("p2", 0);
        playerList.add(p1);
        playerList.add(p2);
    }

    public void startGame() {
        while (winner == null) {
            Player playerTurn = findPlayerTurn();
            System.out.println("Player turn is" + playerTurn.getId() + "current position is" + playerTurn.getCurrentPosition());
            int diceNumbers = dice.rollDice();

            int playerNewPosition = playerTurn.getCurrentPosition() + diceNumbers;
            playerNewPosition = jumpCheck(playerNewPosition);

            int numberOfCells = board.getCells().length;
            if (playerNewPosition >= (numberOfCells * (numberOfCells - 1))) {
                winner = playerTurn;
            }
        }
    }

    private int jumpCheck(int playerNewPosition) {
        // TODO - Add jump check
        return -1;
    }

    private Player findPlayerTurn() {
        Player playerTurns = playerList.removeFirst();
        playerList.addLast(playerTurns);
        return playerTurns;
    }
}
