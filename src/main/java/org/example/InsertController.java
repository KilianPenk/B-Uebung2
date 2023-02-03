import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class InsertController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField wohnortField;
    private Person person;
    @FXML
    private void initialize() {
    }
    public void setPerson(Person person) {
        this.person = person;
        idField.setText(String.valueOf(person.getId()));
        nameField.setText(person.getName());
        wohnortField.setText(person.getWohnort());
    }
    @FXML
    private void handleOk() {
        person.setId(Integer.parseInt(idField.getText()));
        person.setName(nameField.getText());
        person.setWohnort(wohnortField.getText());
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}