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

    public void openHomePanel(MouseEvent event) {
        if (checkSignInInfo()) {
            SwitchPanel.switchPanel(event,"/fxml/Home.fxml");
        }
    }

    public void openSignUpPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/SignUp.fxml");
    }

    private boolean checkSignInInfo() {
        boolean status = true;
        String nameOrEmail = txtUserName.getText();
        String password = txtPassword.getText();
        UserChecker userChecker = new UserChecker();

        if (nameOrEmail.isEmpty() || password.isEmpty()) {
            signInStatus(Color.TOMATO, "Empty credentials");
            status = false;
        } else {
            if (!userChecker.isUser(nameOrEmail, password)) {
                signInStatus(Color.TOMATO, "Enter Correct Email/Password");
                status = false;
            }
            else {
                signInStatus(Color.GREEN, "Login Successful..Redirecting..");
            }
        }
        return status;
    }

    private void signInStatus(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
    }

}
