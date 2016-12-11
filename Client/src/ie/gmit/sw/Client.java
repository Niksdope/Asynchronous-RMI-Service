package ie.gmit.sw;

import java.rmi.Naming;
import java.util.Map;
import java.util.Queue;

public class Client implements Runnable {
	private Queue<Task> inqueue;
	private Map<String, Resultator> outqueue;
	
	public Client(Queue<Task> in, Map<String, Resultator> out){
		this.inqueue = in;
		this.outqueue = out;
	}
	
	@Override
	public void run() {
		// Keep the thread running indefinitely
		while(true){
			// Hold the entire RMI process inside the try/catch so I don't have to keep try catching small blocks of code
			// 1. Get a hold of the StringService at localhost:1099/comparisonService
			// 2. Check if there are any tasks in the inqueue.
			// 3. If there is, send off the details to be processed remotely.
			// 4. Wait until processing is finished and repeaet the steps
			try {
				StringService ss = (StringService) Naming.lookup("rmi://localhost:1099/comparisonService");
				
				Task task = inqueue.poll();
				
				if(task != null){
					ss.compare(task.getS(), task.getT(), task.getAlgorithm(), outqueue.get(task.getJobNumber()));
					
					// For the illusion of an asynchronous system, I put the thread to sleep for 5 seconds before looking for a new Task in the queue
					Thread.sleep(5000);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
