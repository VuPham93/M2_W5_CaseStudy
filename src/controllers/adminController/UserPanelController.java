package controllers.adminController;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.serverInterface.IUserManager;
import tools.FinalList;
import tools.SwitchPanel;
import users.User;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserPanelController implements Initializable {

    @FXML
    private AnchorPane JSPanel;

    @FXML
    private FontAwesomeIcon btnCut;

    @FXML
    private FontAwesomeIcon btnLibraryManager;

    @FXML
    private FontAwesomeIcon btnMin;

    @FXML
    private FontAwesomeIcon btnMax;

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private FontAwesomeIcon btnBack;

    @FXML
    private TableView<User> tbData;

    @FXML
    private TableColumn<User, String> colType ;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colEmail ;

    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    void changeName(ActionEvent event) {

    }

    @FXML
    void changeSize(ActionEvent event) {

    }

    @FXML
    void clickItem(MouseEvent event) {

    }

    @FXML
    void closeWindow(MouseEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteRow(MouseEvent event) {
        User selectedItem = tbData.getSelectionModel().getSelectedItem();
        tbData.getItems().remove(selectedItem);
    }

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.ADMIN_HOME_PANEL);
    }

    @FXML
    void maxWindow(MouseEvent event) {
        Stage stage = (Stage) btnMax.getScene().getWindow();
        stage.setFullScreen(true);
    }

    @FXML
    void minWindow(MouseEvent event) {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void saveData(MouseEvent event) {
        ArrayList<User> userList = new ArrayList<>(tbData.getItems());
        try {
            useList.saveUserList(userList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    IUserManager useList;
    {
        try {
            useList = (IUserManager) Naming.lookup(FinalList.SERVER_IP);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public void changeType(TableColumn.CellEditEvent<User, String> userListStringCellEditEvent) {
        User userList = tbData.getSelectionModel().getSelectedItem();
        userList.setType(userListStringCellEditEvent.getNewValue());
    }

    public void changeName(TableColumn.CellEditEvent<User, String> userListStringCellEditEvent) {
        User userList = tbData.getSelectionModel().getSelectedItem();
        userList.setName(userListStringCellEditEvent.getNewValue());
    }

    public void changeEmail(TableColumn.CellEditEvent<User, String> userListStringCellEditEvent) {
        User userList = tbData.getSelectionModel().getSelectedItem();
        userList.setEmail(userListStringCellEditEvent.getNewValue());
    }

    public void changePassword(TableColumn.CellEditEvent<User, String> userListStringCellEditEvent) {
        User userList = tbData.getSelectionModel().getSelectedItem();
        userList.setPassword(userListStringCellEditEvent.getNewValue());
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

    private void initTable() {
        initCols();
    }

    private void initCols() {
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        editAbleTable();
    }

    private void editAbleTable() {
        editAbleColumn(colType);
        editAbleColumn(colName);
        editAbleColumn(colEmail);
        editAbleColumn(colPassword);

        tbData.setEditable(true);
    }

    private void editAbleColumn(TableColumn<User, String> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void loadLibrary() throws RemoteException, NotBoundException, MalformedURLException {
        ArrayList<User> list = useList.getUserList();

        ObservableList<User> userList = FXCollections.observableArrayList(list);
        tbData.setItems(userList);
    }
}
