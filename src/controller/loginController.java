package controller;

import DAO.userDAO;
import helper.Alerts;
import helper.FileLogger;
import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

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
        String userName = loginUsernameTxt.getText();
        String password = loginPasswordTxt.getText();
        if(userDAO.loginCorrect(userName, password)) {
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
     * Sets test based on system language
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
