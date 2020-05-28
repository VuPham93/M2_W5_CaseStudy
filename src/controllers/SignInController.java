package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {

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

    public void openHomePanel(MouseEvent event) {
        if (signIn()) {
            SwitchPanel.switchPanel(event,"/fxml/Home.fxml" );
        }
    }

    public void openSignUpPanel(MouseEvent event) {
        SwitchPanel.switchPanel(event, "/fxml/SignUp.fxml");
    }

    private boolean signIn() {
        boolean status = true;
        String name = txtUserName.getText();
        String password = txtPassword.getText();

        if (name.isEmpty() || password.isEmpty()) {
            signInStatus(Color.TOMATO, "Empty credentials");
            status = false;
        } else {
            if (!name.equals("admin") || !password.equals("123")) {
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
