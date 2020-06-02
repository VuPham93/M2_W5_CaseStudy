package controllers.adminController;

import controllers.userController.JavaPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import library.java.JavaLibrary;
import server.serverInterface.ILibraryManager;
import tools.FinalList;
import tools.SwitchPanel;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdminJavaPanelController extends JavaPanelController {
    @FXML
    private TableView<JavaLibrary> tbData;

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_HOME_PANEL);
    }
    @FXML
    private void saveData() {
        ArrayList<JavaLibrary> javaList = new ArrayList<>(tbData.getItems());
        try {
            libraryManager.saveJavaLibrary(javaList);
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

    public void changeSerial(TableColumn.CellEditEvent<JavaLibrary, String> javaLibraryStringCellEditEvent) {
        JavaLibrary javaLibrary = tbData.getSelectionModel().getSelectedItem();
        javaLibrary.setSerial(javaLibraryStringCellEditEvent.getNewValue());
    }

    public void changeName(TableColumn.CellEditEvent<JavaLibrary, String> javaLibraryStringCellEditEvent) {
        JavaLibrary javaLibrary = tbData.getSelectionModel().getSelectedItem();
        javaLibrary.setName(javaLibraryStringCellEditEvent.getNewValue());
    }

    public void changeSize(TableColumn.CellEditEvent<JavaLibrary, String> javaLibraryStringCellEditEvent) {
        JavaLibrary javaLibrary = tbData.getSelectionModel().getSelectedItem();
        javaLibrary.setSize(javaLibraryStringCellEditEvent.getNewValue());
    }

    public void changePath(TableColumn.CellEditEvent<JavaLibrary, String> javaLibraryStringCellEditEvent) {
        JavaLibrary javaLibrary = tbData.getSelectionModel().getSelectedItem();
        javaLibrary.setPath(javaLibraryStringCellEditEvent.getNewValue());
    }

    public void changeDetail(TableColumn.CellEditEvent<JavaLibrary, String> javaLibraryStringCellEditEvent) {
        JavaLibrary javaLibrary = tbData.getSelectionModel().getSelectedItem();
        javaLibrary.setDetail(javaLibraryStringCellEditEvent.getNewValue());
    }
}
