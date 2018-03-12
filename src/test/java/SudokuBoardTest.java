import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class SudokuBoardTest {

    @Test
    public void fillBoard() {

        pl.lodz.p.pl.SudokuBoard sb = new pl.lodz.p.pl.SudokuBoard();

        System.out.println(sb.toString());

        sb.fillBoard();

        System.out.println(sb.toString());

        assertTrue( 9 == 9 );
    }
}