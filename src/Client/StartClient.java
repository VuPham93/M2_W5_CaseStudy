package Client;

import Server.ServerIF;

import java.rmi.Naming;
import java.util.Scanner;

public class StartClient {
    public static void main(String[] args) {
        try {
            Client client = new Client("Client");
            ServerIF server = (ServerIF) Naming.lookup("rmi://192.168.2.205/Download");

            System.out.println("Connected to server");

            Scanner scanner = new Scanner(System.in);

            System.out.println("What file to copy");
            server.setFile(scanner.nextLine());

            System.out.println("Where to save?");
            client.setPath(scanner.nextLine());

            server.sendData(client);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
