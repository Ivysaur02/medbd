package com.example.medbd.controllers;


import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdmController {

    @FXML
    private Button AddBut;

    @FXML
    private Button DelBut;

    @FXML
    private TableView<Doctor> DoctTable;

    @FXML
    private Tab DoctorTab;

    @FXML
    private TableColumn<Doctor, String> FamDoct;

    @FXML
    private TableColumn<Doctor, String> ImDoct;

    @FXML
    private Button UpdBut;

    @FXML
    private TableColumn<Doctor, String> idDoct;

    @FXML
    private TableColumn<Doctor, String> otchDoct;

    @FXML
    private TableColumn<Doctor, String> roomDoct;

    @FXML
    private TableColumn<Doctor, String> spesDoct;

    @FXML
    void DelDoc(ActionEvent event) {

    }

    @FXML
    void UpdDoc(ActionEvent event) {

    }

    @FXML
    void addDoc(ActionEvent event) {

    }

    private Connection connection = null;
    ObservableList<Doctor> DoctorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        showDoctors();

    }

    private void getDoctorFromBD() throws SQLException {
        DoctorList.clear();
        connection = BdTools.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM doctor");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String patronymic = resultSet.getString(4);
            String specialization = resultSet.getString(5);
            String roomNumber = resultSet.getString(6);
            Doctor doctor = new Doctor(id, name, surname, patronymic, specialization, roomNumber);
            DoctorList.add(doctor);
        }
    }

    private void showDoctors() {
        try {
            getDoctorFromBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList(DoctorList); // создаем ObservableList из ArrayList

        idDoct.setCellValueFactory(new PropertyValueFactory<>("id")); // привязываем значения столбцов к свойствам Doctor
        FamDoct.setCellValueFactory(new PropertyValueFactory<>("Fam"));
        ImDoct.setCellValueFactory(new PropertyValueFactory<>("Imya"));
        otchDoct.setCellValueFactory(new PropertyValueFactory<>("Otch"));
        spesDoct.setCellValueFactory(new PropertyValueFactory<>("IdSpec"));
        roomDoct.setCellValueFactory(new PropertyValueFactory<>("Room"));

        DoctTable.setItems(doctorObservableList);
    }


}
