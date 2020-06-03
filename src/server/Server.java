package server;

import client.clientInterface.IGetFile;
import library.java.JavaLibrary;
import library.javaScript.JavaScriptLibrary;
import library.software.SoftwareLibrary;
import server.serverInterface.ILibraryManager;
import server.serverInterface.ISentFile;
import server.serverInterface.IUserManager;
import tools.FileReaderAndWriter;
import tools.FinalList;
import users.User;
import users.UserChecker;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ISentFile, IUserManager, ILibraryManager {
    private final UserChecker userChecker = new UserChecker();
    private String originalFile;

    protected Server() throws RemoteException {
        super();
    }

    //Server kiểm tra thông tin đăng nhập:
    @Override
    public String checkUser(String nameOrEmail, String password) throws RemoteException {
        return userChecker.isUser(nameOrEmail, password);
    }

    //Server kiểm tra user có tồn tại không:
    @Override
    public boolean checkValidUser(String userNameOrEmail) throws RemoteException {
        return userChecker.isValidUser(userNameOrEmail);
    }

    @Override
    public boolean checkOldUser(String name, String email) throws RemoteException {
        return userChecker.isOldUser(name, email);
    }

    //Server lưu user mới:
    @Override
    public void saveNewUser(String name, String email, String password) throws RemoteException {
        userChecker.saveNewUser(name, email, password);
    }

    @Override
    public void saveUserInfo(String name, String email, String password) throws RemoteException {
        userChecker.saveUserInfo(name, email, password);
    }

    @Override
    public void saveUserList(ArrayList<User> userList) throws RemoteException {
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();
        fileReaderAndWriter.writeToFile(userList, FinalList.USER_SAVE_LOCATION);
    }

    @Override
    public ArrayList<User> getUserList() throws RemoteException {
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();
        return (ArrayList<User>) fileReaderAndWriter.readFile(FinalList.USER_SAVE_LOCATION);
    }

    //Lấy danh sách thư viện JavaScript từ server:
    @Override
    public ArrayList<JavaScriptLibrary> getJSLibrary() throws RemoteException {
        FileReaderAndWriter<JavaScriptLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        return (ArrayList<JavaScriptLibrary>) fileReaderAndWriter.readFile(FinalList.JAVASCRIPT_LIBRARY);
    }

    //Lấy danh sách thư viện Java từ server:
    @Override
    public ArrayList<JavaLibrary> getJavaLibrary() throws RemoteException {
        FileReaderAndWriter<JavaLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        return (ArrayList<JavaLibrary>) fileReaderAndWriter.readFile(FinalList.JAVA_LIBRARY);
    }

    //Lấy danh sách thư viện Software từ server:
    @Override
    public ArrayList<SoftwareLibrary> getSoftwareLibrary() throws RemoteException {
        FileReaderAndWriter<SoftwareLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        return (ArrayList<SoftwareLibrary>) fileReaderAndWriter.readFile(FinalList.SOFTWARE_LIBRARY);
    }

    @Override
    public void saveJSLibrary(ArrayList<JavaScriptLibrary> javaScriptLibraries) throws RemoteException {
        FileReaderAndWriter<JavaScriptLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        fileReaderAndWriter.writeToFile(javaScriptLibraries, FinalList.JAVASCRIPT_LIBRARY);
    }

    @Override
    public void saveJavaLibrary(ArrayList<JavaLibrary> javaLibraries) throws RemoteException {
        FileReaderAndWriter<JavaLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        fileReaderAndWriter.writeToFile(javaLibraries, FinalList.JAVA_LIBRARY);
    }

    @Override
    public void saveSoftwareLibrary(ArrayList<SoftwareLibrary> softwareLibraries) throws RemoteException {
        FileReaderAndWriter<SoftwareLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        fileReaderAndWriter.writeToFile(softwareLibraries, FinalList.SOFTWARE_LIBRARY);
    }

    //Lấy file cần chuyển đi:
    @Override
    public void getRequestedFile(String fileName) throws RemoteException {
        this.originalFile = fileName;
    }

    //Chuyển file cho client:
    @Override
    public void sendData(IGetFile client) throws RemoteException {
        try {
            File sendFile = new File(originalFile);
            FileInputStream inputStream = new FileInputStream(sendFile);
            byte[] myData = new byte[1024*1024];
            int length = inputStream.read(myData);

            while (length > 0) {
                client.getData(sendFile.getName(), myData, length);
                length = inputStream.read(myData);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
