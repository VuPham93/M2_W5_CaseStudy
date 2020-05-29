import Client.Client;
import Server.ServerIF;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.Naming;
import java.util.Scanner;

public class Main extends Application{
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/JavaScript.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);

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

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        try {
            Client client = new Client("Client");
            ServerIF server = (ServerIF) Naming.lookup("rmi://192.168.2.205/Download");

            System.out.println("Connected to server");

            Scanner scanner = new Scanner(System.in);

            System.out.println("What file to copy");
            server.setFile(scanner.nextLine());

            System.out.println("Where to save?");
            client.setPath(scanner.nextLine());

            server.sendData(client);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
