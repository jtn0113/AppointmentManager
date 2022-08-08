package controller;

import DAO.customerDAO;
import helper.JDBC;
import helper.ShowScene;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class customersScreenController implements Initializable {

    ShowScene scene = new ShowScene();

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