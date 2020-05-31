package tools;

import client.Client;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.Library;
import server.serverInterface.ISentFile;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class FileDownloadManager<LibraryType extends Library> {

    public void downLoadFile(MouseEvent event, TableView<LibraryType> tbData) throws RemoteException, NotBoundException, MalformedURLException {
        Stage stage = (Stage) tbData.getScene().getWindow();

        String selectedItemPath = getSelectedItemPath(tbData);

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

        //Lưu lại tên và địa chỉ file đã tải:
        String fileName = selectedItemPath.substring(selectedItemPath.lastIndexOf("\\")+1);
        saveDownloadList(fileName, destination);
    }


    public String fileIsDownloaded(TableView<LibraryType> tbData) {
        //Lấy tên file từ dòng đã chọn:
        String selectedItemPath = getSelectedItemPath(tbData);
        String fileName = selectedItemPath.substring(selectedItemPath.lastIndexOf("\\")+1);

        //Mở danh sách file đã lưu:
        FileReaderAndWriter<SavedFile> fileReaderAndWriter = new FileReaderAndWriter<>();
        ArrayList<SavedFile> savedFiles = (ArrayList<SavedFile>) fileReaderAndWriter.readFile("/src/library/SavedList.txt");

        //Kiểm tra xem file có nằm trong danh sách đã tải về hay không:
        for (SavedFile savedFile: savedFiles) {
            if (fileName.equals(savedFile.getName())) {
                String filePath = savedFile.getSaveLocation() + "/" + savedFile.getName();

                //Kiểm tra file có tồn tại không:
                File file = new File(filePath);
                if (file.exists()) {
                    return filePath;
                }
            }
        }
        return "File not found";
    }

    //Lưu danh sách tên và địa chỉ file đã tải:
    private void saveDownloadList(String fileName, String saveLocation) {
        SavedFile savedFile = new SavedFile(fileName, saveLocation);
        FileReaderAndWriter<SavedFile> fileReaderAndWriter = new FileReaderAndWriter<>();

        ArrayList<SavedFile> savedFiles = (ArrayList<SavedFile>) fileReaderAndWriter.readFile("/src/library/SavedList.txt");

        savedFiles.add(savedFile);

        fileReaderAndWriter.writeToFile(savedFiles, "/src/library/SavedList.txt");
    }

    //Lấy đường dẫn file trên server từ dòng bị kích đúp chuột:
    private String getSelectedItemPath (TableView<LibraryType> tbData) {
        LibraryType selectedItem = tbData.getSelectionModel().getSelectedItem();
        return selectedItem.getPath();
    }
}
