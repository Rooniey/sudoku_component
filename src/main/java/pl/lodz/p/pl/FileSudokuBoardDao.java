package pl.lodz.p.pl;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;

    private ObjectOutputStream oOut;
    private ObjectInputStream oIn;


    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        oIn = new ObjectInputStream(new FileInputStream(fileName));
        SudokuBoard toRet = (SudokuBoard) oIn.readObject();
        oIn.close();
        return toRet;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        oOut = new ObjectOutputStream(new FileOutputStream(fileName));
        oOut.writeObject(obj);
        oOut.flush();
        oOut.close();
    }

    @Override
    public void close() throws Exception {
        if(oOut != null) {
            oOut.close();
        }
        if(oIn != null) {
            oIn.close();
        }

    }

    @Override
    public void finalize() throws Exception {
        this.close();
    }
}
