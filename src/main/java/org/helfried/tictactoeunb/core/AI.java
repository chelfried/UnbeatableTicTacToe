package org.helfried.tictactoeunb.core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import static org.helfried.tictactoeunb.core.GameActions.checkForWin;
import static org.helfried.tictactoeunb.core.GameBoard.*;
import static org.helfried.tictactoeunb.core.StrategyTable.*;

public class AI {

    private static SecureRandom random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void actionByAI(int gameID) {
        if (Arrays.equals(getActiveGameBoard(gameID), new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0})) {
            makeFirstMove(gameID);
        } else if (!tryForWinningMove(gameID) && !tryForStrategicMove(gameID)) {
            randomMove(gameID);
        }
        setPlayerTurn(gameID, getPlayerHuman(gameID));
    }

    private static void makeFirstMove(int gameID) {
        int[] randomPick = {0, 2, 6, 8};
        setFieldAI(gameID, randomPick[random.nextInt(4)]);
    }

    private static int getGameDepth(int gameID) {
        int counter = 0;
        for (int field : getActiveGameBoard(gameID))
            if (field != 0) {
                counter++;
            }
        return counter;
    }

    private static int getDepthIndex(int gameID) {
        int depthIndex = getGameDepth(gameID);
        if (depthIndex <= 2) {
            depthIndex = 0;
        } else if (depthIndex <= 4) {
            depthIndex = 1;
        } else if (depthIndex <= 6) {
            depthIndex = 2;
        } else if (depthIndex == 7) {
            depthIndex = 3;
        } else {
            depthIndex = -1;
        }
        return depthIndex;
    }

    private static boolean tryForWinningMove(int gameID) {
        for (int i = 0; i < 9; i++) {
            int[] board = deepCopyBoard(getActiveGameBoard(gameID));
            if (board[i] == 0) {
                board[i] = getPlayerAI(gameID);
                if (checkForWin(gameID, board) != null) {
                    setFieldAI(gameID, i);
                    return true;
                }
            }
        }
        return false;
    }

    private static void rotate(int gameID, int depthIndex, int boardIndex) {
        int[] newBoard = new int[9];
        int[] board = getStrategyBoard(gameID, depthIndex, boardIndex);
        newBoard[0] = board[2];
        newBoard[1] = board[5];
        newBoard[2] = board[8];
        newBoard[3] = board[1];
        newBoard[4] = board[4];
        newBoard[5] = board[7];
        newBoard[6] = board[0];
        newBoard[7] = board[3];
        newBoard[8] = board[6];
        setStrategyBoard(gameID, newBoard, depthIndex, boardIndex);
    }

    private static void mirror(int gameID, int depthIndex, int boardIndex) {
        int[] newBoard = new int[9];
        int[] board = getStrategyBoard(gameID, depthIndex, boardIndex);
        newBoard[0] = board[2];
        newBoard[1] = board[1];
        newBoard[2] = board[0];
        newBoard[3] = board[5];
        newBoard[4] = board[4];
        newBoard[5] = board[3];
        newBoard[6] = board[8];
        newBoard[7] = board[7];
        newBoard[8] = board[6];
        setStrategyBoard(gameID, newBoard, depthIndex, boardIndex);
    }

    private static boolean tryForStrategicMove(int gameID) {
        int depthIndex = getDepthIndex(gameID);
        int boardIterations = 0;
        if (depthIndex >= 0) {
            boardIterations = getBoardIterations(gameID, depthIndex);
        }
        for (int boardIndex = 0; boardIndex < boardIterations; boardIndex++) {
            for (int mirror = 0; mirror < 2; mirror++) {
                for (int rotation = 0; rotation < 4; rotation++) {
                    if (checkForMatch(gameID, depthIndex, boardIndex)) {
                        setActiveGameBoard(gameID, getStrategyBoard(gameID, depthIndex, boardIndex));
                        return true;
                    }
                    rotate(gameID, depthIndex, boardIndex);
                }
                mirror(gameID, depthIndex, boardIndex);
            }
        }
        return false;
    }

    private static void randomMove(int gameID) {
        while (true) {
            int i = random.nextInt(9);
            if (getField(gameID, i) == 0) {
                setFieldAI(gameID, i);
                return;
            }
        }
    }

    private static boolean checkForMatch(int gameID, int depthIndex, int boardIndex) {
        int matchingCounter = 0;
        int gameDepth = getGameDepth(gameID);
        for (int i = 0; i < 9; i++) {
            if (getField(gameID, i) == getStrategyBoard(gameID, depthIndex, boardIndex)[i] && getField(gameID, i) != 0) {
                matchingCounter++;
                if (matchingCounter == gameDepth) {
                    setActiveGameBoard(gameID, getStrategyBoard(gameID, depthIndex, boardIndex));
                    return true;
                }
            }
        }
        return false;
    }

    private AI() {
    }

}
