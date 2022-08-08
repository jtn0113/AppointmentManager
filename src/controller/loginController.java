package controller;

import helper.ShowScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    ShowScene scene = new ShowScene();

    @FXML
    private ToggleGroup languageTG;

    @FXML
    private Button loginButton;

    @FXML
    private RadioButton loginEnglishRBtn;

    @FXML
    private RadioButton loginFrenchRBtn;

    @FXML
    private Label loginPassword;

    @FXML
    private TextField loginPasswordTxt;

    @FXML
    private Label loginSelectLanguage;

    @FXML
    private Label loginTimezone;

    @FXML
    private Label loginUsername;

    @FXML
    private TextField loginUsernameTxt;

    @FXML
    void onActionLoginEnglish(ActionEvent event) {
        loginSelectLanguage.setText("Select Language");
        loginEnglishRBtn.setText("English");
        loginFrenchRBtn.setText("French");
        loginUsername.setText("Username");
        loginPassword.setText("Password");
        loginTimezone.setText("Timezone:");
        loginButton.setText("Login");
    }

    @FXML
    void onActionLoginFrench(ActionEvent event) {
        loginSelectLanguage.setText("Choisir la langue");
        loginEnglishRBtn.setText("Anglais");
        loginFrenchRBtn.setText("Français");
        loginUsername.setText("Nom d'utilisateur");
        loginPassword.setText("Mot de passe");
        loginTimezone.setText("Fuseau horaire:");
        loginButton.setText("Connexion");
    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException{
        scene.showScene(event, "/view/appointmentsScreen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
