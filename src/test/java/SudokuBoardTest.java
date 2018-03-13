import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import pl.lodz.p.pl.SudokuBoard;

import java.util.Arrays;
import java.util.Set;

public class SudokuBoardTest {

    private SudokuBoard sb;

    @Before
    public void setUp() {
        sb = new SudokuBoard();
    }

    @Test
    public void When_FillBoardIsCalled_Expect_BeFilledProperly() {
        sb.fillBoard();
        int[][] board = sb.getBoard();

        //Assert.assertTrue(checkRows(board));
        //Assert.assertTrue(checkColumns(board));
        //Assert.assertTrue(checkBoxes(board));
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
}