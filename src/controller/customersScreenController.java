package controller;

import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class customersScreenController {

    ShowScene scene = new ShowScene();

    @FXML
    private TableView<?> AppointmentTableView;

    @FXML
    private TableColumn<?, ?> customerAddressTableCol;

    @FXML
    private TableColumn<?, ?> customerDivisionIdTableCol;

    @FXML
    private TableColumn<?, ?> customerIdTableCol;

    @FXML
    private TableColumn<?, ?> customerNameTableCol;

    @FXML
    private TableColumn<?, ?> customerPhoneTableCol;

    @FXML
    private TableColumn<?, ?> customerPostalTableCol;

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/addCustomerScreen.fxml");
    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionShowAppointments(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/updateCustomerScreen.fxml");
    }

}