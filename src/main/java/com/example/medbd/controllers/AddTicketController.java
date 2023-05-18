package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.Doctor;
import com.example.medbd.models.Patient;
import com.example.medbd.models.TimeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddTicketController {

    @FXML
    private AnchorPane AddTicket;

    @FXML
    private Button AddTicketButton;

    @FXML
    private Button ClearButton;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private Button ExitButton;

    @FXML
    private TableColumn<String, String> InfoColumn;

    @FXML
    private Button SearchButton;

    @FXML
    private TableView<TimeTable> TicketView;

    @FXML
    private TableColumn<String, String> TimeColumn;

    @FXML
    private TextField TimeTicket;

    @FXML
    private TextField TypeTicket;

    @FXML
    void addTicket(ActionEvent event) {

    }

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        Stage st = (Stage) AddTicket.getScene().getWindow();
        st.close();
    }

    ObservableList<TimeTable> timeTables = FXCollections.observableArrayList();

    @FXML
    void searchTicket(ActionEvent event) throws SQLException {

        LocalDate date = DatePicker.getValue();
        if (date == null)
            return;
        Connection connection = BdTools.getConnection();
        timeTables.clear();
        String query = "SELECT mc.fam, mc.imya, mc.otch, ti.type_ticket, " +
                "TO_CHAR(date_appointment, 'HH24:MI') " +
                "FROM ticket ti JOIN medcard mc ON ti.id_medcard = mc.id_medcard " +
                "WHERE DATE_TRUNC('day', date_appointment) = ? AND id_doctor = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setDate(1, java.sql.Date.valueOf(date));
        pstmt.setInt(2, Integer.parseInt(doctor.getId()));
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String fam = rs.getString(1);
            String im = rs.getString(2);
            String otch = rs.getString(3);
            String time = rs.getString(5);
            String type = rs.getString(4);
            timeTables.add(new TimeTable(fam, im, otch, type, time));
        }
        String selmax = "SELECT MAX(TO_CHAR(date_appointment, 'HH24:MI')) FROM ticket " +
                "WHERE DATE_TRUNC('day', date_appointment) = ? AND id_doctor = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selmax);
        preparedStatement.setDate(1, java.sql.Date.valueOf(date));
        preparedStatement.setInt(2, Integer.parseInt(doctor.getId()));
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        maxTime = resultSet.getString(1);
        if (maxTime == null)
            maxTime = "08:00:00";
        System.out.println(maxTime);
        ObservableList<TimeTable> timeTableObservableList = FXCollections.observableArrayList(timeTables);

        TimeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        InfoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));

        TicketView.setItems(timeTableObservableList);


    }

    Doctor doctor;
    Patient patient;

    double yOffset;
    double xOffset;

    String maxTime;

    String id_reg;


    @FXML
    public void initialize() {
        AddTicket.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        AddTicket.setOnMouseDragged(event -> {
            Stage stage = (Stage) AddTicket.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }


    @FXML
    public void initData(Patient pat, Doctor doc, String id) {
        this.doctor = doc;
        this.patient = pat;
        this.id_reg = id;
    }
}
