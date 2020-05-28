package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class SignUpController {

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

    public void signUp(MouseEvent event) {
        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (isValidUserName(name) &&
            isValidPassword(email) &&
            isValidPassword(password) &&
            isValidConfirmPassword(password, confirmPassword)) {

            SwitchPanel.switchPanel(event, "/fxml/SignIn.fxml");
        }
    }

    public boolean isValidUserName(String name) {
        String regex = "[a-z0-9]{1,10}";
        return Pattern.matches(regex, name);
    }

    public boolean isValidEmail(String email) {
        String regex = "[a-z0-9]{1,20}";
        return Pattern.matches(regex, email);
    }

    public boolean isValidPassword(String password) {
        String regex = "[a-z0-9]{1,20}";
        return Pattern.matches(regex, password);
    }

    public boolean isValidConfirmPassword(String password, String confirmPassword) {
        return confirmPassword.equals(password);
    }
}