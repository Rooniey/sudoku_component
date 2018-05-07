import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.pl.FileSudokuBoardDao;
import pl.lodz.p.pl.SudokuBoard;
import pl.lodz.p.pl.SudokuBoardDaoFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;

class FileSudokuBoardDaoTest {

    private SudokuBoard writtenBoard;
    private SudokuBoard readBoard;

    private final String fileName = "DaoTestBoard.ser";

    @BeforeEach
    public void setUp() {
    	readBoard = new SudokuBoard();
        writtenBoard = new SudokuBoard();
        int[][] sampleSudoku = {
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
        settingSudokuBoard(sampleSudoku);
    }
    
    @AfterEach
    public void cleanUp() {
    	try {
    		Files.deleteIfExists(Paths.get("DaoTestBoard.ser"));
    	}
    	catch(Exception e){}
    }

    @Test
    void When_DaoUsedToWriteAndReadFromFile_Expect_BoardsAreIdentical() {

        try (FileSudokuBoardDao dao = (FileSudokuBoardDao)SudokuBoardDaoFactory.getFileDao(fileName)){
            dao.write(writtenBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileSudokuBoardDao dao =  (FileSudokuBoardDao)SudokuBoardDaoFactory.getFileDao(fileName)){
            readBoard = dao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(writtenBoard.equals(readBoard));

    }
    
    @Test
    void When_DaoUsedToWriteAndReadFromFile_Expect_ReadBoardToBeFullyFunctional() {

        try (FileSudokuBoardDao dao =  (FileSudokuBoardDao)SudokuBoardDaoFactory.getFileDao(fileName)){
            dao.write(writtenBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileSudokuBoardDao dao =  (FileSudokuBoardDao)SudokuBoardDaoFactory.getFileDao(fileName)){
            readBoard = dao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        readBoard.set(0, 0, 4);
        assertEquals(readBoard.get(0, 0), 4);

    }

    private void settingSudokuBoard(final int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                writtenBoard.set(i,j,arr[i][j]);
            }
        }
    }
}