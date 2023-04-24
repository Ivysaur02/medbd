package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.Doctor;
import com.example.medbd.models.Patient;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Predicate;

public class RegController {

    @FXML
    private Button AddPatientButton;

    @FXML
    private TextField DateTextField;

    @FXML
    private TableColumn<Doctor, String> DegreeDoctor;

    @FXML
    private Tab DoctorTab;

    @FXML
    private TableView<Doctor> DoctorTableView;

    @FXML
    private TextField FamTextField;

    @FXML
    private TextField ImTextField;

    @FXML
    private Button OpenPatientButton;

    @FXML
    private TextField OtchTextField;

    @FXML
    private Tab PatientTab;

    @FXML
    private TableView<Patient> PatientTableView;

    @FXML
    private TableColumn<Doctor, String> RoomDoctor;

    @FXML
    private TableColumn<Patient, String> datePatient;

    @FXML
    private TableColumn<Patient, String> famPatient;

    @FXML
    private TableColumn<Patient, String> idPatient;

    @FXML
    private TableColumn<Patient, String> imPatient;

    @FXML
    private TableColumn<Patient, String> otcPatient;

    @FXML
    private TableColumn<Patient, String> sexPatient;

    @FXML
    private TableColumn<Patient, String> SNILS;

    @FXML
    private TextField SNILSTextField;

    @FXML
    private TextField SearchDoctorField;

    @FXML
    private Button SearchPatientButton;

    @FXML
    private TableColumn<Doctor, String> famDoctor;

    @FXML
    private TableColumn<Doctor, String> idDoctor;

    @FXML
    private TableColumn<Doctor, String> imDoctor;

    @FXML
    private Button openDoctor;

    @FXML
    private TableColumn<Doctor, String> otcDoctor;

    @FXML
    private Button showPatientButton;


    @FXML
    void clearSearch(ActionEvent event) {
        ImTextField.setText("");
        FamTextField.setText("");
        OtchTextField.setText("");
        DateTextField.setText("");
        SNILSTextField.setText("");
        showPatient();
    }

    @FXML
    void SearchPatient(ActionEvent event) {
        searchPatient();
    }

    @FXML
    void addPatient(ActionEvent event) {

    }

    @FXML
    void openDoctor(ActionEvent event) {

    }

    @FXML
    void openPatient(ActionEvent event) {

    }
    
    @FXML
    public void initialize() {
        showDoctors();
        showPatient();
        SearchDoctorField.textProperty().addListener(this::onSearchFieldChanged);
    }

    ObservableList<Doctor> DoctorList = FXCollections.observableArrayList();
    ObservableList<Patient> PatientList = FXCollections.observableArrayList();

    private void getDoctorFromBD() throws SQLException {
        DoctorList.clear();
        Connection connection = BdTools.getConnection();
        String sel =
                "SELECT doctor.id, doctor.fam, doctor.imya, doctor.otch, med_specialty.name_of_specialty, doctor.room" +
                        " FROM doctor " +
                        "INNER JOIN med_specialty ON doctor.specialty = med_specialty.id_specialty";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sel);
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
        resultSet.close();
        stmt.close();
    }

    private void getPatientFromBD() throws SQLException{
        PatientList.clear();
        Connection connection = BdTools.getConnection();
        String sel = "SELECT * FROM medcard";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sel);
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String fam = resultSet.getString(2);
            String im = resultSet.getString(3);
            String otc = resultSet.getString(4);
            String sex = resultSet.getString(5);
            String date = resultSet.getString(6);
            String street = resultSet.getString(7);
            String home = resultSet.getString(8);
            String apart = resultSet.getString(9);
            String phone = resultSet.getString(10);
            String snils = resultSet.getString(11);
            Patient pat = new Patient(id,fam,im,otc,sex,date,street,home,apart,snils,phone);
            PatientList.add(pat);
        }
        resultSet.close();
        stmt.close();
    }

    private void showDoctors() {
        try {
            getDoctorFromBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList(DoctorList); // создаем ObservableList из ArrayList

        idDoctor.setCellValueFactory(new PropertyValueFactory<>("id")); // привязываем значения столбцов к свойствам Doctor
        famDoctor.setCellValueFactory(new PropertyValueFactory<>("Fam"));
        imDoctor.setCellValueFactory(new PropertyValueFactory<>("Imya"));
        otcDoctor.setCellValueFactory(new PropertyValueFactory<>("Otch"));
        DegreeDoctor.setCellValueFactory(new PropertyValueFactory<>("IdSpec"));
        RoomDoctor.setCellValueFactory(new PropertyValueFactory<>("Room"));

        DoctorTableView.setItems(doctorObservableList);
    }

    private void showPatient(){
        try {
            getPatientFromBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(PatientList);
        idPatient.setCellValueFactory(new PropertyValueFactory<>("id"));
        famPatient.setCellValueFactory(new PropertyValueFactory<>("Fam"));
        imPatient.setCellValueFactory(new PropertyValueFactory<>("Imya"));
        otcPatient.setCellValueFactory(new PropertyValueFactory<>("Otch"));
        sexPatient.setCellValueFactory(new PropertyValueFactory<>("sex"));
        datePatient.setCellValueFactory(new PropertyValueFactory<>("date"));
        SNILS.setCellValueFactory(new PropertyValueFactory<>("snils"));

        PatientTableView.setItems(patientObservableList);
    }

    private void onSearchFieldChanged(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // фильтруем данные в таблице по новому значению в поле поиска
        FilteredList<Doctor> filteredList = new FilteredList<>(DoctorList, doctor ->
                doctor.getIdSpec().toLowerCase().contains(newValue.toLowerCase())
                        || doctor.getRoom().toLowerCase().contains(newValue.toLowerCase())
                        || doctor.getId().toLowerCase().contains(newValue.toLowerCase())
                        || doctor.getFam().toLowerCase().contains(newValue.toLowerCase())
                        || doctor.getImya().toLowerCase().contains(newValue.toLowerCase())
                        || doctor.getOtch().toLowerCase().contains(newValue.toLowerCase()));

        // обновляем данные в таблице
        DoctorTableView.setItems(filteredList);
    }

    private void searchPatient() {
        // Получаем значения из текстовых полей
        String im = ImTextField.getText().toLowerCase();
        String fam = FamTextField.getText().toLowerCase();
        String otc = OtchTextField.getText().toLowerCase();
        String date = DateTextField.getText();
        String snils = SNILSTextField.getText().toLowerCase();

        // Создаем фильтр для списка пациентов
        Predicate<Patient> predicate = patient -> {
            if (!fam.isEmpty() && !patient.getFam().toLowerCase().contains(fam)) {
                return false;
            }
            if (!im.isEmpty() && !patient.getImya().toLowerCase().contains(im)) {
                return false;
            }
            if (!otc.isEmpty() && !patient.getOtch().toLowerCase().contains(otc)) {
                return false;
            }
            if (!date.isEmpty() && !patient.getDate().equals(date)) {
                return false;
            }
            if (!snils.isEmpty() && !patient.getSnils().toLowerCase().contains(snils)) {
                return false;
            }
            return true;
        };

        // Создаем отфильтрованный список пациентов
        FilteredList<Patient> filteredList = new FilteredList<>(PatientList, predicate);

        // Обновляем содержимое TableView
        PatientTableView.setItems(filteredList);
    }

}

