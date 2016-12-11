package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
// Just creating a registry on this ip, at the port 1099 and adding a service "comparisonService" to it
public class RemoteServer {
	public static void main(String[] args) throws Exception{
		StringService ss = new StringServiceImpl();
		
		LocateRegistry.createRegistry(1099);
		Naming.rebind("comparisonService", ss);
		
		System.out.println("RMI Server awaiting requests...");
	}
}
