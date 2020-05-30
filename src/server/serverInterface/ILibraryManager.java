package server.serverInterface;

import library.Library;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ILibraryManager<LibraryType extends Library> extends Remote {
    ArrayList<LibraryType> getLibrary() throws RemoteException;
}
