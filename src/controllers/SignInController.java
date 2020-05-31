package controllers;

import server.serverInterface.IUserManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tools.SwitchPanel;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SignInController {

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private FontAwesomeIcon btnMin;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Label btnForgotPassword;

    @FXML
    private Button btnFB;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblErrors;

    @FXML
    void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minWindow() {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }

    //Kiểm tra thông tin, nếu đúng vào màn hình chính:
    public void openHomePanel(MouseEvent event) {
        if (checkSignInInfo().equals("Admin")) {
            SwitchPanel.switchPanel(event, "/fxml/adminFxml/AdminHome.fxml");
        }
        if (checkSignInInfo().equals("User")) {
            SwitchPanel.switchPanel(event, "/fxml/userFxml/Home.fxml");
        }
    }

    //Vào màn hình đăng ký:
    public void openSignUpPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/userFxml/SignUp.fxml");
    }

    //Gọi interface IUserManager:
    IUserManager userManager;
    {
        try {
            userManager = (IUserManager) Naming.lookup("rmi://192.168.1.68/Server");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    //Kiểm tra thông tin đăng nhập qua IUserManager:
    private String checkSignInInfo() {
        String status = "";
        String nameOrEmail = txtUserName.getText();
        String password = txtPassword.getText();

        if (nameOrEmail.isEmpty() || password.isEmpty()) {
            signInStatus(Color.TOMATO, "Empty credentials");
            status = "false";
        }
        else {
            try {
                if ((userManager.checkUser(nameOrEmail, password)).equals("false")) {
                    signInStatus(Color.TOMATO, "Enter Correct Email/Password");
                    status = "false";
                }
                else {
                    if ((userManager.checkUser(nameOrEmail, password)).equals("Admin")) {
                        signInStatus(Color.GREEN, "Admin login Successful..Redirecting..");
                        status = "Admin";
                    }
                    else {
                        signInStatus(Color.GREEN, "Login Successful..Redirecting..");
                        status = "User";
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    //Thông báo kết quả kiểm tra thông tin:
    private void signInStatus(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
    }

}
