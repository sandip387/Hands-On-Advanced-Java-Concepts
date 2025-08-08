package the08_rmi_and_corba;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FactorialClient {
    public static void main(String[] args) {
        try {
            // Get a reference to the RMI Registry on the server's machine
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Look up the remote object by its name ("find it in the phone book")
            // This returns a "stub" - a local proxy object.
            FactorialInterface factorialStub = (FactorialInterface) registry.lookup("FactorialService");
            
            // Now, call the method on the stub as if it were a local object!
            System.out.print("Enter a number to calculate its factorial: ");
            int num = new Scanner(System.in).nextInt();
            long result = factorialStub.getFactorial(num);
            
            System.out.println("Client: The factorial of " + num + " is " + result);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}