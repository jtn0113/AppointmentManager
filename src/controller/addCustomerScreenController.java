package controller;

import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addCustomerScreenController {

    ShowScene scene = new ShowScene();

    @FXML
    private TextField addCustomerAddressTxt;

    @FXML
    private ComboBox<?> addCustomerCountryCombo;

    @FXML
    private ComboBox<?> addCustomerDivisionCombo;

    @FXML
    private TextField addCustomerIdText;

    @FXML
    private TextField addCustomerNameTxt;

    @FXML
    private TextField addCustomerPhoneTxt;

    @FXML
    private TextField addCustomerPostalTxt;

    @FXML
    void onActionSaveCustomer(ActionEvent event) {

    }

    @FXML
    void onActionToCustomersScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/customersScreen.fxml");
    }

}