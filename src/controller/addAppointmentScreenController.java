package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
import DAO.customerDAO;
import DAO.userDAO;
import helper.Alerts;
import helper.JDBC;
import helper.ShowScene;
import helper.TimeConversions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class addAppointmentScreenController implements Initializable {

    ShowScene scene = new ShowScene();
    LocalTime businessOpen = LocalTime.of(7,59,59);
    LocalTime businessClose = LocalTime.of(22,00,01);

    @FXML
    private ComboBox<String> addAppointmentContactCombo;

    @FXML
    private ComboBox<Integer> addAppointmentCustomerCombo;

    @FXML
    private ComboBox<Integer> addAppointmentUserCombo;

    @FXML
    private TextField addAppointmentDescriptionText;

    @FXML
    private TextField addAppointmentEndTimeText;

    @FXML
    private TextField addAppointmentIdText;

    @FXML
    private TextField addAppointmentLocationText;

    @FXML
    private DatePicker addAppointmentStartDate;

    @FXML
    private TextField addAppointmentStartTimeText;

    @FXML
    private TextField addAppointmentTitleText;

    @FXML
    private TextField addAppointmentTypeText;

    @FXML
    void onActionSaveAppointment(ActionEvent event) throws SQLException, IOException {
        try {
            String title = addAppointmentTitleText.getText();
            String description = addAppointmentDescriptionText.getText();
            String location = addAppointmentLocationText.getText();
            int contactID = contactsDAO.contactNameToId(addAppointmentContactCombo.getSelectionModel().getSelectedItem());
            String type = addAppointmentTypeText.getText();
            LocalDateTime start = TimeConversions.localToUtc(TimeConversions.dateTime(addAppointmentStartDate.getValue(), addAppointmentStartTimeText.getText()));
            LocalDateTime end = TimeConversions.localToUtc(TimeConversions.dateTime(addAppointmentStartDate.getValue(), addAppointmentEndTimeText.getText()));
            int customerID = addAppointmentCustomerCombo.getSelectionModel().getSelectedItem();
            int userID = addAppointmentUserCombo.getSelectionModel().getSelectedItem();

            if (TimeConversions.utcToEst(start).toLocalTime().isAfter(businessOpen) && TimeConversions.utcToEst(end).toLocalTime().isBefore(businessClose) && TimeConversions.utcToEst(end).toLocalTime().isAfter(businessOpen) && TimeConversions.utcToEst(start).isBefore(TimeConversions.utcToEst(end))) {
                if(title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()) {
                    Alerts.errorAlert("Enter Valid Data");
                } else {
                    if(appointmentsDAO.customerOverlappingAppointments(customerID, start, end)) {
                        Alerts.errorAlert("Customer has overlapping appointment");
                    } else {
                        String query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)"
                                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
                        ps.setString(1, title);
                        ps.setString(2, description);
                        ps.setString(3, location);
                        ps.setString(4, type);
                        ps.setTimestamp(5, Timestamp.valueOf(start));
                        ps.setTimestamp(6, Timestamp.valueOf(end));
                        ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                        ps.setString(8, "script");
                        ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
                        ps.setString(10, "script");
                        ps.setInt(11, customerID);
                        ps.setInt(12, userID);
                        ps.setInt(13, contactID);
                        ps.execute();

                        scene.showScene(event, "/view/appointmentsScreen.fxml");
                    }
                }
            } else {
                Alerts.errorAlert("Appointment is outside business hours");
            }
        } catch (DateTimeParseException e) {
            Alerts.errorAlert("Enter Valid Data");
        } catch (NullPointerException e) {
            Alerts.errorAlert("Enter Valid Data");
        } catch (SQLException e) {
            Alerts.errorAlert("Enter Valid Data");
        }
    }

    @FXML
    void onActionToAppointmentsScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addAppointmentContactCombo.setItems(contactsDAO.getAllContactNames());
            addAppointmentUserCombo.setItems(userDAO.getAllUserId());
            addAppointmentCustomerCombo.setItems(customerDAO.getAllCustomerId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}