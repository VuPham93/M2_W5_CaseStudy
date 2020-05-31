package tools;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.Library;

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
    private TableView<LibraryType> tbData;

    @FXML
    private TableColumn<LibraryType, Integer> colSerial;

    @FXML
    private TableColumn<LibraryType, String> colLesson;

    @FXML
    private TableColumn<LibraryType, String> colDetails;

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
        try {
            loadLibrary();
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
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
