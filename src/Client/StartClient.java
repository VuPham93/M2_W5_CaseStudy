package Client;

import Server.ServerIF;

import java.rmi.Naming;
import java.util.Scanner;

public class StartClient {
    public static void main(String[] args) {
        try {
            Client client = new Client("Client");
            ServerIF server = (ServerIF) Naming.lookup("rmi://192.168.2.205/Download");
            server.sendData(client);

            System.out.println("Listening......");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String line = scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
