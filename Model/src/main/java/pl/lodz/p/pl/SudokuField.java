package pl.lodz.p.pl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {

    transient private IntegerProperty fieldValue;


    public SudokuField(int fieldValue) {
        this.fieldValue = new SimpleIntegerProperty(fieldValue);
    }

    public SudokuField() {
        fieldValue = new SimpleIntegerProperty(0);
    }

    public int getFieldValue() {
        return fieldValue.get();
    }

    public IntegerProperty fieldValueProperty() {
        return fieldValue;
    }

    public void setFieldValue(int fieldValue) {
        this.fieldValue.set(fieldValue);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return Objects.equal(this.fieldValue.get(), that.fieldValue.get());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.fieldValue.get());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fieldValue", fieldValue)
                .toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(final SudokuField o) {
        return Integer.compare(this.getFieldValue(), o.getFieldValue());
    }


    //customized serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(fieldValue.get());
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int x = in.readInt();
        fieldValue = new SimpleIntegerProperty(x);
    }
}
