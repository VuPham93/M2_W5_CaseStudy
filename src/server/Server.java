package server;

import client.clientInterface.IGetFile;
import tools.FileReaderAndWriter;
import library.javaScript.JavaScriptLibrary;
import server.serverInterface.ILibraryManager;
import server.serverInterface.ISentFile;
import server.serverInterface.IUserManager;
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
    public boolean checkUser(String nameOrEmail, String password) throws RemoteException {
        return userChecker.isUser(nameOrEmail, password);
    }

    //Server kiểm tra user có tồn tại không:
    @Override
    public boolean checkValidUser(String userNameOrEmail) throws RemoteException {
        return userChecker.isValidUser(userNameOrEmail);
    }

    //Server lưu user mới:
    @Override
    public void saveNewUser(String name, String email, String password) throws RemoteException {
        userChecker.saveNewUser(name, email, password);
    }

    //Lấy danh sách thư viện JavaScript từ server:
    @Override
    public ArrayList<JavaScriptLibrary> getLibrary() throws RemoteException {
        FileReaderAndWriter<JavaScriptLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        return (ArrayList<JavaScriptLibrary>) fileReaderAndWriter.readFile("/src/library/javaScript/JavaScriptLibrary.txt");
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
