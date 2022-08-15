package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
import helper.Alerts;
import helper.JDBC;
import helper.ShowScene;
import helper.TimeConversions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for Appointments Screen
 */
public class appointmentsScreenController implements Initializable {

    ShowScene scene = new ShowScene();
    Stage stage;

    @FXML
    private RadioButton AllAppointmentsRB;

    @FXML
    private TableView<Appointments> AppointmentTableView;

    @FXML
    private ToggleGroup Appoints;

    @FXML
    private RadioButton MonthAppointmentsRB;

    @FXML
    private RadioButton WeekAppointmentsRB;

    @FXML
    private TableColumn<?, ?> appointmentContactCol;

    @FXML
    private TableColumn<?, ?> appointmentCustomerIdCol;

    @FXML
    private TableColumn<?, ?> appointmentDescriptionCol;

    @FXML
    private TableColumn<?, ?> appointmentEndCol;

    @FXML
    private TableColumn<?, ?> appointmentIdTableCol;

    @FXML
    private TableColumn<?, ?> appointmentLocationCol;

    @FXML
    private TableColumn<?, ?> appointmentStartCol;

    @FXML
    private TableColumn<?, ?> appointmentTitleCol;

    @FXML
    private TableColumn<?, ?> appointmentTypeCol;

    @FXML
    private TableColumn<?, ?> appointmentUserIdCol;

    /**
     * Adds appointment when button is clicked
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/addAppointmentScreen.fxml");
    }

    /**
     * Deletes appointment when button is clicked
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws SQLException, IOException{
        try {
            int appointmentId = AppointmentTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            String appointmentType = AppointmentTableView.getSelectionModel().getSelectedItem().getAppointmentType();

            String query = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentId;
            PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
            ps.execute();
            Alerts.informationAlert("Appointment Cancelled \nAppointment ID: " + appointmentId + "\nAppointment Type: " + appointmentType);
            scene.showScene(event, "/view/appointmentsScreen.fxml");
        } catch (NullPointerException e) {
            Alerts.errorAlert("Select an Appointment");
        }
    }

    /**
     * Exits program
     * @param event
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Displays all appointments
     * @param event
     */
    @FXML
    void onActionShowAllAppointments(ActionEvent event) {
        try {
            ObservableList<Appointments> appointmentsObservableList = appointmentsDAO.getAllAppointments();
            AppointmentTableView.setItems(appointmentsObservableList);
            appointmentIdTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
            appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            appointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows customers screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionShowCustomers(ActionEvent event) throws IOException{
        scene.showScene(event, "/view/customersScreen.fxml");
    }

    /**
     * Displays appointments for current month
     * @param event
     */
    @FXML
    void onActionShowMonthAppointments(ActionEvent event) {
        try {
            ObservableList<Appointments> appointmentsObservableList = appointmentsDAO.getMonthAppointments();
            AppointmentTableView.setItems(appointmentsObservableList);
            appointmentIdTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
            appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            appointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays appointments for current week
     * @param event
     */
    @FXML
    void onActionShowWeekAppointments(ActionEvent event) {
        try {
            ObservableList<Appointments> appointmentsObservableList = appointmentsDAO.getWeekAppointments();
            AppointmentTableView.setItems(appointmentsObservableList);
            appointmentIdTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
            appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            appointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows update appointment screen. Sends appointment data
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent event)throws IOException, SQLException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateAppointmentScreen.fxml"));
            loader.load();

            updateAppointmentScreenController UAController = loader.getController();
            UAController.sendAppointment(AppointmentTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alerts.errorAlert("Select an Appointment");
        }
    }

    /**
     * Displays appointments report table
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionShowAppointmentsReport(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentByTypeMonthScreen.fxml");
    }

    /**
     * Displays contact schedule table
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionShowContactSchedule(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/contactSchedule.fxml");
    }

    /**
     * Displays customer schedule table
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionShowCustomReport(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/customerSchedule.fxml");
    }

    /**
     * Initialize method
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Appointments> appointmentsObservableList = appointmentsDAO.getAllAppointments();
            AppointmentTableView.setItems(appointmentsObservableList);
            appointmentIdTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
            appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            appointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}