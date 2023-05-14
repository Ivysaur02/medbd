package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class AddUserController {

    @FXML
    private Button AddButton;

    @FXML
    private Button CancelButton;

    @FXML
    private TextField FamField;

    @FXML
    private TextField ImyaField;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField OtchField;

    @FXML
    private Button ExitButton;

    @FXML
    private TextField PasswordField;

    @FXML
    private ComboBox<String> TitleComboBox;
    private Connection connection;

    @FXML
    void addUser(ActionEvent event) throws SQLException {
        //todo написать аллерты
        String im = ImyaField.getText();
        String fam = FamField.getText();
        String otc = OtchField.getText();
        String login = LoginField.getText();
        String pas = PasswordField.getText();
        int title = 0;
        switch (TitleComboBox.getSelectionModel().getSelectedItem()) {
            case "Администратор" -> title = 0;
            case "Регистратор" -> title = 1;
        }
        connection = BdTools.getConnection(); // Получаем соединение с базой данных

        String selmax = "SELECT MAX(id) + 1 FROM receptionist";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selmax);
        resultSet.next(); // Необходимо выполнить перед чтением данных из ResultSet
        String ind = resultSet.getString(1);

        String query = "INSERT INTO receptionist VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(ind));
        preparedStatement.setString(2, fam);
        preparedStatement.setString(3, im);
        preparedStatement.setString(4, otc);
        preparedStatement.setInt(5, title);
        preparedStatement.setString(6, login);
        preparedStatement.setString(7, pas);

        preparedStatement.executeUpdate();

        resultSet.close();
        statement.close();

        clean();
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle("Успех");
        message.setHeaderText(null);
        message.setContentText("Пользователь добавлен");
        message.showAndWait();
    }

    @FXML
    void cleanField(ActionEvent event) {
        clean();
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Node AddUser;

    @FXML
    public void initialize() {
        TitleComboBox.getItems().addAll("Администратор", "Регистратор");
        AddUser.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        AddUser.setOnMouseDragged(event -> {
            Stage stage = (Stage) AddUser.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    private void clean() {
        FamField.setText("");
        ImyaField.setText("");
        LoginField.setText("");
        PasswordField.setText("");
        TitleComboBox.setValue("");
        OtchField.setText("");
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

}
