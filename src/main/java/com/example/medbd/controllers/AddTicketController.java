package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.PDFCreate.PdfGenerator;
import com.example.medbd.models.Doctor;
import com.example.medbd.models.Patient;
import com.example.medbd.models.Ticket;
import com.example.medbd.models.TimeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private Label WarningLabel;

    @FXML
    void addTicket(ActionEvent event) throws SQLException, IOException {
        LocalDate date_picker = DatePicker.getValue();
        if (date_picker == null)
            return;
        if (TimeTicket.getText().isEmpty())
            return;
        String date = TimeTicket.getText();
        date = date.trim();
        date += ":00";
        LocalTime time = LocalTime.parse(date);
        if (time.isBefore(LocalTime.of(8, 0)) || time.isAfter(LocalTime.of(18, 0))) {
            WarningLabel.setText("Запись на неприёмное время");
            return;
        }
        String date_with_time = date_picker + " " + date;
        String info = TypeTicket.getText();
        Connection connection = BdTools.getConnection();
        String query = "SELECT * FROM ticket WHERE date_appointment = ? AND id_doctor = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setTimestamp(1, Timestamp.valueOf(date_with_time));
        pstmt.setInt(2, Integer.parseInt(doctor.getId()));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()){
            WarningLabel.setText("");
            WarningLabel.setText("Уже есть запись на данное время");
            return;
        }
        LocalDate currentDate = LocalDate.now();
        if (date_picker.isBefore(currentDate)) {
            WarningLabel.setText("");
            WarningLabel.setText("Невозможно записать на прошедшее число");
            return;
        }
        String insert = "INSERT INTO ticket " +
                "(id_medcard, id_doctor, id_user, date_receipt, date_appointment, status, type_ticket)" +
                " VALUES (?, ?, ?, CURRENT_TIMESTAMP(0), ?, ?, ?)";
        pstmt = connection.prepareStatement(insert);
        pstmt.setInt(1, Integer.parseInt(patient.getId()));
        pstmt.setInt(2, Integer.parseInt(doctor.getId()));
        pstmt.setInt(3, Integer.parseInt(id_reg));
        pstmt.setTimestamp(4, Timestamp.valueOf(date_with_time));
        pstmt.setInt(5,1);
        pstmt.setString(6, info);

        pstmt.executeUpdate();

        String selmax = "select MAX(id_ticket) from ticket_view";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(selmax);
        resultSet.next();
        int id_ticket = resultSet.getInt(1);

        String getTicketinfo = "SELECT * FROM ticket_view WHERE id_ticket = ?";
        pstmt = connection.prepareStatement(getTicketinfo);
        pstmt.setInt(1, id_ticket);
        ResultSet ticketres = pstmt.executeQuery();
        if (ticketres.next()) {
            Ticket ticket = new Ticket();
            ticket.setDate_appointment(ticketres.getString(6));
            ticket.setDate_receipt(ticketres.getString(5));
            ticket.setId_ticket(ticketres.getString(1));
            ticket.setPat_otch(ticketres.getString(4));
            ticket.setDc_fam(ticketres.getString(7));
            ticket.setDc_im(ticketres.getString(8));
            ticket.setDc_otch(ticketres.getString(9));
            ticket.setName_of_specialty(ticketres.getString(10));
            ticket.setRoom(ticketres.getString(11));
            ticket.setUs_fam(ticketres.getString(12));
            ticket.setUs_im(ticketres.getString(13));
            ticket.setUs_otch(ticketres.getString(14));
            ticket.setPat_fam(ticketres.getString(2));
            ticket.setPat_im(ticketres.getString(3));
            PdfGenerator pdfGenerator = new PdfGenerator();
            pdfGenerator.generatePdf(ticket,"ticket.pdf");
            //TODo
        }
    }

    @FXML
    void clear(ActionEvent event) {
        TimeTicket.clear();
        TypeTicket.clear();

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

        ObservableList<TimeTable> timeTableObservableList = FXCollections.observableArrayList(timeTables);

        TimeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        InfoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));

        TicketView.setItems(timeTableObservableList);


    }

    Doctor doctor;
    Patient patient;

    double yOffset;
    double xOffset;


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
