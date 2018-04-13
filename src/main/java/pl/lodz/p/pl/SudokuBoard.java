package pl.lodz.p.pl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {

    public static final int RowLen = 9;    //length of the sudoku board
    public static final int BoxLen = 3;    //length of a side of small boxes (that sudoku is built upon)

    private List<SudokuField> board;

    public SudokuBoard() {
        board = Arrays.asList(new SudokuField[RowLen * RowLen]);
        for (int i = 0; i < RowLen; i++) {
            for (int j = 0; j < RowLen; j++) {
                board.set(i * RowLen + j, new SudokuField());
            }
        }
    }

    public int get(int x, int y) {
        return board.get(x * RowLen + y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        board.get(x * RowLen + y).setFieldValue(value);
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

        ArrayList<SudokuField> row = new ArrayList<SudokuField>();

        for (int i = 0; i < RowLen; i++) {
            row.add(new SudokuField(get(y, i)));
        }
        return new SudokuSegment(row);
    }

    public SudokuSegment getColumn(int x) {

        ArrayList<SudokuField> col = new ArrayList<SudokuField>();

        for (int i = 0; i < RowLen; i++) {
            col.add(new SudokuField(get(i, x)));
        }
        return new SudokuSegment(col);
    }

    //return 3x3 boxex with copies of sudokufields
    public SudokuSegment getBox(int x, int y) {
        int firstX = (x / 3) * 3;
        int firstY = (y / 3) * 3;

        ArrayList<SudokuField> box = new ArrayList<SudokuField>();

        for (int i = 0; i < BoxLen; i++) {
            for (int j = 0; j < BoxLen; j++) {
                box.add(new SudokuField(get(firstX + i, firstY + j)));
            }
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
                boardString.append(board.get(i * RowLen + j).getFieldValue()).append((j + 1) % BoxLen == 0 ? " | " : "  ");
            }

            boardString.append("\n");

        }

        boardString.append("------------------------------\n");

        return boardString.toString();
    }

    //getBoard returns deepcopy of the board
    public List<SudokuField> getBoard() {
        List<SudokuField> toReturn = Arrays.asList(new SudokuField[RowLen * RowLen]);

        for (int i = 0; i < RowLen; i++) {
            for (int j = 0; j < RowLen; j++) {
                toReturn.set(i * RowLen + j, new SudokuField(get(i, j)));
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
