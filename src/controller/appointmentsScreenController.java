package controller;

import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class appointmentsScreenController {

    ShowScene scene = new ShowScene();

    @FXML
    private RadioButton AllAppointmentsRB;

    @FXML
    private TableView<?> AppointmentTableView;

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

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/addAppointmentScreen.fxml");
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionShowAllAppointments(ActionEvent event) {

    }

    @FXML
    void onActionShowCustomers(ActionEvent event) throws IOException{
        scene.showScene(event, "/view/customersScreen.fxml");
    }

    @FXML
    void onActionShowMonthAppointments(ActionEvent event) {

    }

    @FXML
    void onActionShowWeekAppointments(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event)throws IOException {
        scene.showScene(event, "/view/updateAppointmentScreen.fxml");
    }

}