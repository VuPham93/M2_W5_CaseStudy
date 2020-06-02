package client;

import client.clientInterface.IGetFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements IGetFile {
    private static final long serialVersionUID = 1L;
    public String name;
    public String saveLocation;

    public Client(String name) throws RemoteException {
        super();
        this.name = name;
    }

    //Lấy file từ server:
    @Override
    public void getData(String fileName, byte[] data, int length) throws RemoteException {
        try {
            File file = new File(saveLocation + "/" + fileName);
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file, true);

            outputStream.write(data, 0, length);
            outputStream.flush();
            outputStream.close();

            System.out.println("Done writing data...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Xác định vị trí lưu file:
    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }
}
