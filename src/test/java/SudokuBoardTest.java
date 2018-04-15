import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.pl.Dao;
import pl.lodz.p.pl.FileSudokuBoardDao;
import pl.lodz.p.pl.SudokuBoard;
import pl.lodz.p.pl.SudokuBoardDaoFactory;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;

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
    void TestToString() {
        int[][] incorrectSudoku = {
                {4, 2, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},

                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},

                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        settingSudokuBoard(incorrectSudoku);
        String fs = sb.getBox(0,0).toString();
        String ss = sb.toString();
        String ps = sb.prettyToString();

//        String path = Paths.get("BoardFile.ser").toAbsolutePath().toString();
//
//        File  cFile = new File("BoardFile.ser");
//
//        String cpath = cFile.getAbsolutePath();

        SudokuBoard test = new SudokuBoard();
        test.set(0,1,8);

        String fileName = "BoardFileT.ser";

        try(FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao(fileName);
            FileSudokuBoardDao dao2 = SudokuBoardDaoFactory.getFileDao("BoardFile.ser")) {
            dao.write(sb);
            dao2.write(test);

//            SudokuBoard tBoard = dao.read();
//            SudokuBoard tBoard2 = dao2.read();
//            String tbs = tBoard.prettyToString();
//            String tbs2 = tBoard2.prettyToString();
//            System.out.println(tbs2);
        } catch (EOFException e) {
            // ... this is fine
        } catch (Exception e) {
            e.printStackTrace();
        }

        try(FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao("BoardFileT.ser")) {
            SudokuBoard tBoard = dao.read();
            String tbs = tBoard.prettyToString();
            System.out.println(tbs);
        } catch (EOFException e) {
            // ... this is fine
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    void TestSerialization() {
        int[][] incorrectSudoku = {
                {4, 2, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},

                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},

                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        settingSudokuBoard(incorrectSudoku);

        SudokuBoard test = new SudokuBoard();
        test.set(0,1,8);

        String fileName = "BoardFileT.ser";

        try(FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao(fileName);
            FileSudokuBoardDao dao2 = SudokuBoardDaoFactory.getFileDao("BoardFile.ser")) {
            dao.write(sb);
            dao2.write(test);

//            SudokuBoard tBoard = dao.read();
//            SudokuBoard tBoard2 = dao2.read();
//            String tbs = tBoard.prettyToString();
//            String tbs2 = tBoard2.prettyToString();
//            System.out.println(tbs2);
        } catch (EOFException e) {
            // ... this is fine
        } catch (Exception e) {
            e.printStackTrace();
        }

        try(FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao("BoardFileT.ser")) {
            SudokuBoard tBoard = dao.read();
            String tbs = tBoard.prettyToString();
            System.out.println(tbs);
        } catch (EOFException e) {
            // ... this is fine
        } catch (Exception e) {
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