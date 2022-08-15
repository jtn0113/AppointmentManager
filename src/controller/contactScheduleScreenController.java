package controller;

import DAO.appointmentsDAO;
import DAO.contactsDAO;
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
 * Controller for Contact Schedule screen
 */
public class contactScheduleScreenController implements Initializable {
    ShowScene scene = new ShowScene();
    Stage stage;
    int contactID;
    String contactName;

    @FXML
    private TableColumn<?, ?> AppointmentIdTableCol;

    @FXML
    private ComboBox<String> ContactCombo;

    @FXML
    private TableView<Appointments> ContactScheduleTableView;

    @FXML
    private TableColumn<?, ?> CustomerIdTableCol;

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
     * Returns to appointments screen
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
            ContactCombo.setItems(contactsDAO.getAllContactNames());

//            Lambda expression
            ContactCombo.setOnAction(e ->  {
                contactName = ContactCombo.getValue();
                try {
                    contactID = contactsDAO.contactNameToId(contactName);
                    ObservableList<Appointments> appointmentsObservableList = appointmentsDAO.getAllContactsAppointments(contactID);
                    ContactScheduleTableView.setItems(appointmentsObservableList);
                    AppointmentIdTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                    TitleTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                    TypeTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
                    DescriptionTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
                    StartDateTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
                    EndDateTableCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
                    CustomerIdTableCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
