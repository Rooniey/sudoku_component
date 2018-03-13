import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.pl.SudokuBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoardTest {

    private static int rowLen = 9;
    private static int boxLen = 3;

    private SudokuBoard sb;

    @Before
    public void setUp() {
        sb = new SudokuBoard();
    }

    @Test
    public void When_FillBoardIsCalled_Expect_BeFilledProperly() {
        sb.fillBoard();
        int[][] board = sb.getBoard();

        Assert.assertTrue(checkRows(board));
        Assert.assertTrue(checkCols(board));
        Assert.assertTrue(checkBoxes(board));
    }


    @Test
    public void When_FillBoardIsCalledTwice_Expect_GeneratesTwoDifferentBoards() {
        sb.fillBoard();
        int[][] firstSudoku = sb.getBoard();

        sb.resetBoard();

        sb.fillBoard();
        int[][] secondSudoku = sb.getBoard();

        Assert.assertFalse(Arrays.deepEquals(firstSudoku, secondSudoku));

    }


    private boolean checkRows(int[][] board) {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int row = 0; row < rowLen; row++) {

            //getSudokuNumbers
            for (int i = 1; i < rowLen + 1; i++) {
                possibilities.add(i);
            }

            for (int i = 0; i < rowLen; i++) {
                possibilities.remove(Integer.valueOf(board[row][i]));
            }

            if (!possibilities.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCols(int[][] board) {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int col = 0; col < rowLen; col++) {

            //getSudokuNumbers
            for (int i = 1; i < rowLen + 1; i++) {
                possibilities.add(i);
            }

            for (int i = 0; i < rowLen; i++) {
                possibilities.remove(Integer.valueOf(board[i][col]));
            }

            if (!possibilities.isEmpty()) {
                return false;
            }

        }
        return true;
    }


    private boolean checkBoxes(int[][] board) {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int row = 0; row < rowLen; row += boxLen) {
            for (int col = 0; col < rowLen; col += boxLen) {

                //getSudokuNumbers
                for (int i = 1; i < rowLen + 1; i++) {
                    possibilities.add(i);
                }

                for (int i = row; i < row + boxLen; i++) {
                    for (int j = col; j < col + boxLen; j++) {
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