import pl.lodz.p.pl.SudokuBoard;
import pl.lodz.p.pl.SudokuField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {

    private SudokuBoard sb;
    private SudokuSolver ss;

    @BeforeEach
    void setUp() {
        sb = new SudokuBoard();
        ss = new BacktrackingSudokuSolver();
    }

    @Test
    void When_SolveIsCalled_Expect_SudokuBoardToBeFilledProperly() {
        ss.solve(sb);
        sb.getBoard();

        assertTrue(checkRows());
        assertTrue(checkCols());
        assertTrue(checkBoxes());
    }

    @Test
    public void When_SolveIsCalledTwice_Expect_GeneratesTwoDifferentBoards() {
        ss.solve(sb);
        SudokuField[][] first = sb.getBoard();

        SudokuBoard secondBoard = new SudokuBoard();
        ss.solve(secondBoard);
        SudokuField[][] second = secondBoard.getBoard();

        assertFalse(Arrays.deepEquals(first, second));
    }

    private boolean checkRows() {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int row = 0; row < SudokuBoard.RowLen; row++) {

            //getSudokuNumbers
            for (int i = 1; i < SudokuBoard.RowLen + 1; i++) {
                possibilities.add(i);
            }

            for (int i = 0; i < SudokuBoard.RowLen; i++) {
                possibilities.remove(Integer.valueOf(sb.get(row,i)));
            }

            if (!possibilities.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCols() {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int col = 0; col < SudokuBoard.RowLen; col++) {

            //getSudokuNumbers
            for (int i = 1; i < SudokuBoard.RowLen + 1; i++) {
                possibilities.add(i);
            }

            for (int i = 0; i < SudokuBoard.RowLen; i++) {
                possibilities.remove(Integer.valueOf(sb.get(i,col)));
            }

            if (!possibilities.isEmpty()) {
                return false;
            }

        }
        return true;
    }


    private boolean checkBoxes() {
        List<Integer> possibilities = new ArrayList<Integer>();

        for (int row = 0; row < SudokuBoard.RowLen; row += SudokuBoard.BoxLen) {
            for (int col = 0; col < SudokuBoard.RowLen; col += SudokuBoard.BoxLen) {

                //getSudokuNumbers
                for (int i = 1; i < SudokuBoard.RowLen + 1; i++) {
                    possibilities.add(i);
                }

                for (int i = row; i < row + SudokuBoard.BoxLen; i++) {
                    for (int j = col; j < col + SudokuBoard.BoxLen; j++) {
                        possibilities.remove(Integer.valueOf(sb.get(i,j)));
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