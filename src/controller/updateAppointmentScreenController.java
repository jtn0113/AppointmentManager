package controller;

import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Customers;

import java.io.IOException;

public class updateAppointmentScreenController {

    ShowScene scene = new ShowScene();

    @FXML
    private ComboBox<?> updateAppointmentContactCombo;

    @FXML
    private TextField updateAppointmentCustomerIdText;

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
    private TextField updateAppointmentUserIdText;

    @FXML
    void onActionToAppointmentsScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }

}