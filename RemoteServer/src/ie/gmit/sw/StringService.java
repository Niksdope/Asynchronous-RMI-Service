package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringService extends Remote{
	public void compare(String s, String t, String algo, Resultator r) throws RemoteException;
}