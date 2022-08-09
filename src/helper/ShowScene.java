package helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowScene {

    Stage stage;
    Parent scene;

    /**
     * Changes scene to url passed in as parameter
     * @param event
     * @param url
     * @throws IOException
     */
    public void showScene(ActionEvent event, String url) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(url));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
