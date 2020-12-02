package org.helfried.tictactoeunb.core;

import java.util.Arrays;

import static org.helfried.tictactoeunb.core.GameBoard.*;

public class GameActions {

    public static void humanPlayerChoice(int gameID, int choice) {
        if (getPlayerTurn(gameID) == getPlayerHuman(gameID)) {
            while (true) {
                for (int i = 0; i < 9; ++i) {
                    if (choice == i && getField(gameID, i) == 0) {
                        setFieldHuman(gameID, i);
                        setPlayerTurn(gameID, getPlayerAI(gameID));
                        return;
                    }
                }
            }
        }
    }

    public static String checkForGameEnd(int gameID) {
        if (checkForDraw(gameID) != null) {
            setPlayerTurn(gameID,0);
            return checkForDraw(gameID);
        } else if (checkForWin(gameID, getActiveGameBoard(gameID)) != null) {
            setPlayerTurn(gameID, 0);
            return checkForWin(gameID, getActiveGameBoard(gameID));
        }
        return null;
    }

    public static String checkForWin(int gameID, int[] board) {
        int[][] toCheck = {
                {board[0], board[1], board[2]},
                {board[3], board[4], board[5]},
                {board[6], board[7], board[8]},
                {board[0], board[3], board[6]},
                {board[1], board[4], board[7]},
                {board[2], board[5], board[8]},
                {board[0], board[4], board[8]},
                {board[2], board[4], board[6]}};
        for (int i = 1; i <= 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (Arrays.equals(new int[]{i, i, i}, toCheck[j])) {
                    if (i == getPlayerHuman(gameID)) {
                        return "You win!";
                    } else {
                        return "Computer wins!";
                    }
                }
            }
        }
        return null;
    }

    public static String checkForDraw(int gameID) {
        for (int i = 0; i < 9; i++) {
            if (getField(gameID, i) == 0) {
                return null;
            }
        }
        return "Draw!";
    }

    private GameActions() {
    }

}
