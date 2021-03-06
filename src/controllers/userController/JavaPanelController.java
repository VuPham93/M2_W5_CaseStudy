package controllers.userController;

import controllers.LibraryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import library.java.JavaLibrary;
import server.serverInterface.ILibraryManager;
import tools.FinalList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class JavaPanelController extends LibraryController<JavaLibrary> {
    @FXML
    private TableView<JavaLibrary> tbData;

    @Override
    public void loadLibrary() throws RemoteException, NotBoundException, MalformedURLException {
        //Gọi interface ILibraryManager:
        ILibraryManager libraryManager = (ILibraryManager) Naming.lookup(FinalList.SERVER_IP);
        ArrayList<JavaLibrary> list = libraryManager.getJavaLibrary();

        //Lấy dữ liệu trả về từ server hiển thị lên bảng:
        ObservableList<JavaLibrary> javaLibraries = FXCollections.observableArrayList(list);
        tbData.setItems(javaLibraries);
    }


}
