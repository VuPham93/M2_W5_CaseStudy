package server;

import client.ClientIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
    public void sendData(ClientIF c) throws RemoteException;

    public void setFile(String client) throws RemoteException;
}
