package controller;

import DAO.firstLevelDivisionsDAO;
import helper.CountryConversions;
import helper.JDBC;
import helper.ShowScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class addCustomerScreenController implements Initializable {

    ShowScene scene = new ShowScene();

    @FXML
    private TextField addCustomerAddressTxt;

    @FXML
    private ComboBox<String> addCustomerCountryCombo;

    @FXML
    private ComboBox<String> addCustomerDivisionCombo;

    @FXML
    private TextField addCustomerIdText;

    @FXML
    private TextField addCustomerNameTxt;

    @FXML
    private TextField addCustomerPhoneTxt;

    @FXML
    private TextField addCustomerPostalTxt;

    @FXML
    void onActionSaveCustomer(ActionEvent event) throws SQLException, IOException {
        String name = addCustomerNameTxt.getText();
        String address = addCustomerAddressTxt.getText();
        String postalCode = addCustomerPostalTxt.getText();
        String phone = addCustomerPhoneTxt.getText();
        int divisionId = CountryConversions.divisionNameToId(addCustomerDivisionCombo.getSelectionModel().getSelectedItem());

        String query = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        ps.setString(6, "script");
        ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
        ps.setString(8, "script");
        ps.setInt(9, divisionId);
        ps.execute();

        scene.showScene(event, "/view/customersScreen.fxml");

    }

    @FXML
    void onActionToCustomersScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/customersScreen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<String> countriesOL = FXCollections.observableArrayList("US", "UK", "Canada");
            ObservableList<String> usOL = firstLevelDivisionsDAO.getUsDivisions();
            ObservableList<String> ukOL = firstLevelDivisionsDAO.getUkDivisions();
            ObservableList<String> canadaOL = firstLevelDivisionsDAO.getCanadaDivisions();
            addCustomerCountryCombo.setItems(countriesOL);

            addCustomerCountryCombo.setOnAction(e -> {
                if (addCustomerCountryCombo.getValue() == "US") {
                    addCustomerDivisionCombo.setItems(usOL);
                } else if (addCustomerCountryCombo.getValue() == "UK") {
                    addCustomerDivisionCombo.setItems(ukOL);
                } else if (addCustomerCountryCombo.getValue() == "Canada") {
                    addCustomerDivisionCombo.setItems(canadaOL);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}