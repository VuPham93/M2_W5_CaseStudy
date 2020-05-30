package controllers;

import server.IUserManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SignInController {

    public SignInController() throws RemoteException, NotBoundException, MalformedURLException {
    }

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
        if (checkSignInInfo()) {
            SwitchPanel.switchPanel(event,"/fxml/Home.fxml");
        }
    }

    //Vào màn hình đăng ký:
    public void openSignUpPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/SignUp.fxml");
    }

    //Gọi interface IUserManager:
    IUserManager userManager = (IUserManager) Naming.lookup("rmi://192.168.1.68/Server");

    //Kiểm tra thông tin đăng nhập qua IUserManager:
    private boolean checkSignInInfo() {
        boolean status = true;
        String nameOrEmail = txtUserName.getText();
        String password = txtPassword.getText();

        if (nameOrEmail.isEmpty() || password.isEmpty()) {
            signInStatus(Color.TOMATO, "Empty credentials");
            status = false;
        }
        else {
            try {
                if (!userManager.checkUser(nameOrEmail, password)) {
                    signInStatus(Color.TOMATO, "Enter Correct Email/Password");
                    status = false;
                }
                else {
                    signInStatus(Color.GREEN, "Login Successful..Redirecting..");
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
