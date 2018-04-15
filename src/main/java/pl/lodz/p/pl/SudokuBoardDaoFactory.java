package pl.lodz.p.pl;


public class SudokuBoardDaoFactory {

    public static FileSudokuBoardDao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

}
