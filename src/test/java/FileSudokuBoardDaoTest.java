import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.pl.FileSudokuBoardDao;
import pl.lodz.p.pl.SudokuBoard;
import pl.lodz.p.pl.SudokuBoardDaoFactory;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    private SudokuBoard correctBoard;

    private String fileName = "DaoTestBoard.ser";

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
    }

    @Test
    void When_DaoUsedToWriteAndReadFromFile_Expect_BoardsAreIdentical() {

        SudokuBoard daoBoard = new SudokuBoard();

        try (FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao(fileName)){
            dao.write(correctBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao(fileName)){
            daoBoard = dao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(correctBoard.equals(daoBoard));

    }

    private void settingSudokuBoard(final int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                correctBoard.set(i,j,arr[i][j]);
            }
        }
    }
}