package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {
    void getData(String fileName, byte[] data, int length) throws RemoteException;
}
