package client;

import server.ServerIF;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.Naming;

public class StartClient extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignIn.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);

        //Sự kiện kéo thả cho phần mềm:
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        //Hiển thị giao diện:
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Khởi chạy phần mềm:
    public static void main(String[] args) {
        try {
            Client client = new Client("client");
            ServerIF server = (ServerIF) Naming.lookup("rmi://192.168.1.68/Server");

            System.out.println("Connected to server");

            //Bật màn hình đăng nhập:
            launch(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
