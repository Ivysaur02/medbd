package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.MedHistory;
import com.example.medbd.models.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientPanelController {

    @FXML
    private Button AddHistoryButton;

    @FXML
    private CheckBox CheckBoxUPD;

    @FXML
    private Button CloseButton;

    @FXML
    private TextField PatientHome;

    @FXML
    private TextField PatientDate;

    @FXML
    private TextField PatientFam;

    @FXML
    private Tab PatientInfoTab;

    @FXML
    private Tab PatientMedHistoryTab;

    @FXML
    private TextField PatientName;

    @FXML
    private TextField PatientOtch;

    @FXML
    private TextField PatientRoom;

    @FXML
    private TextField PatientSex;

    @FXML
    private TextField PatientSnils;

    @FXML
    private TextField PatientStreet;

    @FXML
    private Button SearchHistoryButton;

    @FXML
    private Button UpdButton;

    @FXML
    private TableColumn<MedHistory, String> DateHistory;

    @FXML
    private TableView<MedHistory> HistoryTableView;

    @FXML
    private TableColumn<MedHistory, String> TypeHistory;


    @FXML
    private TableColumn<MedHistory, String> idHistory;


    @FXML
    private TextField PhonePatient;

    Patient patient;
    private Connection connection;


    @FXML
    void ChangeUPD(ActionEvent event) {
        boolean isReadOnly = CheckBoxUPD.isSelected();
        PatientFam.setEditable(isReadOnly);
        PatientDate.setEditable(isReadOnly);
        PatientName.setEditable(isReadOnly);
        PatientOtch.setEditable(isReadOnly);
        PatientSex.setEditable(isReadOnly);
        PatientSnils.setEditable(isReadOnly);
        PhonePatient.setEditable(isReadOnly);
        PatientStreet.setEditable(isReadOnly);
        PatientHome.setEditable(isReadOnly);
        PatientRoom.setEditable(isReadOnly);
    }

    @FXML
    void UpdPatient(ActionEvent event) throws SQLException {
        PreparedStatement statement = BdTools.getConnection().prepareStatement(
                "UPDATE medcard " +
                        "SET snils = ?, " +
                        "date_birth = ?, " +
                        "apart = ?, " +
                        "sex = ?, " +
                        "phone = ?, " +
                        "street = ?, " +
                        "home = ?, " +
                        "fam = ?, " +
                        "imya = ?, " +
                        "otch = ? " +
                        "WHERE id_medcard = ?"
        );

        statement.setInt(1, Integer.parseInt(PatientSnils.getText()));
        statement.setDate(2, java.sql.Date.valueOf(PatientDate.getText()));
        //TODO проверять ебучую дату
        statement.setInt(3, Integer.parseInt(PatientRoom.getText()));
        statement.setString(4, PatientSex.getText());
        statement.setString(5, PhonePatient.getText());
        statement.setString(6, PatientStreet.getText());
        statement.setString(7, PatientHome.getText());
        statement.setString(8, PatientFam.getText());
        statement.setString(9, PatientName.getText());
        statement.setString(10, PatientOtch.getText());
        statement.setInt(11, Integer.parseInt(patient.getId()));

        statement.executeUpdate();


    }

    @FXML
    void addhistory(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchhistory(ActionEvent event) {

    }

    @FXML
    public void initialize() {

    }

    public void getPatientInfo(Patient pat){
        this.patient = pat;
        PatientFam.setText(patient.getFam());
        PatientDate.setText(patient.getDate());
        PatientName.setText(patient.getImya());
        PatientOtch.setText(patient.getOtch());
        PatientSex.setText(patient.getSex());
        PatientSnils.setText(patient.getSnils());
        PhonePatient.setText(patient.getPhone());
        PatientStreet.setText(patient.getStreet());
        PatientHome.setText(patient.getHome());
        PatientRoom.setText(patient.getApart());
        PatientFam.setEditable(false);
        PatientDate.setEditable(false);
        PatientName.setEditable(false);
        PatientOtch.setEditable(false);
        PatientSex.setEditable(false);
        PatientSnils.setEditable(false);
        PhonePatient.setEditable(false);
        PatientStreet.setEditable(false);
        PatientHome.setEditable(false);
        PatientRoom.setEditable(false);
    }

}

