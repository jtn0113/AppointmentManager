package controller;

import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class updateCustomerScreenController {

    ShowScene scene = new ShowScene();

    @FXML
    private TextField updateCustomerAddressTxt;

    @FXML
    private ComboBox<?> updateCustomerCountryCombo;

    @FXML
    private ComboBox<?> updateCustomerDivisionCombo;

    @FXML
    private TextField updateCustomerIdText;

    @FXML
    private TextField updateCustomerNameTxt;

    @FXML
    private TextField updateCustomerPhoneTxt;

    @FXML
    private TextField updateCustomerPostalTxt;

    @FXML
    void onActionToCustomersScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/customersScreen.fxml");
    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }

}