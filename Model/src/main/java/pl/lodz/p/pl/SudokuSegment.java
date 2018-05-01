package pl.lodz.p.pl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuSegment {

    private List<SudokuField> fields;

    public SudokuSegment(final List<SudokuField> fields) {
        this.fields = Arrays.asList(new SudokuField[fields.size()]);
        for (int i = 0; i < fields.size(); i++) {
            this.fields.set(i, fields.get(i));
        }
    }

    public boolean verify() {

        int length = fields.size();
        //populate the list with ints ranging from 1 to {length}
        List<Integer> possibilities = IntStream.rangeClosed(1, length).boxed().collect(Collectors.toList());

        //remove a number from the list if encountered in the segment
        for (int i = 0; i < length; i++) {
            possibilities.remove(Integer.valueOf(fields.get(i).getFieldValue()));
        }

        //if the list isn't empty, then there were duplicates
        if (!possibilities.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuSegment that = (SudokuSegment) o;
        return Objects.equal(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fields);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fields", fields)
                .toString();
    }
}
