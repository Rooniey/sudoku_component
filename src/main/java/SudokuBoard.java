package pl.lodz.p.pl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private static final int rowLen = 9;    //length of the sudoku board
    private static final int boxLen = 3;    //length of a side of small boxes (that sudoku is built upon)

    private int[][] board;

    public SudokuBoard() {
        board = new int[rowLen][rowLen];
    }

    public void fillBoard() {
        if (isFilled()) {
            return;
        } else {
            //find the next empty cell
            int[] emptyCell = findNextEmptyCell();
            int x = emptyCell[0];
            int y = emptyCell[1];

            //select possible values in found empty cell and shuffle them
            List<Integer> possibilities = possibleEntries(x, y);
            Collections.shuffle(possibilities);

            for (Integer possibility : possibilities) {
                board[x][y] = possibility;
                fillBoard();
                if (isFilled()) {
                    return;
                }
                board[x][y] = 0;
            }
        }
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    private boolean isFilled() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] findNextEmptyCell() {
        int[] emptyCell = new int[2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    emptyCell[0] = i;
                    emptyCell[1] = j;
                    return emptyCell;
                }
            }
        }

        return emptyCell;
    }


    private List<Integer> possibleEntries(int row, int col) {
        List<Integer> possibilities = new ArrayList<>();
        for (int i = 1; i < rowLen + 1; i++) {
            possibilities.add(i);
        }

        //check row
        for (int i = 0; i < rowLen; i++) {
            if (board[row][i] != 0) {
                possibilities.remove(Integer.valueOf(board[row][i]));
            }
        }

        //check col
        for (int j = 0; j < rowLen; j++) {
            if (board[j][col] != 0) {
                possibilities.remove(Integer.valueOf(board[j][col]));
            }
        }

        //check box
        int leftUpperX = (row / boxLen) * boxLen;
        int leftUpperY = (col / boxLen) * boxLen;

        for (int i = leftUpperX; i < leftUpperX + boxLen; i++) {
            for (int j = leftUpperY; j < leftUpperY + boxLen; j++) {
                if (board[i][j] != 0) {
                    possibilities.remove(Integer.valueOf(board[i][j]));
                }
            }
        }

        return possibilities;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();

        for (int i = 0; i < rowLen; i++) {

            if (i % boxLen == 0) {
                boardString.append("------------------------------\n");
            }

            boardString.append("|");

            for (int j = 0; j < rowLen; j++) {
                boardString.append(this.board[i][j]).append((j + 1) % boxLen == 0 ? " | " : "  ");
            }

            boardString.append("\n");

        }

        boardString.append("------------------------------\n");

        return boardString.toString();
    }

    //getBoard returns deepcopy of the board
    public int[][] getBoard() {
        int[][] toReturn = new int[rowLen][rowLen];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                toReturn[i][j] = board[i][j];
            }
        }

        return toReturn;
    }
}
