import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.pl.SudokuBoard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuBoardTest {

    private SudokuBoard sb;

    @BeforeEach
    public void setUp() {
        sb = new SudokuBoard();
    }

    @Test
    public void When_CheckBoardIsCalledOnCorrectSudokuBoard_Expect_ReturnsTrue()
    {
        // The correct example of sudoku from Wikipedia
        int[][] correctSudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},

                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},

                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        settingSudokuBoard(correctSudoku);

        assertTrue(sb.checkBoard());
    }

    @Test
    void When_CheckBoardIsCalledOnIncorrectSudokuBoard_Expect_ReturnsFalse() {

        // The incorrect example of sudoku (modified from Wikipedia)
        int[][] incorrectSudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},

                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 3, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 5, 8, 5, 6},

                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 3, 9}
        };

        settingSudokuBoard(incorrectSudoku);

        assertFalse(sb.checkBoard());
    }


    @Test
    void When_CloneIsCalled_Expect_ReturnsClonedObjectWithDifferentMemoryAddress() {
        try{
            SudokuBoard cloned = (SudokuBoard) sb.clone();
            assertTrue(sb != cloned);
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }

    @Test
    void When_CloneIsCalled_Expect_ReturnsClonedObjectEqualToOriginal() {
        try{
            SudokuBoard cloned = (SudokuBoard) sb.clone();
            assertTrue(sb.equals(cloned));
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }


    @Test
    void When_CloneIsCalled_Expect_ReturnsClonedObjectOfTheSameClassAsOriginal() {
        try{
            SudokuBoard cloned = (SudokuBoard) sb.clone();
            assertTrue(sb.getClass() == cloned.getClass());
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }

    private void settingSudokuBoard(final int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.set(i,j,arr[i][j]);
            }
        }
    }


}