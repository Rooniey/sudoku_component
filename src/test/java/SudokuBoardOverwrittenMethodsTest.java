import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.pl.SudokuBoard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuBoardOverwrittenMethodsTest {

    private SudokuBoard sb, sbCopy, sbDiff;

    @BeforeEach
    public void setUp() {
        sb = new SudokuBoard();
        sbCopy = new SudokuBoard();
        sbDiff = new SudokuBoard();
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

        settingSudokuBoard(sb, correctSudoku);
        settingSudokuBoard(sbCopy, correctSudoku);
        correctSudoku[0][0] = 9;
        settingSudokuBoard(sbDiff, correctSudoku);
    }

    @Test
    void When_EqualsIsCalledWithNullRef_Expect_ReturnsFalse() {
        assertFalse(sb.equals(null));
    }

    @Test
    void When_EqualsIsCalledWithSameRef_Expect_ReturnsTrue() {
        assertTrue(sb.equals(sb));
    }

    @Test
    void When_EqualsIsCalledWithEqualValues_Expect_ReturnsTrue() {
        assertTrue(sb.equals(sbCopy));
    }

    @Test
    void Equals_IsSymmetric() {
        assertTrue(sb.equals(sbCopy) == sbCopy.equals(sb));
        assertTrue(sb.equals(sbDiff) == sbDiff.equals(sb));
    }

    @Test
    void Equals_IsTransitive() {
        assertTrue(!(sb.equals(sbCopy) && sbCopy.equals(sbDiff)) || sb.equals(sbDiff)); // p implies q === !p || q
    }

    @Test
    void Equals_IsConsistent() {
        assertTrue(sb.equals(sbCopy) == sb.equals(sbCopy)); //multiple invocations don't change the result
    }

    @Test
    void HashCode_IsConsistent() {
        assertTrue(sb.hashCode() == sb.hashCode()); //multiple invocations don't change the result
    }

    @Test
    void When_HashCodeIsCalledWithValidRef_Expect_ReturnsTrue() {
        assertTrue(sb.hashCode() == sbCopy.hashCode());
    }

    @Test
    void When_HashCodeIsCalledWithInvalidRef_Expect_ReturnsFalse() {
        assertFalse(sb.hashCode() == sbDiff.hashCode());
    }


    private void settingSudokuBoard(SudokuBoard sb, final int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.set(i,j,arr[i][j]);
            }
        }
    }


}
