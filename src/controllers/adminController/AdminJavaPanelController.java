package controllers.adminController;

import controllers.userController.JavaPanelController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import tools.FinalList;
import tools.SwitchPanel;

public class AdminJavaPanelController extends JavaPanelController {
    @FXML
    private FontAwesomeIcon btnLibraryManager;

    @FXML
    private FontAwesomeIcon btnUserManager;

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_HOME_PANEL);
    }
}
