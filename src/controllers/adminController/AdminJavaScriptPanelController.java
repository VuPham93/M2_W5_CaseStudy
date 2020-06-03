package controllers.adminController;

import controllers.userController.JavaScriptPanelController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import library.Library;
import library.javaScript.JavaScriptLibrary;
import server.serverInterface.ILibraryManager;
import tools.FinalList;
import tools.SwitchPanel;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdminJavaScriptPanelController extends JavaScriptPanelController {

    @FXML
    private TableView<JavaScriptLibrary> tbData;

    @FXML
    private FontAwesomeIcon btnCut;

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_HOME_PANEL);
    }

    @FXML
    void openUserPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.USER_PANEL);
    }

    @FXML
    void openAddLibraryPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADD_LIBRARY);
    }

    @FXML
    void deleteRow(MouseEvent event) {
        Library selectedItem = tbData.getSelectionModel().getSelectedItem();
        tbData.getItems().remove(selectedItem);
    }

    @FXML
    private void saveData() {
        ArrayList<JavaScriptLibrary> jsList = new ArrayList<>(tbData.getItems());
        try {
            libraryManager.saveJSLibrary(jsList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    ILibraryManager libraryManager;
    {
        try {
            libraryManager = (ILibraryManager) Naming.lookup(FinalList.SERVER_IP);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public void changeSerial(TableColumn.CellEditEvent<JavaScriptLibrary, String> javaScriptLibraryStringCellEditEvent) {
        JavaScriptLibrary javaScriptLibrary = tbData.getSelectionModel().getSelectedItem();
        javaScriptLibrary.setSerial(javaScriptLibraryStringCellEditEvent.getNewValue());
    }

    public void changeName(TableColumn.CellEditEvent<JavaScriptLibrary, String> javaScriptLibraryStringCellEditEvent) {
        JavaScriptLibrary javaScriptLibrary = tbData.getSelectionModel().getSelectedItem();
        javaScriptLibrary.setName(javaScriptLibraryStringCellEditEvent.getNewValue());
    }

    public void changeSize(TableColumn.CellEditEvent<JavaScriptLibrary, String> javaScriptLibraryStringCellEditEvent) {
        JavaScriptLibrary javaScriptLibrary = tbData.getSelectionModel().getSelectedItem();
        javaScriptLibrary.setSize(javaScriptLibraryStringCellEditEvent.getNewValue());
    }

    public void changePath(TableColumn.CellEditEvent<JavaScriptLibrary, String> javaScriptLibraryStringCellEditEvent) {
        JavaScriptLibrary javaScriptLibrary = tbData.getSelectionModel().getSelectedItem();
        javaScriptLibrary.setPath(javaScriptLibraryStringCellEditEvent.getNewValue());
    }

    public void changeDetail(TableColumn.CellEditEvent<JavaScriptLibrary, String> javaScriptLibraryStringCellEditEvent) {
        JavaScriptLibrary javaScriptLibrary = tbData.getSelectionModel().getSelectedItem();
        javaScriptLibrary.setDetail(javaScriptLibraryStringCellEditEvent.getNewValue());
    }
}
