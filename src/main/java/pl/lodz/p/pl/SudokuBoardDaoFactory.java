package pl.lodz.p.pl;

import java.io.IOException;

public class SudokuBoardDaoFactory {

    public static FileSudokuBoardDao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

}
