package controllers.adminController;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.java.JavaLibrary;
import library.javaScript.JavaScriptLibrary;
import library.software.SoftwareLibrary;
import server.serverInterface.ILibraryManager;
import tools.FinalList;
import tools.SwitchPanel;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AddToLibraryController {

    @FXML
    private AnchorPane AddToLibraryPanel;

    @FXML
    private TextField txtSerial;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtPath;

    @FXML
    private TextField txtDetail;

    @FXML
    private Button btnAddToJS;

    @FXML
    private Button btnAddToJava;

    @FXML
    private Button btnAddToSoftware;

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private FontAwesomeIcon btnMin;

    @FXML
    void closeWindow(MouseEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_HOME_PANEL);
    }

    @FXML
    void minWindow(MouseEvent event) {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }

    ILibraryManager libraryManager;
    {
        try {
            libraryManager = (ILibraryManager) Naming.lookup(FinalList.SERVER_IP);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addToJSLibrary(MouseEvent event) {
        String serial = txtSerial.getText();
        String name = txtName.getText();
        String size = txtSize.getText();
        String path = txtPath.getText();
        String detail = txtDetail.getText();

        try {
            ArrayList<JavaScriptLibrary> jsList = libraryManager.getJSLibrary();
            jsList.add(new JavaScriptLibrary(serial, name, size, path, detail));
            libraryManager.saveJSLibrary(jsList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addToJavaLibrary(MouseEvent event) {
        String serial = txtSerial.getText();
        String name = txtName.getText();
        String size = txtSize.getText();
        String path = txtPath.getText();
        String detail = txtDetail.getText();

        try {
            ArrayList<JavaLibrary> javaList = libraryManager.getJavaLibrary();
            javaList.add(new JavaLibrary(serial, name, size, path, detail));
            libraryManager.saveJavaLibrary(javaList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addToSoftwareLibrary(MouseEvent event) {
        String serial = txtSerial.getText();
        String name = txtName.getText();
        String size = txtSize.getText();
        String path = txtPath.getText();
        String detail = txtDetail.getText();

        try {
            ArrayList<SoftwareLibrary> softwareList = libraryManager.getSoftwareLibrary();
            softwareList.add(new SoftwareLibrary(serial, name, size, path, detail));
            libraryManager.saveSoftwareLibrary(softwareList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



}