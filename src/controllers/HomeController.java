package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tools.SwitchPanel;

public class HomeController {

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnJava;

    @FXML
    private Button bntSoftWares;

    @FXML
    private Button btnJavaScript;

    @FXML
    private Button btnSignOut;

    @FXML
    private FontAwesomeIcon btnMin;

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minWindow() {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/SignIn.fxml");
    }

    @FXML
    void openJavaScriptPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/JavaScript.fxml");
    }

    @FXML
    void openJavaPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/JavaScript.fxml");
    }

    @FXML
    void openSoftWarePanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/JavaScript.fxml");
    }


}
