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
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/**
 * Controller for update appointment screen
 */
public class updateAppointmentScreenController implements Initializable {

    ShowScene scene = new ShowScene();
    LocalTime businessOpen = LocalTime.of(7,59,59);
    LocalTime businessClose = LocalTime.of(22,00,01);

    @FXML
    private ComboBox<String> updateAppointmentContactCombo;

    @FXML
    private ComboBox<Integer> updateAppointmentCustomerCombo;

    @FXML
    private TextField updateAppointmentDescriptionText;

    @FXML
    private DatePicker updateAppointmentEndDate;

    @FXML
    private TextField updateAppointmentEndTimeText;

    @FXML
    private TextField updateAppointmentIdText;

    @FXML
    private TextField updateAppointmentLocationText;

    @FXML
    private DatePicker updateAppointmentStartDate;

    @FXML
    private TextField updateAppointmentStartTimeText;

    @FXML
    private TextField updateAppointmentTitleText;

    @FXML
    private TextField updateAppointmentTypeText;

    @FXML
    private ComboBox<Integer> updateAppointmentUserCombo;

    /**
     * Returns to appointments screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionToAppointmentsScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    /**
     * Updates appointment
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws SQLException, IOException{
        try {
            String appointmentID = updateAppointmentIdText.getText();
            String title = updateAppointmentTitleText.getText();
            String description = updateAppointmentDescriptionText.getText();
            String location = updateAppointmentLocationText.getText();
            int contactID = contactsDAO.contactNameToId(updateAppointmentContactCombo.getSelectionModel().getSelectedItem());
            String type = updateAppointmentTypeText.getText();
            LocalDateTime start = TimeConversions.localToUtc(TimeConversions.dateTime(updateAppointmentStartDate.getValue(), updateAppointmentStartTimeText.getText()));
            LocalDateTime end = TimeConversions.localToUtc(TimeConversions.dateTime(updateAppointmentStartDate.getValue(), updateAppointmentEndTimeText.getText()));
            int customerID = updateAppointmentCustomerCombo.getSelectionModel().getSelectedItem();
            int userID = updateAppointmentUserCombo.getSelectionModel().getSelectedItem();

            if (TimeConversions.utcToEst(start).toLocalTime().isAfter(businessOpen) && TimeConversions.utcToEst(end).toLocalTime().isBefore(businessClose) && TimeConversions.utcToEst(end).toLocalTime().isAfter(businessOpen) && TimeConversions.utcToEst(start).isBefore(TimeConversions.utcToEst(end))) {
                if(title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()) {
                    Alerts.errorAlert("Enter Valid Data");
                } else {
                    if(appointmentsDAO.customerOverlappingAppointmentsUpdate(customerID, appointmentID, start, end)) {
                        Alerts.errorAlert("Customer has overlapping appointment");
                    } else {
                        String query = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = " + appointmentID;
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
            Alerts.errorAlert("Enter Valid Dataaaa");
        } catch (NullPointerException e) {
            Alerts.errorAlert("Enter Valid Databbb");
        }
    }

    /**
     * Receives appointment data from appointments screen
     * @param appointment
     */
    public void sendAppointment(Appointments appointment) {
        String dateTimeStringStart = String.valueOf(appointment.getAppointmentStart());
        String dateTimeStringEnd = String.valueOf(appointment.getAppointmentEnd());
        String timeSubstringStart = dateTimeStringStart.substring(11);
        String timeSubstringEnd = dateTimeStringEnd.substring(11);
        LocalDate dateSubstring = LocalDate.parse(dateTimeStringStart.substring(0, 10));

        updateAppointmentIdText.setText(String.valueOf(appointment.getAppointmentID()));
        updateAppointmentTitleText.setText(String.valueOf(appointment.getAppointmentTitle()));
        updateAppointmentDescriptionText.setText(String.valueOf(appointment.getAppointmentDescription()));
        updateAppointmentLocationText.setText(String.valueOf(appointment.getAppointmentLocation()));
        updateAppointmentTypeText.setText(String.valueOf(appointment.getAppointmentType()));
        updateAppointmentStartTimeText.setText(timeSubstringStart);
        updateAppointmentEndTimeText.setText(timeSubstringEnd);
        updateAppointmentContactCombo.setValue(appointment.getContactName());
        updateAppointmentCustomerCombo.setValue(appointment.getCustomerID());
        updateAppointmentUserCombo.setValue(appointment.getUserID());
        updateAppointmentStartDate.setValue(dateSubstring);
    }

    /**
     * Initialize method
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            updateAppointmentContactCombo.setItems(contactsDAO.getAllContactNames());
            updateAppointmentCustomerCombo.setItems(customerDAO.getAllCustomerId());
            updateAppointmentUserCombo.setItems(userDAO.getAllUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}