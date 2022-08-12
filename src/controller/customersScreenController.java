package controller;

import DAO.appointmentsDAO;
import DAO.customerDAO;
import helper.Alerts;
import helper.JDBC;
import helper.ShowScene;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for Customers screen
 */
public class customersScreenController implements Initializable {

    ShowScene scene = new ShowScene();
    Stage stage;

    @FXML
    private TableView<Customers> CustomersTableView;

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

    /**
     * Shows Add Customer Screen when button is clicked
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/addCustomerScreen.fxml");
    }

    /**
     * Deletes selected customer
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException, IOException{
        try {
            int customerId = CustomersTableView.getSelectionModel().getSelectedItem().getCustomerId();
            String customerName = CustomersTableView.getSelectionModel().getSelectedItem().getCustomerName();
            if (appointmentsDAO.customerHasAppointments(customerId)) {
                Alerts.errorAlert("Customers appointments must be deleted before deleting customer");
            } else {
                String query = "DELETE FROM customers WHERE Customer_ID = " + customerId;
                PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
                ps.execute();
                Alerts.informationAlert(customerName + " deleted");
                scene.showScene(event, "/view/customersScreen.fxml");
            }
        } catch (NullPointerException e) {
            Alerts.errorAlert("Select a Customer");
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
     * Shows appointments table
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionShowAppointments(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    /**
     * Shows Update Customer screen when button is clicked
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException, SQLException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateCustomerScreen.fxml"));
            loader.load();

            updateCustomerScreenController UCController = loader.getController();
            UCController.sendCustomer(CustomersTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NullPointerException e) {
            Alerts.errorAlert("Select a Customer");
        }
    }

    /**
     * Initialize method
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            ObservableList<Customers> customersObservableList = customerDAO.getAllCustomers();

            CustomersTableView.setItems(customersObservableList);
            customerIdTableCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameTableCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            customerAddressTableCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            customerPostalTableCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
            customerPhoneTableCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
            customerDivisionIdTableCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}