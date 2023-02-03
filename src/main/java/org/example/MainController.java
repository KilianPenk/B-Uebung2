package org.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class MainController {
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, String> wohnortColumn;
    private ObservableList<Person> data;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        wohnortColumn.setCellValueFactory(new PropertyValueFactory<>("wohnort"));
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }
    @FXML
    public void refreshData() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/B_Uebung", "B_Uebung", "B_Uebung");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String wohnort = resultSet.getString("wohnort");
                data.add(new Person(id, name, wohnort));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void insertData() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/B_Uebung", "B_Uebung", "B_Uebung");
            statement = connection.createStatement();
            int id = data.get(data.size() - 1).getId() + 1;
            String name = "New Person";
            String wohnort = "New Wohnort";
            statement.executeUpdate("INSERT INTO person (id, name, wohnort) VALUES (" + id + ", '" + name + "', '" + wohnort + "')");
            statement.close();
            connection.close();
            refreshData();
        }
    }
}