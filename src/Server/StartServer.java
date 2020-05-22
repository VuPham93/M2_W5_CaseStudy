package Server;

import java.rmi.Naming;

public class StartServer {
    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);

            Server server = new Server();

            server.setFile("1(88).png");

            Naming.rebind("rmi://192.168.2.205/Download", server);

            System.out.println("Server.Server is Ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
