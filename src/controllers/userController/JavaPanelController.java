package controllers.userController;

import controllers.LibraryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import library.java.JavaLibrary;
import server.serverInterface.ILibraryManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class JavaPanelController extends LibraryController<JavaLibrary>  {
    @FXML
    private TableView<JavaLibrary> tbData;

    @FXML
    private TableColumn<JavaLibrary, Integer> colSerial;

    @FXML
    private TableColumn<JavaLibrary, String> colLesson;

    @FXML
    private TableColumn<JavaLibrary, String> colDetails;

    @Override
    public void loadLibrary() throws RemoteException, NotBoundException, MalformedURLException {
        //Gọi interface ILibraryManager:
        ILibraryManager libraryManager = (ILibraryManager) Naming.lookup("rmi://192.168.1.68/Server");
        ArrayList<JavaLibrary> list = libraryManager.getJavaLibrary();

        //Lấy dữ liệu trả về từ server hiển thị lên bảng:
        ObservableList<JavaLibrary> javaLibraries = FXCollections.observableArrayList(list);
        tbData.setItems(javaLibraries);
    }
}
