package Client;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientIF {
    private static final long serialVersionUID = 1L;
    public String name;

    public Client(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public void getData(String fileName, byte[] data, int length) throws RemoteException {
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file, true);

            outputStream.write(data, 0, length);
            outputStream.flush();
            outputStream.close();

            System.out.println("Done writing data...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String sendPath() throws RemoteException {
        System.out.println("What file to download?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
