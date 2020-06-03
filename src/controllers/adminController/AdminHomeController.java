package controllers.adminController;

import controllers.userController.HomeController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import tools.FinalList;
import tools.SwitchPanel;

public class AdminHomeController extends HomeController {

    @FXML
    private FontAwesomeIcon btnUserManager;

    @FXML
    private FontAwesomeIcon btnAddLibrary;

    @FXML
    void openAddLibraryPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADD_LIBRARY);
    }

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.SIGN_IN_PANEL);
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
