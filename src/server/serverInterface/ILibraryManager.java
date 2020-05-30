package server.serverInterface;

import library.javaScript.JavaScriptLibrary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ILibraryManager extends Remote {
    ArrayList<JavaScriptLibrary> getJSLibrary() throws RemoteException;
}
