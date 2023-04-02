package com.example.medbd.controllers;


import com.example.medbd.BdConnection.BdTools;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
            String login = LoginField.getText();
            String pasword = PasswField.getText();
            if (checkUser(login, pasword))
                System.out.print("Pobeda");
        });

    }

    private boolean checkUser(String login, String password) {
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
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // если пользователь не найден, то возвращаем false
        return false;
    }
}
