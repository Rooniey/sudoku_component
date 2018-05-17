import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.lodz.p.pl.BacktrackingSudokuSolver;
import pl.lodz.p.pl.SudokuBoard;
import pl.lodz.p.pl.SudokuSolver;

import java.io.IOException;

public class Controller {

    @FXML
    GridPane TopGrid;
    @FXML
    GridPane SudokuGrid;
    @FXML
    Button Button_ShowField;
    @FXML
    ChoiceBox<SudokuBoard.Difficulties> DifficultySelector;
    @FXML
    public void initialize() {

        DifficultySelector.getItems().addAll(SudokuBoard.Difficulties.values());
        DifficultySelector.setValue(SudokuBoard.Difficulties.Medium);

        System.out.println(SudokuGrid.getColumnConstraints().size());
        System.out.println(SudokuGrid.getRowConstraints().size());

        SudokuGrid.setVisible(false);

        for (int i = 0; i < SudokuBoard.RowLen; i++) {
            for (int j = 0; j < SudokuBoard.RowLen; j++) {

                SudokuGrid.add(new Button("0"), i, j);

            }
        }
    }


    public void selectDifficulty(MouseEvent mouseEvent) throws IOException {

        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        board.setDifficulty(DifficultySelector.getValue());
        SudokuGrid.setVisible(true);

        for (int i = 0; i < SudokuBoard.RowLen; i++) {
            for (int j = 0; j < SudokuBoard.RowLen; j++) {

              Button button = ((Button) SudokuGrid.getChildren().get(i * SudokuBoard.RowLen + j));

              button.setOnAction(e -> {

                  try {
                      Stage numberSelectionStage =  new Stage();
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/NumberBoxStage.fxml"));
                      Parent root = loader.load();
                      numberSelectionStage.setTitle("Sudoku");
                      numberSelectionStage.setScene(new Scene(root));

                      Button thisButton = (Button)e.getSource();
                      ((SelectNumberBox)loader.getController()).setSourceButton(thisButton);

                      numberSelectionStage.show();


//                    ((SelectNumberBox)loader.getController()).getSelectedNumber(thisButton);

                  } catch (IOException e1) {
                      e1.printStackTrace();
                  }


              });

              int value = board.get(i, j);
              String toSet = " ";
              if(value != 0) {
                  toSet = Integer.toString(value);
              }
              button.setText(toSet);
              button.setDisable(!(value == 0));


            }
        }


    }
}
