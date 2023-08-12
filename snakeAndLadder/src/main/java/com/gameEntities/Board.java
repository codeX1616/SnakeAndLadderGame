package com.gameEntities;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Board {
    private Cell[][] cells;

    public Board(int boardSize, int numberOfSnakes, int numberOfLadders) {
        initializeCells(boardSize);
        addSnakesAndLadders(cells, numberOfSnakes, numberOfLadders);
    }

    /**
     * Initialize empty board cells
     * @param boardSize
     */
    private void initializeCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];
        for (int i=0;i<boardSize;i++)
            for (int j=0;j<boardSize;j++)
                cells[i][j] = new Cell();
    }

    /**
     * Add snakes and ladders to board cells
     * @param cells
     * @param numberOfSnakes
     * @param numberOfLadders
     */
    private void addSnakesAndLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders) {
        while (numberOfSnakes > 0) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length-1);
            if (snakeTail >= snakeHead) {
                continue;
            }
            Jump snakeObj = Jump.builder()
                    .start(snakeHead)
                    .end(snakeTail)
                    .build();
            Cell cell = getCell(snakeHead);
            cell.setJump(snakeObj);
            numberOfSnakes--;
        }
        while (numberOfLadders > 0) {
            int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length-1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length-1);
            if (ladderTail >= ladderHead) {
                continue;
            }
            Jump snakeObj = Jump.builder()
                    .start(ladderHead)
                    .end(ladderTail)
                    .build();
            Cell cell = getCell(ladderHead);
            cell.setJump(snakeObj);
            numberOfSnakes--;
        }
    }

    /**
     * Method to get get cell object from player position
     * @param playerPosition
     * @return
     */
    private Cell getCell(int playerPosition) {
        int boardRow = playerPosition / cells.length;
        int boardCol = playerPosition % cells.length;
        return cells[boardRow][boardCol];
    }
}
