package pl.lodz.p.pl;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;

    private ObjectOutputStream oOut;
    private ObjectInputStream oIn;

    private FileOutputStream fileOut;
    private FileInputStream fileIn;

    public FileSudokuBoardDao(String fileName) throws IOException {
        this.fileName = fileName;

        fileOut = new FileOutputStream(fileName);
        fileIn = new FileInputStream(fileName);

        oOut = new ObjectOutputStream(fileOut);
        oIn = new ObjectInputStream(fileIn);
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        return (SudokuBoard) oIn.readObject();
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
//        try(FileOutputStream fileOut = new FileOutputStream(fileName);
//            ObjectOutputStream oOut = new ObjectOutputStream(fileOut)) {
//
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
        oOut.writeObject(obj);
        oOut.flush();

    }

    @Override
    public void close() throws Exception {
        oOut.close();
        oIn.close();
        fileOut.close();
        fileIn.close();
    }

    @Override
    public void finalize() throws Exception {
        this.close();
    }
}
