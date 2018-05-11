package src;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import pl.lodz.p.pl.BacktrackingSudokuSolver;
import pl.lodz.p.pl.SudokuBoard;
import pl.lodz.p.pl.SudokuSolver;

public class Controller {

    @FXML
    GridPane TopGrid;
    @FXML
    GridPane SudokuGrid;
    @FXML
    Button Button_ShowField;
    @FXML
    ChoiceBox<SudokuBoard.Difficulties> DifficultySelector;

    public void initialize() {

        DifficultySelector.getItems().addAll(SudokuBoard.Difficulties.values());
        DifficultySelector.setValue(SudokuBoard.Difficulties.Medium);

        System.out.println(SudokuGrid.getColumnConstraints().size());
        System.out.println(SudokuGrid.getRowConstraints().size());

        //hide the sudoku
        SudokuGrid.setVisible(false);

        for (int i = 0; i < SudokuBoard.RowLen; i++) {
            for (int j = 0; j < SudokuBoard.RowLen; j++) {

//                SudokuGrid.add(new TextField("0");, i, j);
                SudokuGrid.add(new Button("0"), i, j);

            }
        }
    }


    public void selectDifficulty(MouseEvent mouseEvent) {

        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        board.setDifficulty(DifficultySelector.getValue());
        SudokuGrid.setVisible(true);

        for (int i = 0; i < SudokuBoard.RowLen; i++) {
            for (int j = 0; j < SudokuBoard.RowLen; j++) {

//              TextField field = ((TextField) SudokuGrid.getChildren().get(i * SudokuBoard.RowLen + j));
//              field.setText(Integer.toString(board.get(i, j)));

              Button button = ((Button) SudokuGrid.getChildren().get(i * SudokuBoard.RowLen + j));
              button.setText(Integer.toString(board.get(i, j)));

            }
        }


    }
}
