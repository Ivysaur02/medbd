package com.example.medbd.controllers;


import com.example.medbd.models.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

public class AdmController {

    @FXML
    private Button AddBut;

    @FXML
    private Button DelBut;

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

}
