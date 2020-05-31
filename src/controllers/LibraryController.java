package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.Library;
import tools.FileDownloadManager;
import tools.SwitchPanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public abstract class LibraryController<LibraryType extends Library> implements Initializable {

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private FontAwesomeIcon btnMin;

    @FXML
    private FontAwesomeIcon btnMax;

    @FXML
    private FontAwesomeIcon btnBack;

    @FXML
    private FontAwesomeIcon btnLibraryManager;

    @FXML
    private FontAwesomeIcon btnUserManager;

    @FXML
    private TableView<LibraryType> tbData;

    @FXML
    private TableColumn<LibraryType, String> colSerial;

    @FXML
    private TableColumn<LibraryType, String> colLesson;

    @FXML
    private TableColumn<LibraryType, String> colDetails;

    @FXML
    private TableColumn<LibraryType, String> colFileSize;

    @FXML
    private TableColumn<LibraryType, String> colPath;

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
        SwitchPanel.switchPanel(event, "/fxml/userFxml/Home.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        try {
            loadLibrary();
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //Tạo bảng:
    private void initTable() {
        initCols();
    }

    //Tạo cột:
    private void initCols() {
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colLesson.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("detail"));
        colFileSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPath.setCellValueFactory(new PropertyValueFactory<>("path"));

        editableColumn();
    }

    //Cài đặt tính năng chỉnh sửa cho từng cột:
    private void editableColumn() {
        editTable(colSerial);
        editTable(colLesson);
        editTable(colDetails);
        editTable(colFileSize);
        editTable(colPath);

        tbData.setEditable(true);
    }

    //Sự kiện kích chuột để chỉnh sửa dòng:
    private void editTable(TableColumn<LibraryType, String> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSerial(e.getNewValue());
        });
    }

    //Tải danh sách thư viện:
    public void loadLibrary() throws RemoteException, NotBoundException, MalformedURLException {
    }

    //Kích đúp vào dòng để download:
    public void clickItem(MouseEvent event) throws RemoteException, NotBoundException, MalformedURLException {
        if (event.getClickCount() == 2) {
            //Mở danh sách file đã tải:
            FileDownloadManager<LibraryType> downLoadFile = new FileDownloadManager<>();
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
