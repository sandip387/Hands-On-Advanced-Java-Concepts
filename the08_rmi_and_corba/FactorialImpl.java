package the08_rmi_and_corba;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Must extend UnicastRemoteObject to handle network communication.
public class FactorialImpl extends UnicastRemoteObject implements FactorialInterface {
    
    // Must have a constructor that throws RemoteException.
    public FactorialImpl() throws RemoteException {
        super();
    }
    
    // The actual implementation of the remote method.
    @Override
    public long getFactorial(int number) throws RemoteException {
        if (number < 0) return -1;
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        System.out.println("Server: Calculation complete for " + number);
        return factorial;
    }
}