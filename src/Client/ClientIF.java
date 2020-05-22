package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {
    public void getData(String fileName, byte[] data, int length) throws RemoteException;

    public String getName() throws RemoteException;
}
