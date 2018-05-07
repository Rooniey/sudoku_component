package pl.lodz.p.pl;


public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(final String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

}
