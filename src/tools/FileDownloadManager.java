package tools;

import client.Client;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.javaScript.JavaScriptLibrary;
import server.serverInterface.ISentFile;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FileDownloadManager {
    public void downLoadFile(MouseEvent event, TableView<JavaScriptLibrary> tbData) throws RemoteException, NotBoundException, MalformedURLException {
        //Lấy thông tin vị trí file trên server từ dòng bị kích đúp chuột:
        Stage stage = (Stage) tbData.getScene().getWindow();
        JavaScriptLibrary selectedItem = tbData.getSelectionModel().getSelectedItem();
        String selectedItemPath = selectedItem.getPath();

        //Gọi interface gửi file đã lấy được địa chỉ từ server:
        ISentFile server = (ISentFile) Naming.lookup("rmi://192.168.1.68/Server");
        server.getRequestedFile(selectedItemPath);

        //Mở của sổ để chọn địa chỉ lưu file trên client:
        PathChooser pathChooser = new PathChooser();
        try {
            pathChooser.choosePath(event, stage);
        } catch (Exception e) {
            System.out.println("No chosen path");
        }
        String destination = pathChooser.getPath();

        //Gọi lại client để nhận file:
        Client client = new Client("client");
        client.setSaveLocation(destination);

        //Server gửi file cho client:
        server.sendData(client);
    }
}
