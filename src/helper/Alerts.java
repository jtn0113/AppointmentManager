package helper;

import javafx.scene.control.Alert;

/**
 * Class with methods to show alerts
 */
public class Alerts {

    /**
     * Displays information alert
     * @param alertMessage
     */
    public static void informationAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setContentText(alertMessage);
        alert.show();
    }

    /**
     * Displays error alert
     * @param alertMessage
     */
    public static void errorAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setContentText(alertMessage);
        alert.show();
    }

    /**
     * Displays warning alert
     * @param alertMessage
     */
    public static void warningAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.setContentText(alertMessage);
        alert.show();
    }
}
