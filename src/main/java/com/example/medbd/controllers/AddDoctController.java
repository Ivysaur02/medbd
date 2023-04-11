package com.example.medbd.controllers;

import java.sql.*;


import com.example.medbd.BdConnection.BdTools;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddDoctController {

    @FXML
    private TextField firstNameField;

    @FXML
    private Label AlertLabel;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField middleNameField;

    @FXML
    private ComboBox<String> titleComboBox;

    @FXML
    private TextField roomField;

    public void initialize() {
        try {
            showTitle();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Connection connection = null;

    @FXML
    void addDoctor() throws SQLException {
        // Получение данных из элементов интерфейса
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String middleName = middleNameField.getText();
        String title = titleComboBox.getSelectionModel().getSelectedItem();
        String room = roomField.getText();

        // Валидация данных (можно добавить дополнительные проверки, если нужно)
        if (firstName.isEmpty() || lastName.isEmpty() || middleName.isEmpty() || title == null || room.isEmpty()) {
            AlertLabel.setText("Введите все поля");
        }
        connection = BdTools.getConnection(); // Получаем соединение с базой данных

        String selmax = "SELECT MAX(id) + 1 FROM doctor";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selmax);
        resultSet.next(); // Необходимо выполнить перед чтением данных из ResultSet
        String ind = resultSet.getString(1);

        String selin = "SELECT id_specialty FROM med_specialty WHERE name_of_specialty = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(selin);
        preparedStatement1.setString(1, title);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        resultSet1.next(); // Необходимо выполнить перед чтением данных из ResultSet
        String titleind = resultSet1.getString(1);

        String query = "INSERT INTO doctor VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(ind));
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, middleName);
        preparedStatement.setInt(5, Integer.parseInt(titleind));
        preparedStatement.setInt(6, Integer.parseInt(room));
        preparedStatement.executeUpdate();

        resultSet.close();
        statement.close();


        // Очистка полей после добавления врача
        clearFields();
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle("Успех");
        message.setHeaderText(null);
        message.setContentText("Пользователь добавлен");
        message.showAndWait();
    }

    private void showTitle() throws SQLException {
        // Заполнение ComboBox значениями из таблицы title
        connection = BdTools.getConnection();
        String query = "SELECT name_of_specialty FROM med_specialty";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            titleComboBox.getItems().add(resultSet.getString(1));
        }
        resultSet.close();
        statement.close();

    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        middleNameField.setText("");
        roomField.setText("");
        titleComboBox.setValue(null);
    }
}
