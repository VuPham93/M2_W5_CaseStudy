package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserManager extends Remote {
    boolean checkUser(String nameOrEmail, String password) throws RemoteException;

    boolean checkValidUser(String input) throws RemoteException;

    void saveNewUser(String name, String email, String password) throws RemoteException;
}
