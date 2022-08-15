package controller;

import DAO.firstLevelDivisionsDAO;
import helper.Alerts;
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
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/**
 * Controller for Update Customer screen
 */
public class updateCustomerScreenController implements Initializable {

    ShowScene scene = new ShowScene();

    @FXML
    private TextField updateCustomerAddressTxt;

    @FXML
    private ComboBox<String> updateCustomerCountryCombo;

    @FXML
    private ComboBox<String> updateCustomerDivisionCombo;

    @FXML
    private TextField updateCustomerIdText;

    @FXML
    private TextField updateCustomerNameTxt;

    @FXML
    private TextField updateCustomerPhoneTxt;

    @FXML
    private TextField updateCustomerPostalTxt;

    /**
     * Returns to Customers Screen when cancel is clicked
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionToCustomersScreen(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/customersScreen.fxml");
    }

    /**
     * Saves updated customer data
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws SQLException, IOException{
        try {
            String name = updateCustomerNameTxt.getText();
            String address = updateCustomerAddressTxt.getText();
            String postalCode = updateCustomerPostalTxt.getText();
            String phone = updateCustomerPhoneTxt.getText();
            int divisionId = CountryConversions.divisionNameToId(updateCustomerDivisionCombo.getSelectionModel().getSelectedItem());
            String customerId = updateCustomerIdText.getText();

            if(name.isEmpty() || address.isEmpty() || postalCode.isEmpty() || phone.isEmpty()) {
                Alerts.errorAlert("Enter Valid Data");
            } else {
                String query = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = " + customerId;

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
        } catch (DateTimeParseException e) {
            Alerts.errorAlert("Enter Valid Data");
        } catch (NullPointerException e) {
            Alerts.errorAlert("Enter Valid Data");
        } catch (SQLException e) {
            Alerts.errorAlert("Enter Valid Data");
        }
    }

    /**
     * Receives data from Customers screen and populates text fields
     * @param customer
     * @param
     * @throws SQLException
     */
    public void sendCustomer(Customers customer) throws SQLException {
        ObservableList<String> usOL = firstLevelDivisionsDAO.getUsDivisions();
        ObservableList<String> ukOL = firstLevelDivisionsDAO.getUkDivisions();
        ObservableList<String> canadaOL = firstLevelDivisionsDAO.getCanadaDivisions();
        int countryID = CountryConversions.divisionIdToCountryId(customer.getDivisionId());
        String divisionName = CountryConversions.divisionIdToName(customer.getDivisionId());

        updateCustomerIdText.setText(String.valueOf(customer.getCustomerId()));
        updateCustomerNameTxt.setText(customer.getCustomerName());
        updateCustomerAddressTxt.setText(customer.getCustomerAddress());
        updateCustomerPostalTxt.setText(customer.getCustomerPostalCode());
        updateCustomerPhoneTxt.setText(customer.getCustomerPhoneNumber());

        if (countryID == 1) {
            updateCustomerCountryCombo.getSelectionModel().select("US");
            updateCustomerDivisionCombo.setItems(usOL);
        } else if (countryID == 2) {
            updateCustomerCountryCombo.getSelectionModel().select("UK");
            updateCustomerDivisionCombo.setItems(ukOL);
        } else if (countryID == 3) {
            updateCustomerCountryCombo.getSelectionModel().select("Canada");
            updateCustomerDivisionCombo.setItems(canadaOL);
        }

        updateCustomerDivisionCombo.getSelectionModel().select(divisionName);
    }

    /**
     * Initialize method. Uses lambda expression to set division combo box. Using a lambda expression here makes code shorter and easier to read.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<String> countriesOL = FXCollections.observableArrayList("US", "UK", "Canada");
            ObservableList<String> usOL = firstLevelDivisionsDAO.getUsDivisions();
            ObservableList<String> ukOL = firstLevelDivisionsDAO.getUkDivisions();
            ObservableList<String> canadaOL = firstLevelDivisionsDAO.getCanadaDivisions();
            updateCustomerCountryCombo.setItems(countriesOL);

//            Lambda expression
            updateCustomerCountryCombo.setOnAction(e -> {
                if (updateCustomerCountryCombo.getValue() == "US") {
                    updateCustomerDivisionCombo.setItems(usOL);
                } else if (updateCustomerCountryCombo.getValue() == "UK") {
                    updateCustomerDivisionCombo.setItems(ukOL);
                } else if (updateCustomerCountryCombo.getValue() == "Canada") {
                    updateCustomerDivisionCombo.setItems(canadaOL);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}