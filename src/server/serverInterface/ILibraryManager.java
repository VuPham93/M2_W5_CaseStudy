package server.serverInterface;

import library.java.JavaLibrary;
import library.javaScript.JavaScriptLibrary;
import library.software.SoftwareLibrary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ILibraryManager extends Remote {
    ArrayList<JavaScriptLibrary> getJSLibrary() throws RemoteException;

    ArrayList<JavaLibrary> getJavaLibrary() throws RemoteException;

    ArrayList<SoftwareLibrary> getSoftwareLibrary() throws RemoteException;

    void saveJSLibrary(ArrayList<JavaScriptLibrary> javaScriptLibraries) throws RemoteException;

    void saveJavaLibrary(ArrayList<JavaLibrary> javaLibraries) throws RemoteException;

    void saveSoftwareLibrary(ArrayList<SoftwareLibrary> softwareLibraries) throws RemoteException;
}
