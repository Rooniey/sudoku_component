package pl.lodz.p.pl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import pl.lodz.p.pl.SudokuField; //MAVEN COMMENT
//import pl.lodz.p.pl.SudokuSegment; //MAVEN COMMENT

public class SudokuBoard {

    public static final int RowLen = 9;    //length of the sudoku board
    public static final int BoxLen = 3;    //length of a side of small boxes (that sudoku is built upon)

    private SudokuField[][] board = new SudokuField[RowLen][RowLen];

//    private int[][] board;

    public SudokuBoard() {
        for (int i = 0; i < RowLen; i++) {
            for (int j = 0; j < RowLen; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    //probably useless now
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setFieldValue(0);
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

    public SudokuSegment getRow(int y) {

        return new SudokuSegment(Arrays.asList(board[y]).subList(0, RowLen));
    }

    public SudokuSegment getColumn(int x) {
        ArrayList<SudokuField> col = new ArrayList<SudokuField>();

        for (int i = 0; i < RowLen; i++) {
            col.add(board[i][x]);
        }
        return new SudokuSegment(col);
    }

    public SudokuSegment getBox(int x, int y) {
        int firstX = (x / 3) * 3;
        int firstY = (y / 3) * 3;

        ArrayList<SudokuField> box = new ArrayList<SudokuField>();

        for (int i = firstY; i < firstY + 3; i++) {
            box.addAll(Arrays.asList(board[i]).subList(firstX, firstX + 3));
        }
        return new SudokuSegment(box);
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
                boardString.append(this.board[i][j].getFieldValue()).append((j + 1) % BoxLen == 0 ? " | " : "  ");
            }

            boardString.append("\n");

        }

        boardString.append("------------------------------\n");

        return boardString.toString();
    }

    //getBoard returns deepcopy of the board
    public SudokuField[][] getBoard() {
        SudokuField[][] toReturn = new SudokuField[RowLen][RowLen];

        for (int i = 0; i < RowLen; i++) {
//            toReturn[i] = board[i].clone();
            for (int j = 0; j < RowLen; j++) {
                toReturn[i][j] = board[i][j];
            }
        }
        return toReturn;
    }

    public boolean checkBoard() {

        //check all rows and columns
        for (int i = 0; i < RowLen; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }

        //check all 3x3 boxes
        for (int i = 0; i < RowLen; i += 3) {
            for (int j = 0; j < RowLen; j += 3) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }

        return true;
    }
}
