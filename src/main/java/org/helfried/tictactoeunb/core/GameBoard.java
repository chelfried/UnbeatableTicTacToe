package org.helfried.tictactoeunb.core;

public class GameBoard {

    private static final int[][] gameBoards = new int[100][];

    private static final int[] playerAI = new int[100];
    private static final int[] playerHuman = new int[100];
    private static final int[] playerTurn = new int[100];

    private static int gameID = 0;

    public static int createNewGameBoard() {
        gameID += 1;
        int newId = gameID;
        gameBoards[newId] = new int[9];
        return newId;
    }

    public static void setActiveGameBoard(int gameID, int[] board) {
        System.arraycopy(board, 0, gameBoards[gameID], 0, 9);
    }

    public static void resetGameBoard(int gameID) {
        for (int i = 0; i < 9; i++) {
            gameBoards[gameID][i] = 0;
        }
    }

    public static int getPlayerAI(int gameID) {
        return playerAI[gameID];
    }

    public static void setPlayerHuman(int gameID, int playerHuman) {
        GameBoard.playerHuman[gameID] = playerHuman;
        if (playerHuman == 1) {
            playerAI[gameID] = 2;
        } else {
            playerAI[gameID] = 1;
        }
    }

    public static int getPlayerHuman(int gameID) {
        return playerHuman[gameID];
    }

    public static void setFieldAI(int gameID, int index) {
        gameBoards[gameID][index] = getPlayerAI(gameID);
    }

    public static void setFieldHuman(int gameID, int index) {
        gameBoards[gameID][index] = getPlayerHuman(gameID);
    }

    public static int getField(int gameID, int index) {
        return gameBoards[gameID][index];
    }

    public static int[] getActiveGameBoard(int gameID) {
        return gameBoards[gameID];
    }

    public static int getPlayerTurn(int gameID) {
        return playerTurn[gameID];
    }

    public static void setPlayerTurn(int gameID, int playerTurn) {
        GameBoard.playerTurn[gameID] = playerTurn;
    }

    private GameBoard() {
    }

}
