package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class StringServiceImpl implements StringService{
	// Chain of responsibility
	private List<Node> cor = new LinkedList<Node>();

	public StringServiceImpl() throws RemoteException {
		super();
		UnicastRemoteObject.exportObject(this, 7777);
		
		// Add new Nodes (Algorithms) to the chain of responsibility when initialized
		cor.add(new Node(new Levenshtein(), "Levenshtein Distance"));
		cor.add(new Node(new DamerauLevenshtein(), "Damerau-Levenshtein Distance"));
		cor.add(new Node(new HammingDistance(), "Hamming Distance"));
		cor.add(new Node(new JaroWinklerDistance(), "Jaro-Winkler Distance"));
	}
	
	public void compare(String s, String t, String algo, Resultator r) throws RemoteException {
		System.out.println("Got request for string '" + s + "' and string '" + t +"'");
		
		try {
			// Put the thread to sleep for 5 seconds to simulate asynchronisity
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// For each node in the chain of responsibility, check if the current one is the algorithm the user is looking for
		// If it is, calculate the distance and update the Resultator to have the result and to "be" processed
		for (Node n : cor){
			if (n.getName().equals(algo)){
				String distance = n.getAlgorithm().distance(s, t);
				r.setResult(distance);
				System.out.println("Found algorithm, and calculated distance");
			}
		}
		
		System.out.println("Setting resultator to isProcessed");
		r.setProcessed();
	}
	// Nested class Node. Only used here so there is no need to make it anything more. Holds an algorithm and a name of that algo.
	private class Node{
		private Algorithm a;
		private String name;
		
		public Node(Algorithm a, String name) {
			super();
			this.a = a;
			this.name = name;
		}
		
		public Algorithm getAlgorithm() {
			return a;
		}
		
		public String getName() {
			return name;
		}
	}
}
