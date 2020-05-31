package controllers.adminController;

import controllers.userController.HomeController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import tools.SwitchPanel;

public class AdminHomeController extends HomeController {

    @FXML
    void openJavaScriptPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/adminFxml/AdminJavaScript.fxml");
    }

    @FXML
    void openJavaPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/adminFxml/AdminJava.fxml");
    }

    @FXML
    void openSoftWarePanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/adminFxml/AdminSoftware.fxml");
    }


}
