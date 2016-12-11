package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Basic implementation of the Resultator interface specified in the project spec.
public class ResultatorImpl extends UnicastRemoteObject implements Resultator {
	private static final long serialVersionUID = 1L;

	public ResultatorImpl() throws RemoteException {
		super();
	}

	private String result;
	private boolean processed = false;

	public String getResult() throws RemoteException {
		return result;
	}

	public void setResult(String result) throws RemoteException {
		this.result = result;
	}

	public boolean isProcessed() throws RemoteException {
		if (processed){
			return true;
		} else {
			return false;
		}
	}

	public void setProcessed() throws RemoteException {
		processed = true;
	}
}
