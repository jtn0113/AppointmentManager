package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    public void onActionLogin(ActionEvent event) {
        System.out.println("Logged in");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
