package the08_rmi_and_corba;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FactorialServer {
    public static void main(String[] args) {
        try {
            // Create an instance of our implementation
            FactorialImpl remoteObject = new FactorialImpl();
            
            // Start the RMI Registry (the "phone book") on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Bind our object to a name in the registry ("list it in the phone book")
            registry.bind("FactorialService", remoteObject);
            
            System.out.println("Factorial Server is ready and waiting for clients...");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}