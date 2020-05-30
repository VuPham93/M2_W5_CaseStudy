package tools;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchPanel {

    private static double xOffset = 0;
    private static double yOffset = 0;

    //Chuyển qua lại giữa các giao diện:
    public static void switchPanel(MouseEvent event, String path) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(SwitchPanel.class.getResource(path)));
            stage.setScene(scene);
            stage.show();

            //Sự kiện kéo thả cho giao diện:
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
