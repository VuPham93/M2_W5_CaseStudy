package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import server.serverInterface.IUserManager;
import tools.FinalList;
import tools.SwitchPanel;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.regex.Pattern;

public class ForgotPasswordController extends SignUpController {

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Button btnUpdatePassword;

    @FXML
    private Label lblUserErrors;

    @FXML
    private Label lblEmailErrors;

    //Gọi interface IUserManager:
    IUserManager userManager;
    {
        try {
            userManager = (IUserManager) Naming.lookup(FinalList.SERVER_IP);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    //Lưu thông tin mới:
    public void updatePassword(MouseEvent event) {
        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (isValidUserName(name, email) && isValidEmail(name, email) && isValidPassword(password) && isValidConfirmPassword(password, confirmPassword)) {
            try {
                userManager.saveUserInfo(name, email, password);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            SwitchPanel.switchPanel(event, FinalList.SIGN_IN_PANEL);
        }
    }

    //Kiểm tra tên:
    public boolean isValidUserName(String name, String email) {
        String regex = FinalList.USER_NAME_REGEX;
        boolean status = true;

        signUpStatus(Color.GREEN, "Username match", lblUserErrors);

        if (!Pattern.matches(regex, name)) {
            signUpStatus(Color.TOMATO, "Username must start with uppercase and 2 to 10 characters", lblUserErrors);
            status = false;
        }

        try {
            if (!userManager.checkOldUser(name, email)) {
                signUpStatus(Color.TOMATO, "Enter your information", lblUserErrors);
                signUpStatus(Color.TOMATO, "Enter your information", lblEmailErrors);
                status = false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return status;
    }

    //Kiểm tra email:
    public boolean isValidEmail(String name, String email) {
        String regex = FinalList.EMAIL_REGEX;
        boolean status = true;

        signUpStatus(Color.GREEN, "Email match", lblEmailErrors);

        if (!Pattern.matches(regex, email)) {
            signUpStatus(Color.TOMATO, "Email must be gmail", lblEmailErrors);
            status = false;
        }

        try {
            if (!userManager.checkOldUser(name, email)) {
                signUpStatus(Color.TOMATO, "Enter your information", lblUserErrors);
                signUpStatus(Color.TOMATO, "Enter your information", lblEmailErrors);
                status = false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return status;
    }

    //Thông báo kết quả kiểm tra thông tin:
    private void signUpStatus(Color color, String text, Label label) {
        label.setTextFill(color);
        label.setText(text);
    }
}