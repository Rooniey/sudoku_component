package pl.lodz.p.pl;

import java.io.IOException;

public class SudokuBoardDaoFactory {

    public static FileSudokuBoardDao getFileDao(String fileName) throws IOException {
        return new FileSudokuBoardDao(fileName);
    }

}
