package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdUserController {

    @FXML
    private TextField FamTextField;

    @FXML
    private TextField ImTextField;

    @FXML
    private TextField LoginTextField;

    @FXML
    private TextField OtchTextField;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private Label WarningLabel;

    @FXML
    private Button UpdUSerButton;

    User user;
    Connection connection;

    @FXML
    void updUser(ActionEvent event) throws SQLException {
        if(LoginTextField.getText().equals("") || PasswordTextField.getText().equals("")){
            WarningLabel.setText("Логи или пароль не могут быть пустыми");
            return;
        }
        String query = "UPDATE receptionist SET imya=?, fam=?, otch=?, login=?, password=? WHERE id=?";
        connection = BdTools.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, ImTextField.getText());
        statement.setString(2,FamTextField.getText());
        statement.setString(3,OtchTextField.getText());
        statement.setString(4,LoginTextField.getText());
        statement.setString(5,PasswordTextField.getText());
        statement.setInt(6,Integer.parseInt(user.getId()));

        statement.executeUpdate();
    }



    public void initData(User user){
        this.user=user;
        SetInfo();
    }

    private void SetInfo(){
        ImTextField.setText(user.getImya());
        FamTextField.setText(user.getFam());
        OtchTextField.setText(user.getOtch());
        LoginTextField.setText(user.getLogin());
        PasswordTextField.setText(user.getPassword());
    }
}
