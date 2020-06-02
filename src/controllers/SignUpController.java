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
import java.util.regex.Pattern;

public class SignUpController {

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private FontAwesomeIcon btnMin;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblUserErrors;

    @FXML
    private Label lblEmailErrors;

    @FXML
    private Label lblPasswordErrors;

    @FXML
    private Label lblConfirmPasswordErrors;

    @FXML
    void closeWindow(MouseEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minWindow(MouseEvent event) {
        Stage stage = (Stage) btnMin.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void goBack(MouseEvent event) {
        SwitchPanel.switchPanel(event, FinalList.SIGN_IN_PANEL);
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

    //Lưu thông tin đăng ký:
    public void signUp(MouseEvent event) {
        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (isValidUserName(name) && isValidEmail(email) && isValidPassword(password) && isValidConfirmPassword(password, confirmPassword)) {
            try {
                userManager.saveNewUser(name, email, password);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            SwitchPanel.switchPanel(event, FinalList.SIGN_IN_PANEL);
        }
    }

    //Kiểm tra tên đăng ký:
    public boolean isValidUserName(String name) {
        String regex = FinalList.USER_NAME_REGEX;
        boolean status = true;

        signUpStatus(Color.GREEN, "Username available", lblUserErrors);

        if (!Pattern.matches(regex, name)) {
            signUpStatus(Color.TOMATO, "Username must start with uppercase and 2 to 10 characters", lblUserErrors);
            status = false;
        }

        try {
            if (!userManager.checkValidUser(name)) {
                signUpStatus(Color.TOMATO, "User already exists", lblUserErrors);
                status = false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return status;
    }

    //Kiểm tra email đăng ký:
    public boolean isValidEmail(String email) {
        String regex = FinalList.EMAIL_REGEX;
        boolean status = true;

        signUpStatus(Color.GREEN, "Email available", lblEmailErrors);

        if (!Pattern.matches(regex, email)) {
            signUpStatus(Color.TOMATO, "Email must be gmail", lblEmailErrors);
            status = false;
        }

        try {
            if (!userManager.checkValidUser(email)) {
                signUpStatus(Color.TOMATO, "User already exists", lblEmailErrors);
                status = false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return status;
    }

    //Kiểm tra mật khẩu:
    public boolean isValidPassword(String password) {
        String regex = FinalList.PASSWORD_REGEX;
        boolean status = true;

        signUpStatus(Color.GREEN, "Right password form", lblPasswordErrors);

        if (!Pattern.matches(regex, password)) {
            signUpStatus(Color.TOMATO, "Password must have 3 to 10 characters", lblPasswordErrors);
            status = false;
        }

        return status;
    }

    //Xác nhận mật khẩu:
    public boolean isValidConfirmPassword(String password, String confirmPassword) {
        boolean status = true;

        signUpStatus(Color.GREEN, "Confirm password match", lblConfirmPasswordErrors);

        if (!confirmPassword.equals(password)) {
            signUpStatus(Color.TOMATO, "Confirm password not match", lblConfirmPasswordErrors);
            status = false;
        }

        return status;
    }

    //Thông báo kết quả kiểm tra thông tin:
    private void signUpStatus(Color color, String text, Label label) {
        label.setTextFill(color);
        label.setText(text);
    }
}