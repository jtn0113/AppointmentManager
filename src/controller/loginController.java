package controller;

import DAO.appointmentsDAO;
import DAO.userDAO;
import helper.Alerts;
import helper.FileLogger;
import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller for login screen
 */
public class loginController implements Initializable {

    ShowScene scene = new ShowScene();
    Locale locale = Locale.getDefault();
    String lang = locale.getDisplayLanguage();
    ZoneId timeZone = ZoneId.systemDefault();

    @FXML
    private Button loginButton;

    @FXML
    private Label loginPassword;

    @FXML
    private TextField loginPasswordTxt;

    @FXML
    private Label loginTimezone;

    @FXML
    private Label loginUsername;

    @FXML
    private TextField loginUsernameTxt;

    /**
     * Verifies username and password are correct, logs in
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionLogin(ActionEvent event) throws IOException, SQLException {
        ArrayList<String> appointmentsWithinFifteen = new ArrayList<String>();
        String alertString = "";
        String userName = loginUsernameTxt.getText();
        String password = loginPasswordTxt.getText();

        if(userDAO.loginCorrect(userName, password)) {
            for (Appointments appointment : appointmentsDAO.getAllAppointments()) {
                if (appointment.getAppointmentStart().isAfter(LocalDateTime.now().minusMinutes(15)) && appointment.getAppointmentStart().isBefore(LocalDateTime.now().plusMinutes(15))) {
                    appointmentsWithinFifteen.add("Appointment ID = " + appointment.getAppointmentID() + " Appointment Start: " + appointment.getAppointmentStart());
                }
            }
            for(String string : appointmentsWithinFifteen) {
                alertString = alertString + string + "\n";
            }
            if(alertString.isEmpty()) {
                Alerts.informationAlert("No appointments within 15 minutes");
            } else {
                Alerts.informationAlert("Appointments within 15 minutes: " + "\n" + alertString);
            }
            FileLogger.logLogin(userName, true);
            scene.showScene(event, "/view/appointmentsScreen.fxml");
        } else {
            FileLogger.logLogin(userName, false);
            if(lang == "français") {
                Alerts.errorAlert("Le nom d'utilisateur ou le mot de passe sont incorrects");
            } else {
                Alerts.errorAlert("Username or Password are incorrect");
            }

        }

    }

    /**
     * Sets text based on system language
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(lang == "français") {
            loginUsername.setText("Nom d'utilisateur");
            loginPassword.setText("Mot de passe");
            loginTimezone.setText("" + timeZone);
            loginButton.setText("Connexion");
        } else {
            loginUsername.setText("Username");
            loginPassword.setText("Password");
            loginTimezone.setText("" + timeZone);
            loginButton.setText("Login");
        }
    }
}
