package the08_rmi_and_corba;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Must extend the 'Remote' marker interface.
public interface FactorialInterface extends Remote {
    // Every method must declare that it can throw a RemoteException.
    long getFactorial(int number) throws RemoteException;
}