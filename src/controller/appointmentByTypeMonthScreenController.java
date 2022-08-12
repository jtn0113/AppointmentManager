package controller;

import DAO.appointmentsDAO;
import helper.ShowScene;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.MonthCount;
import model.TypeCount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class appointmentByTypeMonthScreenController implements Initializable {
    ShowScene scene = new ShowScene();
    Stage stage;

    @FXML
    private TableColumn<?, ?> AppointmentMonthTableCol;

    @FXML
    private TableColumn<?, ?> AppointmentMonthTotalTableCol;

    @FXML
    private TableColumn<?, ?> AppointmentTypeTableCol;

    @FXML
    private TableColumn<?, ?> AppointmentTypeTotalTableCol;

    @FXML
    private TableView<MonthCount> AppointmentsMonthTableView;

    @FXML
    private TableView<TypeCount> AppointmentsTypeTableView;

    @FXML
    void onActionShowAppointments(ActionEvent event) throws IOException {
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<TypeCount> appointmentsObservableList = appointmentsDAO.getAllTypes();
            AppointmentsTypeTableView.setItems(appointmentsObservableList);
            AppointmentTypeTableCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            AppointmentTypeTotalTableCol.setCellValueFactory(new PropertyValueFactory<>("count"));

            ObservableList<MonthCount> appointmentsObservableList2 = appointmentsDAO.getAllMonths();
            AppointmentsMonthTableView.setItems(appointmentsObservableList2);
            AppointmentMonthTableCol.setCellValueFactory(new PropertyValueFactory<>("month"));
            AppointmentMonthTotalTableCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
