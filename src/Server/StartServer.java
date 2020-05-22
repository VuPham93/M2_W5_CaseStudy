package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class StartServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            Server server = new Server();

            Naming.rebind("rmi://192.168.2.205/Download", server);

            System.out.println("Server is Ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
