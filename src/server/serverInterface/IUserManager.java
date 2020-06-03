package server.serverInterface;

import users.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IUserManager extends Remote {
    String checkUser(String nameOrEmail, String password) throws RemoteException;

    boolean checkValidUser(String input) throws RemoteException;

    boolean checkOldUser(String name, String email) throws RemoteException;

    void saveNewUser(String name, String email, String password) throws RemoteException;

    void saveUserInfo(String name, String email, String password) throws RemoteException;

    void saveUserList(ArrayList<User> userList) throws RemoteException;

    ArrayList<User> getUserList() throws RemoteException;
}
