package server.serverInterface;

import client.clientInterface.IGetFile;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISentFile extends Remote {
    void sendData(IGetFile file) throws RemoteException;

    void getRequestedFile(String fileName) throws RemoteException;
}
