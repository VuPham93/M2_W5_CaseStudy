package Client;

import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class PathChooser {
    private String path;

    public void choosePath(MouseEvent event, Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select destination");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = directoryChooser.showDialog(stage);

        setPath(file.getAbsolutePath());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}