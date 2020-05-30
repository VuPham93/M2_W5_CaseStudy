package controllers;

import client.PathChooser;
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
import library.FileReaderAndWriter;
import library.javaScript.JavaScriptLibrary;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JavaScriptPanelController implements Initializable {

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

    public void clickItem(MouseEvent event) {
        Stage stage = (Stage) tbData.getScene().getWindow();
        if (event.getClickCount() == 2) {
            PathChooser pathChooserDemo = new PathChooser();
            pathChooserDemo.choosePath(event, stage);
            System.out.println(pathChooserDemo.getPath());
        }
    }

    FileReaderAndWriter<JavaScriptLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
    ArrayList<JavaScriptLibrary> list = (ArrayList<JavaScriptLibrary>) fileReaderAndWriter.readFile("/src/library/javaScript/JavaScriptLibrary.txt");

    private ObservableList<JavaScriptLibrary> javaScriptLibraries = FXCollections.observableArrayList(list);

    private void loadLibrary() {
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colLesson.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("detail"));
        tbData.setItems(javaScriptLibraries);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLibrary();
    }
}
