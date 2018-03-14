package pl.lodz.p.pl;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

    public static final int RowLen = 9;    //length of the sudoku board
    public static final int BoxLen = 3;    //length of a side of small boxes (that sudoku is built upon)

    private int[][] board;

    public SudokuBoard() {
        board = new int[RowLen][RowLen];
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean isFilled() {
        //iterates from the last cell, because it is optimal for the most popular sudoku solver algorithm
        for (int i = RowLen - 1; i >= 0; i--) {
            for (int j = RowLen - 1; j >= 0; j--) {
                if (get(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();

        for (int i = 0; i < RowLen; i++) {

            if (i % BoxLen == 0) {
                boardString.append("------------------------------\n");
            }

            boardString.append("|");

            for (int j = 0; j < RowLen; j++) {
                boardString.append(this.board[i][j]).append((j + 1) % BoxLen == 0 ? " | " : "  ");
            }

            boardString.append("\n");

        }

        boardString.append("------------------------------\n");

        return boardString.toString();
    }

    //getBoard returns deepcopy of the board
    public int[][] getBoard() {
        int[][] toReturn = new int[RowLen][RowLen];
        for (int i = 0; i < board.length; i++) {
            toReturn[i] = board[i].clone();
        }

        return toReturn;
    }

    public boolean checkBoard() {
        return checkCols() && checkBoxes() && checkRows();
    }

    private boolean checkRows() {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int row = 0; row < RowLen; row++) {

            //getSudokuNumbers
            for (int i = 1; i < RowLen + 1; i++) {
                possibilities.add(i);
            }

            for (int i = 0; i < RowLen; i++) {
                possibilities.remove(Integer.valueOf(board[row][i]));
            }

            if (!possibilities.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    //code duplication caused by nonexistence of optimization disadvantages
    //and improved readability
    private boolean checkCols() {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int col = 0; col < RowLen; col++) {

            //getSudokuNumbers
            for (int i = 1; i < RowLen + 1; i++) {
                possibilities.add(i);
            }

            for (int i = 0; i < RowLen; i++) {
                possibilities.remove(Integer.valueOf(board[i][col]));
            }

            if (!possibilities.isEmpty()) {
                return false;
            }

        }
        return true;
    }


    private boolean checkBoxes() {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int row = 0; row < RowLen; row += BoxLen) {
            for (int col = 0; col < RowLen; col += BoxLen) {

                //getSudokuNumbers
                for (int i = 1; i < RowLen + 1; i++) {
                    possibilities.add(i);
                }

                for (int i = row; i < row + BoxLen; i++) {
                    for (int j = col; j < col + BoxLen; j++) {
                        possibilities.remove(Integer.valueOf(board[i][j]));
                    }
                }

                if (!possibilities.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
