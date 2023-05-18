package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.Doctor;
import com.example.medbd.models.TimeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DoctInfController {

    @FXML
    private TableColumn<String, String> AnyInfoField;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private AnchorPane DoctorInfoPanel;

    @FXML
    private Button ExitButton;

    @FXML
    private TableColumn<String, String> FamPat;

    @FXML
    private TableColumn<String, String> ImPat;

    @FXML
    private TableColumn<String, String> OtchPat;

    @FXML
    private Button PrintButton;

    @FXML
    private Button SearchButton;

    @FXML
    private TableView<TimeTable> TicketTableView;

    @FXML
    private TableColumn<String, String> TimeTicket;

    @FXML
    void exit(ActionEvent event) {
        Stage st = (Stage) DoctorInfoPanel.getScene().getWindow();
        st.close();
    }

    @FXML
    void print(ActionEvent event) {

    }



    @FXML
    void search(ActionEvent event) throws SQLException {

        LocalDate date = DatePicker.getValue();
        if (date == null)
            return;
        Connection connection = BdTools.getConnection();
        timeTables.clear();
        String query = "SELECT mc.fam, mc.imya, mc.otch, ti.type_ticket, " +
                "TO_CHAR(date_appointment, 'HH24:MI') "+
                "FROM ticket ti JOIN medcard mc ON ti.id_medcard = mc.id_medcard " +
                "WHERE DATE_TRUNC('day', date_appointment) = ? AND id_doctor = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setDate(1, java.sql.Date.valueOf(date));
        pstmt.setInt(2, Integer.parseInt(doctor.getId()));
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            String fam = rs.getString(1);
            String im = rs.getString(2);
            String otch = rs.getString(3);
            String time = rs.getString(5);
            String type = rs.getString(4);
            timeTables.add(new TimeTable(fam,im,otch,type,time));
        }

        ObservableList<TimeTable> timeTableObservableList = FXCollections.observableArrayList(timeTables);

        ImPat.setCellValueFactory(new PropertyValueFactory<>("im"));
        FamPat.setCellValueFactory(new PropertyValueFactory<>("fam"));
        OtchPat.setCellValueFactory(new PropertyValueFactory<>("otch"));
        AnyInfoField.setCellValueFactory(new PropertyValueFactory<>("info"));
        TimeTicket.setCellValueFactory(new PropertyValueFactory<>("date"));

        TicketTableView.setItems(timeTableObservableList);

    }


    ObservableList<TimeTable> timeTables = FXCollections.observableArrayList();

    double yOffset;
    double xOffset;

    @FXML
    public void initialize() {
        DoctorInfoPanel.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        DoctorInfoPanel.setOnMouseDragged(event -> {
            Stage stage = (Stage) DoctorInfoPanel.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    Doctor doctor;

    public void setInfo(Doctor doctor){
        this.doctor = doctor;
    }

}
