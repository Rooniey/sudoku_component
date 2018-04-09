package pl.lodz.p.pl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(final SudokuBoard board) {

        if (!board.isFilled()) {

            //find the next empty cell
            int[] emptyCell = findNextEmptyCell(board);
            int x = emptyCell[0];
            int y = emptyCell[1];

            //select possible values in found empty cell and shuffle them
            List<Integer> possibilities = possibleEntries(x, y, board);
            Collections.shuffle(possibilities);

            for (Integer possibility : possibilities) {
                board.set(x, y, possibility);
                solve(board);
                if (board.isFilled()) {
                    return;
                }
                board.set(x, y, 0);
            }
        }
    }

    private int[] findNextEmptyCell(final SudokuBoard board) {
        int[] emptyCell = new int[2];

        for (int i = 0; i < SudokuBoard.RowLen; i++) {
            for (int j = 0; j < SudokuBoard.RowLen; j++) {
                if (board.get(i, j) == 0) {
                    emptyCell[0] = i;
                    emptyCell[1] = j;
                    return emptyCell;
                }
            }
        }
        return emptyCell;
    }


    private List<Integer> possibleEntries(int row, int col, final SudokuBoard board) {
        List<Integer> possibilities = new ArrayList<>();
        for (int i = 1; i < SudokuBoard.RowLen + 1; i++) {
            possibilities.add(i);
        }

        //check row
        for (int i = 0; i < SudokuBoard.RowLen; i++) {
            if (board.get(row, i) != 0) {
                possibilities.remove(Integer.valueOf(board.get(row, i)));
            }
        }

        //check col
        for (int j = 0; j < SudokuBoard.RowLen; j++) {
            if (board.get(j, col) != 0) {
                possibilities.remove(Integer.valueOf(board.get(j, col)));
            }
        }

        //check box
        int leftUpperX = (row / SudokuBoard.BoxLen) * SudokuBoard.BoxLen;
        int leftUpperY = (col / SudokuBoard.BoxLen) * SudokuBoard.BoxLen;

        for (int i = leftUpperX; i < leftUpperX + SudokuBoard.BoxLen; i++) {
            for (int j = leftUpperY; j < leftUpperY + SudokuBoard.BoxLen; j++) {
                if (board.get(i, j) != 0) {
                    possibilities.remove(Integer.valueOf(board.get(i, j)));
                }
            }
        }

        return possibilities;
    }
}
