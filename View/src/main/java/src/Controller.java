import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import pl.lodz.p.pl.*;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static javafx.beans.binding.Bindings.bindBidirectional;

public class Controller {

    @FXML
    GridPane TopGrid;
    @FXML
    GridPane SudokuGrid;
    @FXML
    Button Button_ShowField;
    @FXML
    ChoiceBox<SudokuBoard.Difficulties> DifficultySelector;

    private SudokuBoard board;

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

    @FXML
    private void openFile() {
        String fileName = chooseFile();
        try(FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao(fileName)) {
            board = dao.read();
            SudokuGrid.setVisible(true);
            functionalizeButtons();

            for (int i = 0; i < board.RowLen; i++) {
                for (int j = 0; j < board.RowLen; j++) {
                    Button button = ((Button) SudokuGrid.getChildren().get(i * SudokuBoard.RowLen + j));
                    if(!board.getBox(i, j).verify()
                            || !board.getRow(i).verify()
                            || !board.getColumn(j).verify()) {
                        button.setStyle("-fx-background-color: red");
                        button.setDisable(false);
                    }
                    else {
                        button.setStyle("-fx-background-color: lightgreen");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectDifficulty(MouseEvent mouseEvent) throws IOException {

        board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        board.setDifficulty(DifficultySelector.getValue());
        SudokuGrid.setVisible(true);

        functionalizeButtons();
    }

    private void functionalizeButtons() {
        for (int i = 0; i < SudokuBoard.RowLen; i++) {
            for (int j = 0; j < SudokuBoard.RowLen; j++) {

              Button button = ((Button) SudokuGrid.getChildren().get(i * SudokuBoard.RowLen + j));
              button.setStyle("-fx-background-color: lightgreen");


              //bound
              bindBidirectional(
                      button.textProperty(),
                      board.getField(i, j).fieldValueProperty(),
                      new NumberStringConverter()
              );

              button.setOnAction(e -> {
                  try {
                      Stage numberSelectionStage =  new Stage();
                      URL url = getClass().getResource("/NumberBoxStage.fxml");
                      FXMLLoader loader = new FXMLLoader(url);
                      Parent root = loader.load();
                      numberSelectionStage.setTitle("Sudoku");
                      numberSelectionStage.setScene(new Scene(root));

                      //Relate the clicked button to the new number selection window
                      Button thisButton = (Button)e.getSource();
                      ((SelectNumberBox)loader.getController()).setSourceButton(thisButton);

                      numberSelectionStage.show();

                  } catch (IOException e1) {
                      e1.printStackTrace();
                      System.out.println(Arrays.toString(e1.getStackTrace()));
                  }
              });

                int finalI = i;
                int finalJ = j;
                button.textProperty().addListener(new ChangeListener<String>() {
                  @Override
                  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                      if(!board.getBox(finalI, finalJ).verify()
                              || !board.getRow(finalI).verify()
                              || !board.getColumn(finalJ).verify()) {
                          button.setStyle("-fx-background-color: red");
                          button.setDisable(false);
                      }
                      else {
                          button.setStyle("-fx-background-color: lightgreen");
                      }
                  }
              });

              setButtonValue(i, j, button);


            }
        }
    }

    private void setButtonValue(int i, int j, Button button) {
        int value = board.get(i, j);
        String toSet = "  ";
        if(value != 0) {
            toSet = Integer.toString(value);
        }
        button.setText(toSet);
        button.setDisable(!(value == 0));
    }



    private String chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file path");
        String file = fileChooser.showOpenDialog(new Stage()).getPath();
        return file;
    }



    @FXML
    private void writeFile() {
        String fileName = chooseFile();
        try(FileSudokuBoardDao dao = SudokuBoardDaoFactory.getFileDao(fileName)) {
            dao.write(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
