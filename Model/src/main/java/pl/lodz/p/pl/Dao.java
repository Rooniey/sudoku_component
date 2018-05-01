package pl.lodz.p.pl;

import java.io.IOException;
import java.io.Serializable;

public interface Dao<T extends Serializable> {

    T read() throws IOException, ClassNotFoundException;
    void write(T obj) throws IOException;

}
