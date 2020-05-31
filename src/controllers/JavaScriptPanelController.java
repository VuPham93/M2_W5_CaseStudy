package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.javaScript.JavaScriptLibrary;
import server.serverInterface.ILibraryManager;
import tools.LibraryController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class JavaScriptPanelController extends LibraryController<JavaScriptLibrary>{
    @FXML
    private TableView<JavaScriptLibrary> tbData;

    @FXML
    private TableColumn<JavaScriptLibrary, Integer> colSerial;

    @FXML
    private TableColumn<JavaScriptLibrary, String> colLesson;

    @FXML
    private TableColumn<JavaScriptLibrary, String> colDetails;

    @Override
    public void loadLibrary() throws RemoteException, NotBoundException, MalformedURLException {
        //Gọi interface ILibraryManager:
        ILibraryManager libraryManager = (ILibraryManager) Naming.lookup("rmi://192.168.1.68/Server");
        ArrayList<JavaScriptLibrary> list = libraryManager.getJSLibrary();

        //Lấy dữ liệu trả về từ server hiển thị lên bảng:
        ObservableList<JavaScriptLibrary> javaScriptLibraries = FXCollections.observableArrayList(list);

        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colLesson.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("path"));
        tbData.setItems(javaScriptLibraries);
    }
}
