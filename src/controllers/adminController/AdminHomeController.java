package controllers.adminController;

import controllers.userController.HomeController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import tools.FinalList;
import tools.SwitchPanel;

public class AdminHomeController extends HomeController {
    @FXML
    private FontAwesomeIcon btnLibraryManager;

    @FXML
    private FontAwesomeIcon btnUserManager;

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_HOME_PANEL);
    }

    @FXML
    void openJavaScriptPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_JAVASCRIPT_PANEL);
    }

    @FXML
    void openJavaPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_JAVA_PANEL );
    }

    @FXML
    void openSoftWarePanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_SOFTWARE_PANEL);
    }


}
