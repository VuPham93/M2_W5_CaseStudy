package client.clientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGetFile extends Remote {
    void getData(String fileName, byte[] data, int length) throws RemoteException;
}
