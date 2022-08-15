package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
import DAO.customerDAO;
import helper.ShowScene;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for customers schedule screen
 */
public class customersScheduleScreenController implements Initializable {
    ShowScene scene = new ShowScene();
    Stage stage;
    int customerID;
    String customerName;

    @FXML
    private TableColumn<?, ?> AppointmentIdTableCol;

    @FXML
    private TableColumn<?, ?> ContactIdTableCol;

    @FXML
    private ComboBox<String> CustomerCombo;

    @FXML
    private TableView<Appointments> CustomerScheduleTableView;

    @FXML
    private TableColumn<?, ?> DescriptionTableCol;

    @FXML
    private TableColumn<?, ?> EndDateTableCol;

    @FXML
    private TableColumn<?, ?> StartDateTableCol;

    @FXML
    private TableColumn<?, ?> TitleTableCol;

    @FXML
    private TableColumn<?, ?> TypeTableCol;

    /**
     * Returns to show appointments screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionShowAppointments(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    /**
     * Initialize method. Uses lambda expression to set table view. Using a lambda expression here makes code shorter and easier to read.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            CustomerCombo.setItems(customerDAO.getAllCustomerNames());

//            Lambda expression
            CustomerCombo.setOnAction(e ->  {
                customerName = CustomerCombo.getValue();
                try {
                    customerID = customerDAO.customerNameToId(customerName);
                    ObservableList<Appointments> appointmentsObservableList = appointmentsDAO.getAllCustomerAppointments(customerID);
                    CustomerScheduleTableView.setItems(appointmentsObservableList);
                    AppointmentIdTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                    TitleTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                    TypeTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
                    DescriptionTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
                    StartDateTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
                    EndDateTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
                    ContactIdTableCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}