package pl.lodz.p.pl;

//import java.util.Objects;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SudokuField implements Serializable{

    private int value;


    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {
        this.value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
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
        return Objects.equal(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }
}