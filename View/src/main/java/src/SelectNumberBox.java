import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SelectNumberBox {

    @FXML
    private Button resetButton;
    @FXML
    private GridPane numbersGrid;

    private Button sourceButton;

    private String selectedNumber = "D";

    public void initialize() {

        for (int i = 0; i < numbersGrid.getChildren().size(); i++) {

            ((Button) numbersGrid.getChildren().get(i)).setOnAction(e -> {

                selectedNumber = ((Button)e.getSource()).getText();

                System.out.println(selectedNumber);
                sourceButton.setText(selectedNumber);

                Node source = (Node)e.getSource();
                Stage stage = (Stage)source.getScene().getWindow();
                stage.close();
            });
        }
    }

    public void setSourceButton(Button button) {
        sourceButton = button;
    }

    public void getSelectedNumber(Button button) {
       button.setText(selectedNumber);
    }

    public void resetButton_click(ActionEvent actionEvent) {
        sourceButton.setText(" ");

        Node source = (Node)actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }
}
