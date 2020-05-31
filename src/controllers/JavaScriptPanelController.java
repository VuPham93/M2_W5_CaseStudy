package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.javaScript.JavaScriptLibrary;
import server.serverInterface.ILibraryManager;
import tools.FileDownloadManager;
import tools.SwitchPanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JavaScriptPanelController implements Initializable {

    public JavaScriptPanelController() throws RemoteException, NotBoundException, MalformedURLException {
    }

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private FontAwesomeIcon btnMin;

    @FXML
    private FontAwesomeIcon btnMax;

    @FXML
    private FontAwesomeIcon btnBack;

    @FXML
    private TableView<JavaScriptLibrary> tbData;

    @FXML
    private TableColumn<JavaScriptLibrary, Integer> colSerial;

    @FXML
    private TableColumn<JavaScriptLibrary, String> colLesson;

    @FXML
    private TableColumn<JavaScriptLibrary, String> colDetails;

    @FXML
    void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void maxWindow() {
        Stage stage = (Stage) btnMax.getScene().getWindow();
        stage.setFullScreen(true);
    }

    @FXML
    void minWindow() {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/Home.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLibrary();
    }

    //Gọi interface ILibraryManager:
    ILibraryManager libraryManager = (ILibraryManager) Naming.lookup("rmi://192.168.1.68/Server");
    ArrayList<JavaScriptLibrary> list = libraryManager.getJSLibrary();

    //Lấy dữ liệu trả về từ server hiển thị lên bảng:
    private final ObservableList<JavaScriptLibrary> javaScriptLibraries = FXCollections.observableArrayList(list);
    private void loadLibrary() {
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colLesson.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("path"));
        tbData.setItems(javaScriptLibraries);
    }

    //Kích đúp vào dòng để download:
    public void clickItem(MouseEvent event) throws RemoteException, NotBoundException, MalformedURLException {
        if (event.getClickCount() == 2) {
            //Mở danh sách file đã tải:
            FileDownloadManager<JavaScriptLibrary> downLoadFile = new FileDownloadManager<>();
            String savedFileLocation = downLoadFile.fileIsDownloaded(tbData);

            //Kiểm tra xem đã tải file chưa:
            if (!savedFileLocation.equals("File not found")) {
                //Mở file:
                try {
                    Desktop.getDesktop().open(new File(savedFileLocation));
                } catch (IOException e) {
                    downLoadFile.downLoadFile(event, tbData);
                }
            }
            else {
                //Tải file:
                downLoadFile.downLoadFile(event, tbData);
            }
        }
    }
}
