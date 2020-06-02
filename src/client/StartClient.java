package client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tools.FinalList;

public class StartClient extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FinalList.SIGN_IN_PANEL));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);

        //Sự kiện kéo thả cho giao diện:
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
            System.out.println("Connecting to server");
            //Bật màn hình đăng nhập:
            launch(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
