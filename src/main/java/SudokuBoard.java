package pl.lodz.p.pl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private static final int rowLen = 9;

    private int[][] board;

    public SudokuBoard() {
        board = new int[9][9];
    }

    public void fillBoard() {

        if (isFilled()) {
            return;
        } else {
            //find the first empty cell
            int x = -1;
            int y = -1;
            for (int i = 0; i < board.length && x < 0; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }

            List<Integer> possibilities = possibleEntries(x, y);
            Collections.shuffle(possibilities);

            for (int i = 0; i < possibilities.size(); i++) {

                board[x][y] = possibilities.get(i);
                fillBoard();
                if (isFilled()) {
                    return;
                }
                board[x][y] = 0;
            }
        }
    }

    private boolean isFilled() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Integer> possibleEntries(int row, int col) {
        List<Integer> possibilities = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            possibilities.add(i);
        }

        //check row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0) {
                possibilities.remove(Integer.valueOf(board[row][i]));
            }
        }

        //check col
        for (int j = 0; j < 9; j++) {
            if (board[j][col] != 0) {
                possibilities.remove(Integer.valueOf(board[j][col]));
            }
        }

        //check box
        int leftUpperX = (row / 3) * 3;
        int leftUpperY = (col / 3) * 3;

        for (int i = leftUpperX; i < leftUpperX + 3; i++) {
            for (int j = leftUpperY; j < leftUpperY + 3; j++) {
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

            if (i % 3 == 0) {
                boardString.append("------------------------------\n");
            }

            boardString.append("|");

            for (int j = 0; j < rowLen; j++) {
                boardString.append(this.board[i][j]).append((j + 1) % 3 == 0 ? " | " : "  ");
            }

            boardString.append("\n");

        }

        boardString.append("------------------------------\n");

        return boardString.toString();
    }


}
