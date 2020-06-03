package controllers.adminController;

import controllers.userController.SoftwarePanelController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import library.Library;
import library.software.SoftwareLibrary;
import server.serverInterface.ILibraryManager;
import tools.FinalList;
import tools.SwitchPanel;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdminSoftwareController extends SoftwarePanelController {
    @FXML
    private TableView<SoftwareLibrary> tbData;

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
        ArrayList<SoftwareLibrary> softwareList = new ArrayList<>(tbData.getItems());
        try {
            libraryManager.saveSoftwareLibrary(softwareList);
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

    public void changeSerial(TableColumn.CellEditEvent<SoftwareLibrary, String> softwareLibraryStringCellEditEvent) {
        SoftwareLibrary softwareLibrary = tbData.getSelectionModel().getSelectedItem();
        softwareLibrary.setSerial(softwareLibraryStringCellEditEvent.getNewValue());
    }

    public void changeName(TableColumn.CellEditEvent<SoftwareLibrary, String> softwareLibraryStringCellEditEvent) {
        SoftwareLibrary softwareLibrary = tbData.getSelectionModel().getSelectedItem();
        softwareLibrary.setName(softwareLibraryStringCellEditEvent.getNewValue());
    }

    public void changeSize(TableColumn.CellEditEvent<SoftwareLibrary, String> softwareLibraryStringCellEditEvent) {
        SoftwareLibrary softwareLibrary = tbData.getSelectionModel().getSelectedItem();
        softwareLibrary.setSize(softwareLibraryStringCellEditEvent.getNewValue());
    }

    public void changePath(TableColumn.CellEditEvent<SoftwareLibrary, String> softwareLibraryStringCellEditEvent) {
        SoftwareLibrary softwareLibrary = tbData.getSelectionModel().getSelectedItem();
        softwareLibrary.setPath(softwareLibraryStringCellEditEvent.getNewValue());
    }

    public void changeDetail(TableColumn.CellEditEvent<SoftwareLibrary, String> softwareLibraryStringCellEditEvent) {
        SoftwareLibrary softwareLibrary = tbData.getSelectionModel().getSelectedItem();
        softwareLibrary.setDetail(softwareLibraryStringCellEditEvent.getNewValue());
    }
}
