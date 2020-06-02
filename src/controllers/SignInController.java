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
import tools.FinalList;
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
        if (checkSignInInfo().equals(FinalList.ADMIN)) {
            SwitchPanel.switchPanel(event, FinalList.ADMIN_HOME_PANEL);
        }
        if (checkSignInInfo().equals(FinalList.USER)) {
            SwitchPanel.switchPanel(event, FinalList.USER_HOME_PANEL);
        }
    }

    //Vào màn hình đăng ký:
    public void openSignUpPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.SIGN_UP_PANEL);
    }


    //Vào màn hình đổi password:
    public void openForgotPasswordPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.FORGOT_PASSWORD_PANEL);
    }

    //Gọi interface IUserManager:
    IUserManager userManager;
    {
        try {
            userManager = (IUserManager) Naming.lookup(FinalList.SERVER_IP);
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
            status = FinalList.NOT_AN_USER;
        }
        else {
            try {
                String isUser = userManager.checkUser(nameOrEmail, password);
                if (isUser.equals(FinalList.NOT_AN_USER)) {
                    signInStatus(Color.TOMATO, "Enter Correct Email/Password");
                    status = FinalList.NOT_AN_USER;
                }
                else {
                    if (isUser.equals(FinalList.ADMIN)) {
                        signInStatus(Color.GREEN, "Admin login Successful..Redirecting..");
                        status = FinalList.ADMIN;
                    }
                    else {
                        signInStatus(Color.GREEN, "Login Successful..Redirecting..");
                        status = FinalList.USER;
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
