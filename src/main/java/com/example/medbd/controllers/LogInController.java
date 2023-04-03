package com.example.medbd.controllers;


import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.bdApplic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {

    @FXML
    private Button LogInButton;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswField;
    Connection connection = null;

    @FXML
    public void initialize() {
        LogInButton.setOnAction(event -> {
           checkUser(LoginField.getText(),PasswField.getText());
        });

    }

    private void checkUser(String login, String password) {
        try {
            connection = BdTools.getConnection();
            // создаем запрос к базе данных
            PreparedStatement select = connection.prepareStatement("SELECT * FROM receptionist WHERE login = ? AND password = ?");
            select.setString(1, login);
            select.setString(2, password);
            // выполняем запрос
            ResultSet rs = select.executeQuery();
            // если результат запроса не пустой, то возвращаем true
            if (rs.next()) {
                String title = rs.getString("title");
                System.out.println(title);
                if (title.equals("0")) {
                    openAdminWindow();
                } else {
                    //запуск для регистратора
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Неверно введены данные!");
                alert.showAndWait();
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openAdminWindow() throws  IOException {
        // Создание нового окна
        FXMLLoader loader = new FXMLLoader(bdApplic.class.getResource("AdminPanel.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle("AdminPanel");
        newStage.setScene(new Scene(root));
        newStage.show();

    }
}
