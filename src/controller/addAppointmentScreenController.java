package controller;

import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addAppointmentScreenController {

    ShowScene scene = new ShowScene();

    @FXML
    private ComboBox<?> addAppointmentContactCombo;

    @FXML
    private TextField addAppointmentCustomerIdText;

    @FXML
    private TextField addAppointmentDescriptionText;

    @FXML
    private DatePicker addAppointmentEndDate;

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
    private TextField addAppointmentUserIdText;

    @FXML
    void onActionSaveAppointment(ActionEvent event) {

    }

    @FXML
    void onActionToAppointmentsScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

}