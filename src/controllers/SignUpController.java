package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import users.UserChecker;

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
        SwitchPanel.switchPanel(event, "/fxml/SignIn.fxml");
    }

    public void signUp(MouseEvent event) {
        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        UserChecker userChecker = new UserChecker();

        if (isValidUserName(name) &&
            isValidEmail(email) &&
            isValidPassword(password) &&
            isValidConfirmPassword(password, confirmPassword)) {

            userChecker.saveNewUser(name, email, password);
            SwitchPanel.switchPanel(event, "/fxml/SignIn.fxml");
        }
    }

    public boolean isValidUserName(String name) {
        String regex = "[A-Z][a-z0-9]{1,9}$";
        boolean status = true;
        signUpStatus(Color.GREEN, "Username available", lblUserErrors);
        UserChecker userChecker = new UserChecker();

        if (!Pattern.matches(regex, name)) {
            signUpStatus(Color.TOMATO, "Username must start with uppercase and 2 to 10 characters", lblUserErrors);
            status = false;
        }

        if (!userChecker.isValidUser(name)) {
            signUpStatus(Color.TOMATO, "Username already exists", lblUserErrors);
            status = false;
        }
        return status;
    }

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z][\\w]{0,15}+@gmail.com$";
        boolean status = true;
        signUpStatus(Color.GREEN, "Email available", lblEmailErrors);

        if (!Pattern.matches(regex, email)) {
            signUpStatus(Color.TOMATO, "Email must be gmail", lblEmailErrors);
            status = false;
        }
        return status;
    }

    public boolean isValidPassword(String password) {
        String regex = "[a-z0-9]{3,10}";
        boolean status = true;
        signUpStatus(Color.GREEN, "Right password form", lblPasswordErrors);

        if (!Pattern.matches(regex, password)) {
            signUpStatus(Color.TOMATO, "Password must have 3 to 10 characters", lblPasswordErrors);
            status = false;
        }
        return status;
    }

    public boolean isValidConfirmPassword(String password, String confirmPassword) {
        boolean status = true;
        signUpStatus(Color.GREEN, "Confirm password match", lblConfirmPasswordErrors);

        if (!confirmPassword.equals(password)) {
            signUpStatus(Color.TOMATO, "Confirm password not match", lblConfirmPasswordErrors);
            status = false;
        }
        return status;
    }

    private void signUpStatus(Color color, String text, Label label) {
        label.setTextFill(color);
        label.setText(text);
    }
}