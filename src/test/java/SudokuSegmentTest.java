import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import pl.lodz.p.pl.SudokuBoard;
import pl.lodz.p.pl.SudokuSegment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuSegmentTest {

    private SudokuSegment sf, sf0;

    private int baseRowValue = 0;

    private SudokuBoard correctBoard;

    @BeforeEach
    public void setUp() {
        correctBoard = new SudokuBoard();
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

        sf = correctBoard.getRow(baseRowValue);
        sf0 = correctBoard.getColumn(baseRowValue);
    }


    @Test
    void When_EqualsIsCalledWithNullRef_Expect_ReturnsFalse() {
        assertFalse(sf.equals(null));
    }

    @Test
    void When_EqualsIsCalledWithSameRef_Expect_ReturnsTrue() {
        assertTrue(sf.equals(sf));
    }

    @Test
    void When_EqualsIsCalledWithEqualValues_Expect_ReturnsTrue() {
        SudokuSegment sf2 = correctBoard.getRow(baseRowValue);
        assertTrue(sf.equals(sf2));
    }

    @Test
    void Equals_IsSymmetric() {

        SudokuSegment sf3 = correctBoard.getRow(baseRowValue);
        assertTrue(sf.equals(sf0) == sf0.equals(sf));
        assertTrue(sf.equals(sf3) == sf3.equals(sf));
    }

    @Test
    void Equals_IsTransitive() {
        SudokuSegment sf2 = correctBoard.getRow(baseRowValue);
        SudokuSegment sf3 =  correctBoard.getRow(baseRowValue);
        SudokuSegment sf4 = correctBoard.getRow(7);

        assertTrue(!(sf.equals(sf2) && sf2.equals(sf3)) || sf.equals(sf3)); // p implies q === !p || q
        assertTrue(!(sf.equals(sf2) && sf2.equals(sf4)) || sf.equals(sf4));
    }

    @Test
    void Equals_IsConsistent() {
        SudokuSegment sf2 = correctBoard.getRow(baseRowValue);

        assertTrue(sf.equals(sf2) == sf.equals(sf2)); //multiple invocations don't change the result
    }

    @Test
    void HashCode_IsConsistent() {
        assertTrue(sf.hashCode() == sf.hashCode()); //multiple invocations don't change the result
    }

    @Test
    void When_HashCodeIsCalledWithValidRef_Expect_ReturnsTrue() {
        SudokuSegment sf2 = correctBoard.getRow(baseRowValue);
        assertTrue(sf.hashCode() == sf2.hashCode());
    }

    @Test
    void When_HashCodeIsCalledWithInvalidRef_Expect_ReturnsFalse() {
        SudokuSegment sf2 = correctBoard.getRow(2);
        assertFalse(sf.hashCode() == sf2.hashCode());
    }


    private void settingSudokuBoard(final int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                correctBoard.set(i,j,arr[i][j]);
            }
        }
    }


}
