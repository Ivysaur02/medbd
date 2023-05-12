package com.example.medbd.controllers;

import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.models.MedHistory;
import com.example.medbd.models.Patient;
import com.example.medbd.models.TypeHistoryCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

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

    @FXML
    private ComboBox<TypeHistoryCard> AddNewComboBox;

    @FXML
    private ComboBox<TypeHistoryCard> SearchComboBox;

    @FXML
    private TextField SeartTextField;


    @FXML
    private Button CleareButton;

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
        showMedHistory();
    }



    @FXML
    void clearSearch(ActionEvent event) {
        SearchComboBox.getSelectionModel().clearSelection();
        SeartTextField.setText("");
        showMedHistory();
    }

    ObservableList<MedHistory> MedHistList = FXCollections.observableArrayList();
    ObservableList<TypeHistoryCard> TypeHistList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        showTypeHistory();
    }


    public void getPatientInfo(Patient pat) {
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


    private void getMedHistoryFromBD() throws SQLException {
        MedHistList.clear();
        Connection conn = BdTools.getConnection();
        Statement stmt = conn.createStatement();

        String query = "SELECT hm.id_hiscard, thc.name_type, hm.gen_inf, hm.date " +
                "FROM history_medcard hm " +
                "INNER JOIN type_of_card thc ON thc.id_type=hm.type " +
                "INNER JOIN medmap md ON hm.id_hiscard = md.id_medcard " +
                "WHERE md.id_medcard = " + patient.getId();

        String date = SeartTextField.getText();

        if (SearchComboBox.getSelectionModel().getSelectedItem() != null){
            String type = SearchComboBox.getSelectionModel().getSelectedItem().getName();
            query += " AND thc.name_type = '" + type + "'";
        }
        if (!date.isEmpty()) {
            query += " AND hm.date = '" + date + "'";
        }

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String inf = rs.getString(3);
            String date_hist = rs.getString(4);
            MedHistList.add(new MedHistory(id, name, date_hist, inf));
        }
    }

    private void showMedHistory(){
         try {
            getMedHistoryFromBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<MedHistory> medHistoryObservableList = FXCollections.observableArrayList(MedHistList);
        idHistory.setCellValueFactory(new PropertyValueFactory<>("id_hist"));
        TypeHistory.setCellValueFactory(new PropertyValueFactory<>("type"));
        DateHistory.setCellValueFactory(new PropertyValueFactory<>("date"));

        HistoryTableView.setItems(medHistoryObservableList);
    }


    private void getTypeHistoryCard() {
        TypeHistList.clear();
        try {
            Connection conn = BdTools.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM type_of_card");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                TypeHistoryCard typeOfCard = new TypeHistoryCard(id, name);
                TypeHistList.add(typeOfCard);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showTypeHistory() {
        getTypeHistoryCard();
        AddNewComboBox.setItems(TypeHistList);
        SearchComboBox.setItems(TypeHistList);
    }



}

