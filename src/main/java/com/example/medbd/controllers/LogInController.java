package com.example.medbd.controllers;


import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.bdApplic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {
    @FXML
    private Button CancelButton;

    @FXML
    private Button LogInButton;

    @FXML
    private TextField LoginField;

    @FXML
    private Label MessageLabel;

    @FXML
    private PasswordField PaswField;

    @FXML
    void closeStage(ActionEvent event) {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }


    Connection connection = null;


    @FXML
    public void initialize() {
        LogInButton.setOnAction(event ->
                checkUser(LoginField.getText(), PaswField.getText(), (Stage) LogInButton.getScene().getWindow()));

    }

    private void checkUser(String login, String password, Stage stage) {
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
                    loadUserWindow("AdminPanel.fxml", "AdminPanel");
                } else {
                    loadUserWindow("RegistrPanel.fxml", "RegistrPanel");
                }
                stage.close();
            } else {
                MessageLabel.setText("Неправильный логин или пароль");
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadUserWindow(String fail, String title) throws IOException {
        // Создание нового окна
        FXMLLoader loader = new FXMLLoader(bdApplic.class.getResource(fail));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle(title);
        newStage.setScene(new Scene(root));
        newStage.show();

    }
}
