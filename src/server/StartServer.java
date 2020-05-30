package server;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class StartServer {
    public static void main(String[] args) {
        try {
            String ip = ((InetAddress.getLocalHost()).getHostAddress()).trim();
            System.out.println("System IP Address : " + ip);

            LocateRegistry.createRegistry(1099);

            Server server = new Server();

            Naming.rebind("rmi://" + ip + "/Server", server);

            System.out.println("Server is Ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
