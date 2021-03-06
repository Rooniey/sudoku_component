import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.pl.SudokuField;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuFieldTest {

    private SudokuField sf;
    private final int baseFieldValue = 8;

    @BeforeEach
    public void setup()
    {
        sf = new SudokuField(baseFieldValue);
    }

    @Test
    void When_EqualsIsCalledWithNullRef_Expect_ReturnsFalse() {
        assertFalse(sf.equals(null));
    }

    @Test
    void When_EqualsIsCalledWithSameRef_Expect_ReturnsTrue() {
        assertTrue(sf.equals(sf));
    }

    @Test
    void When_EqualsIsCalledWithEqualValues_Expect_ReturnsTrue() {
        SudokuField sf2 = new SudokuField(baseFieldValue);
        assertTrue(sf.equals(sf2));
    }

    @Test
    void When_EqualsIsCalledWithDifferentValues_Expect_ReturnsTrue() {
        SudokuField sf2 = new SudokuField(baseFieldValue + 1);
        assertFalse(sf.equals(sf2));
    }

    @Test
    void Equals_IsSymmetric() {
        SudokuField sf2 = new SudokuField(baseFieldValue);
        SudokuField sf3 = new SudokuField(1);
        assertTrue(sf.equals(sf2) == sf2.equals(sf));
        assertTrue(sf.equals(sf3) == sf3.equals(sf));
    }

    @Test
    void Equals_IsTransitive() {
        SudokuField sf2 = new SudokuField(baseFieldValue);
        SudokuField sf3 = new SudokuField(baseFieldValue);
        SudokuField sf4 = new SudokuField(baseFieldValue - 1);

        assertTrue(!(sf.equals(sf2) && sf2.equals(sf3)) || sf.equals(sf3)); // p implies q === !p || q
        assertTrue(!(sf.equals(sf2) && sf2.equals(sf4)) || sf.equals(sf4));
    }

    @Test
    void Equals_IsConsistent() {
        SudokuField sf2 = new SudokuField(baseFieldValue);

        assertTrue(sf.equals(sf2) == sf.equals(sf2)); //multiple invocations don't change the result
    }

    @Test
    void HashCode_IsConsistent() {
        assertTrue(sf.hashCode() == sf.hashCode()); //multiple invocations don't change the result
    }

    @Test
    void When_HashCodeIsCalledWithValidRef_Expect_ReturnsTrue() {
        SudokuField sf2 = new SudokuField(baseFieldValue);
        assertTrue(sf.hashCode() == sf2.hashCode());
    }

    @Test
    void When_HashCodeIsCalledWithInvalidRef_Expect_ReturnsFalse() {
        SudokuField sf2 = new SudokuField(baseFieldValue - 1);
        assertFalse(sf.hashCode() == sf2.hashCode());
    }

    @Test
    void When_CompareToIsCalledOnSmallerSudokuField_Expect_ReturnNegativeInteger() {
        SudokuField sf2 = new SudokuField(baseFieldValue + 1);
        assertTrue( sf.compareTo(sf2) < 0);
    }

    @Test
    void When_CompareToIsCalledOnBiggerSudokuField_Expect_ReturnPositiveInteger() {
        SudokuField sf2 = new SudokuField(baseFieldValue - 1);
        assertTrue(sf.compareTo(sf2) > 0);
    }
    @Test
    void When_CompareToIsCalledOnEqualSudokuFields_Expect_ReturnZero() {
        SudokuField sf2 = new SudokuField(baseFieldValue);
        assertTrue(sf.compareTo(sf2) == 0);
    }

    @Test
    void When_CloneIsCalled_Expect_ReturnsClonedObjectWithDifferentMemoryAddress() {
        try{
            SudokuField cloned = (SudokuField)sf.clone();
            assertTrue(sf != cloned);
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }

    @Test
    void When_CloneIsCalled_Expect_ReturnsClonedObjectEqualToOriginal() {
        try{
            SudokuField cloned = (SudokuField)sf.clone();
            assertTrue(sf.equals(cloned));
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }


    @Test
    void When_CloneIsCalled_Expect_ReturnsClonedObjectOfTheSameClassAsOriginal() {
        try{
            SudokuField cloned = (SudokuField)sf.clone();
            assertTrue(sf.getClass() == cloned.getClass());
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }


}
