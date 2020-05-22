package Server;

import Client.ClientIF;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerIF {
    private String file = "";

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void sendData(ClientIF client) throws RemoteException {
        try {
            File file1 = new File(file);
            FileInputStream inputStream = new FileInputStream(file1);
            byte[] myData = new byte[1024*1024];
            int length = inputStream.read(myData);
            while (length > 0) {
                client.getData(file1.getName(), myData, length);
                length = inputStream.read(myData);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
