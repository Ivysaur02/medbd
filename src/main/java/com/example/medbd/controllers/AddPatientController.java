package com.example.medbd.controllers;

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

    @FXML
    void addPAtient(ActionEvent event) {

    }

    @FXML
    void clearField(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Node addPatientPan;

    @FXML
    public void initialize(){
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

