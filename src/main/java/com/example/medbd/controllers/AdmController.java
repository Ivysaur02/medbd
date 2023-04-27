package com.example.medbd.controllers;


import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.bdApplic;
import com.example.medbd.models.Doctor;
import com.example.medbd.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdmController {


    @FXML
    private Button AddBut;

    @FXML
    private Button AddUserButton;

    @FXML
    private Button DelBut;

    @FXML
    private Button DelUserButton;


    @FXML
    private Button RefrehUserButton;

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
    private Tab UserTab;

    @FXML
    private TableView<User> UserTable;

    @FXML
    private TableColumn<User, String> idUser;

    @FXML
    private TableColumn<User, String> ImyaUser;

    @FXML
    private TableColumn<User, String> FamUser;


    @FXML
    private TableColumn<User, String> OtchUser;

    @FXML
    private TableColumn<User, String> LoginUser;

    @FXML
    private TableColumn<User, String> PasswordUser;

    @FXML
    private TableColumn<User, String> TitleUser;

    @FXML
    private Button Refresh;

    @FXML
    private Label adminLabel;


    @FXML
    private Button exitButton;


    @FXML
    void onLogInPanel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(bdApplic.class.getResource("Login.fxml"));
        Stage closestage = (Stage) exitButton.getScene().getWindow();
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(new Scene(root));
        newStage.show();
        closestage.close();

    }

    @FXML
    void DelDoc(ActionEvent event) throws SQLException {
        //todo написать алерт на врача
        Doctor selectedDoctor = DoctTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            BdTools.deleteUser(selectedDoctor, "doctor");
        }
        showDoctors();
    }

    @FXML
    void DelUser(ActionEvent event) throws SQLException {
        //todo написать аллерт
        User selectedDoctor = UserTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            BdTools.deleteUser(selectedDoctor, "receptionist");
        }
        showUser();
    }

    @FXML
    void UpdDoc(ActionEvent event) throws IOException {
        // Получаем выбранного пользователя из таблицы
        Doctor selectedPerson = DoctTable.getSelectionModel().getSelectedItem();

        // Проверяем, что пользователь был выбран
        if (selectedPerson == null) {
            return;
        }

        // Создаем новое окно для обновления данных
        FXMLLoader loader = new FXMLLoader(bdApplic.class.getResource("UpdDoctPanel.fxml"));
        Parent root = loader.load();
        UpdDoctController updateController = loader.getController();

        // Вызываем метод initData() на контроллере нового окна и передаем в него выбранного пользователя
        updateController.initData(selectedPerson);

        // Отображаем новое окно
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void addDoc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(bdApplic.class.getResource("addMedPanel.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void addUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(bdApplic.class.getResource("addUserPanel.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void RefreshData(ActionEvent event) {
        showDoctors();
    }

    @FXML
    void RefreshUser(ActionEvent event) {
        showUser();
    }

    ObservableList<Doctor> DoctorList = FXCollections.observableArrayList();
    ObservableList<User> UserList = FXCollections.observableArrayList();

    String id_adm = null;

    @FXML
    public void initialize() {
        showDoctors();
        showUser();

    }


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

    private void getUserFromBD() throws SQLException {
        UserList.clear();
        Connection connection = BdTools.getConnection();
        String sel = "SELECT r.id, r.fam, r.imya, r.otch, title_job.name_of_title, r.login, r.password" +
                " FROM receptionist r" +
                " INNER JOIN title_job ON r.title = title_job.id_title ";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(sel);
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String surname = resultSet.getString(2);
            String name = resultSet.getString(3);
            String otch = resultSet.getString(4);
            String title = resultSet.getString(5);
            String login = resultSet.getString(6);
            String password = resultSet.getString(7);
            User user = new User(id, surname, name, otch, login, password, title);
            UserList.add(user);
        }
        resultSet.close();
        st.close();
    }

    private void showUser() {

        try {
            getUserFromBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<User> userObservableList = FXCollections.observableArrayList(UserList);

        idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        FamUser.setCellValueFactory(new PropertyValueFactory<>("Fam"));
        ImyaUser.setCellValueFactory(new PropertyValueFactory<>("Imya"));
        OtchUser.setCellValueFactory(new PropertyValueFactory<>("Otch"));
        TitleUser.setCellValueFactory(new PropertyValueFactory<>("title"));
        LoginUser.setCellValueFactory(new PropertyValueFactory<>("login"));
        PasswordUser.setCellValueFactory(new PropertyValueFactory<>("password"));

        UserTable.setItems(userObservableList);
    }

    public void initData(String title, String id) {
        adminLabel.setText(title);
        id_adm = id;
    }

}
