package com.example.medbd.controllers;
import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdDoctController {
    @FXML
    private TextField FamTextField;

    @FXML
    private TextField ImyaTextField;

    @FXML
    private TextField OtchTextField;

    @FXML
    private TextField RoomTextField;


    private Doctor person = null;

    @FXML
    private Button UpdButton;
    private Connection connection;

    @FXML
    void UpdDoctor(ActionEvent event) throws SQLException {
        //todo написать ошибки
        String query = "UPDATE doctor SET imya=?, fam=?, otch=?, room=? WHERE id=?";
        connection = BdTools.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, ImyaTextField.getText());
        statement.setString(2,FamTextField.getText());
        statement.setString(3,OtchTextField.getText());
        statement.setInt(4, Integer.parseInt(RoomTextField.getText()));
        statement.setInt(5,Integer.parseInt(person.getId()));

        statement.executeUpdate();

    }
    public void initData(Doctor person) {
        this.person = person;
        ImyaTextField.setText(person.getImya());
        FamTextField.setText(person.getFam());
        OtchTextField.setText(person.getOtch());
        RoomTextField.setText(person.getRoom());

    }
}
