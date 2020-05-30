package server;

import client.ClientIF;
import library.FileReaderAndWriter;
import library.javaScript.JavaScriptLibrary;
import users.UserChecker;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerIF, IUserManager, ILibraryManager {
    private final UserChecker userChecker = new UserChecker();
    private String file = "";

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void sendData(ClientIF client) throws RemoteException {
        try {
            File file1 = new File(file);
            FileInputStream inputStream = new FileInputStream(file1);
            byte[] myData = new byte[1024*1024];
            int length = inputStream.read(myData);
            while (length > 0) {
                client.getData(file1.getName(), myData, length);
                length = inputStream.read(myData);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public ArrayList<JavaScriptLibrary> getJSLibrary() throws RemoteException {
        FileReaderAndWriter<JavaScriptLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        return (ArrayList<JavaScriptLibrary>) fileReaderAndWriter.readFile("/src/library/javaScript/JavaScriptLibrary.txt");
    }
}
