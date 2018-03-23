package pl.lodz.p.pl;

//import pl.lodz.p.pl.SudokuField; //MAVEN COMMENT

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.lodz.p.pl.SudokuBoard.RowLen;

public class SudokuSegment {

    private List<SudokuField> fields;

    public SudokuSegment(final List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {

        //populate the list with ints ranging from 1 to {RowLen}
        List<Integer> possibilities = IntStream.rangeClosed(1, RowLen).boxed().collect(Collectors.toList());

        //remove a number from the list if encountered in the segment
        for (int i = 0; i < RowLen; i++) {
            possibilities.remove(Integer.valueOf(fields.get(i).getFieldValue()));
        }

        //if the list isn't empty, then there were duplicates
        if (!possibilities.isEmpty()) {
            return false;
        }

        return true;
    }
}
