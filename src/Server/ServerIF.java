package Server;

import Client.ClientIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
    public void sendData(ClientIF c) throws RemoteException;
}
