package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.Patient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;

public class AddPatientController {

    @FXML
    private Button AddPatientButton;

    @FXML
    private TextField ApartField;

    @FXML
    private Button ClearButton;

    @FXML
    private TextField DateField;

    @FXML
    private Button ExitButton;

    @FXML
    private TextField FamTextField;

    @FXML
    private TextField ImyaTextField;

    @FXML
    private TextField OtchTextField;

    @FXML
    private TextField PhoneField;

    @FXML
    private TextField RoomField;

    @FXML
    private TextField SNILSField;

    @FXML
    private ComboBox<String> SexCombBox;

    @FXML
    private TextField StreetField;

    private Connection connection;

    @FXML
    void addPAtient(ActionEvent event) throws SQLException {
        if (!FamTextField.getText().isEmpty() & !ImyaTextField.getText().isEmpty() & !(SexCombBox.getSelectionModel().getSelectedItem() == null)
                & !DateField.getText().isEmpty() & !SNILSField.getText().isEmpty()) {
            String fam = FamTextField.getText();
            String im = ImyaTextField.getText();
            String otch = OtchTextField.getText();
            String sex = SexCombBox.getSelectionModel().getSelectedItem();
            String date = DateField.getText();
            String SNILS = SNILSField.getText();
            String phone = PhoneField.getText();
            String street = StreetField.getText();
            String apart = ApartField.getText();
            String room = RoomField.getText();

            connection = BdTools.getConnection(); // Получаем соединение с базой данных

            String selmax = "SELECT MAX(id_medcard) + 1 FROM medcard";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selmax);
            resultSet.next(); // Необходимо выполнить перед чтением данных из ResultSet
            String ind = resultSet.getString(1);

            Patient patient = new Patient(ind, fam,im,otch,sex,date,street,apart,room,SNILS,phone);

            String query = "INSERT INTO medcard VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(patient.getId()));
            preparedStatement.setString(2, patient.getFam());
            preparedStatement.setString(3, patient.getImya());
            preparedStatement.setString(4, patient.getOtch());
            preparedStatement.setString(5, patient.getSex());
            preparedStatement.setDate(6, Date.valueOf(patient.getDate()));
            preparedStatement.setString(7, patient.getStreet());
            if (!patient.getApart().isEmpty())
                preparedStatement.setInt(9, Integer.parseInt(patient.getApart()));
            else
                preparedStatement.setNull(9, Types.INTEGER);
            preparedStatement.setString(8, patient.getHome());
            preparedStatement.setString(10, patient.getPhone());
            preparedStatement.setString(11, patient.getSnils());


            preparedStatement.executeUpdate();

        }
    }

    @FXML
    void clearField(ActionEvent event) {
        FamTextField.clear();
        ImyaTextField.clear();
        OtchTextField.clear();
        SexCombBox.setValue(null);
        DateField.clear();
        SNILSField.clear();
        PhoneField.clear();
        StreetField.clear();
        ApartField.clear();
        RoomField.clear();
    }

    @FXML
    void exit(ActionEvent event) {
        Stage st = (Stage) addPatientPan.getScene().getWindow();
        st.close();
    }


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Node addPatientPan;

    @FXML
    public void initialize() {
        SexCombBox.getItems().addAll("М", "Ж");
        addPatientPan.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        addPatientPan.setOnMouseDragged(event -> {
            Stage stage = (Stage) addPatientPan.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

    }


}

