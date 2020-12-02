package org.helfried.tictactoeunb.core;

import static org.helfried.tictactoeunb.core.GameBoard.getPlayerAI;

public class StrategyTable {

    private static final int[][] xDepth0 = {

            {1, 2, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 2, 1, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 2, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 2, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 2, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 2}
    };

    private static final int[][] xDepth1 = {
            {1, 2, 0, 1, 1, 0, 2, 0, 0},
            {1, 0, 2, 1, 1, 0, 2, 0, 0},
            {1, 1, 2, 0, 2, 0, 1, 0, 0},
            {1, 0, 1, 0, 1, 2, 0, 0, 2},
            {1, 2, 1, 0, 1, 0, 0, 2, 0},
            {1, 2, 1, 0, 0, 0, 1, 0, 2}
    };

    private static final int[][] xDepth2 = {
            {1, 1, 2, 2, 2, 1, 1, 0, 0}
    };

    private static final int[][] oDepth0 = {
            {1, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 1, 0, 0, 2, 0, 0, 0, 0},
            {2, 0, 0, 0, 1, 0, 0, 0, 0}
    };

    private static final int[][] oDepth1 = {
            {1, 1, 2, 0, 2, 0, 0, 0, 0},
            {1, 2, 1, 0, 2, 0, 0, 0, 0},
            {1, 0, 0, 0, 2, 1, 0, 2, 0},
            {1, 2, 0, 0, 2, 0, 0, 0, 1},
            {0, 1, 2, 1, 2, 0, 0, 0, 0},
            {2, 1, 0, 0, 2, 0, 0, 1, 0},
            {2, 1, 0, 0, 1, 0, 0, 2, 0},
            {2, 0, 1, 0, 1, 0, 2, 0, 0},
            {2, 0, 0, 2, 1, 1, 0, 0, 0}
    };

    private static final int[][] oDepth2 = {
            {1, 2, 1, 1, 2, 0, 2, 0, 0},
            {1, 2, 1, 2, 2, 0, 0, 1, 0},
            {1, 1, 2, 0, 2, 1, 0, 2, 0},
            {1, 2, 0, 0, 2, 0, 2, 1, 1},
            {2, 1, 2, 1, 2, 0, 1, 0, 0},
            {2, 1, 0, 0, 2, 0, 2, 1, 1},
            {2, 1, 0, 1, 1, 2, 0, 2, 0},
            {2, 1, 1, 0, 1, 0, 2, 2, 0},
            {2, 1, 0, 2, 1, 1, 0, 2, 0},
            {2, 1, 2, 0, 1, 0, 1, 2, 0},
            {2, 1, 0, 2, 1, 0, 0, 2, 1},
            {2, 0, 2, 2, 1, 1, 1, 0, 0}
    };

    private static final int[][] oDepth3 = {
            {2, 1, 2, 1, 1, 2, 1, 2, 0},
            {2, 1, 2, 2, 1, 1, 1, 2, 0},
            {1, 2, 1, 2, 2, 1, 0, 1, 2},
            {1, 2, 1, 2, 2, 1, 1, 0, 2},
            {2, 0, 1, 1, 2, 2, 2, 1, 1}
    };

    static int[][][] xStrategyBoards = {xDepth0, xDepth1, xDepth2};
    static int[][][] oStrategyBoards = {oDepth0, oDepth1, oDepth2, oDepth3};
    static int[][][][] strategy = {xStrategyBoards, oStrategyBoards};

    public static int[] getStrategyBoard(int id, int depthIndex, int boardIndex) {
        return strategy[getPlayerAI(id) - 1][depthIndex][boardIndex];
    }

    public static void setStrategyBoard(int id, int[] strategyBoard, int depthIndex, int boardIndex) {
        strategy[getPlayerAI(id) - 1][depthIndex][boardIndex] = deepCopyBoard(strategyBoard);
    }

    public static int[] deepCopyBoard(int[] board) {
        int[] newBoard = new int[9];
        System.arraycopy(board, 0, newBoard, 0, 9);
        return newBoard;
    }

    public static int getBoardIterations(int id, int depthIndex) {
        return strategy[getPlayerAI(id) - 1][depthIndex].length;
    }

    private StrategyTable() {
    }

}