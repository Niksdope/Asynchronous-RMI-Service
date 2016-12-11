package ie.gmit.sw;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServiceHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String remoteHost = null;
	private static long jobNumber = 0;
	// Static in-queue, out-queue so that only one is being passed around the client and service handler, and a worker thread of type Client
	private static Queue<Task> inqueue = new LinkedList<Task>();
	private static Map<String, Resultator> outqueue = new LinkedHashMap<String, Resultator>();
	private Thread worker = new Thread(new Client(inqueue, outqueue));

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
		// If the thread isn't already alive, start one (The point being to have only one of these (Less memory consumption but also less efficient))
		if (!worker.isAlive()){
			worker.start();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Initialise some request varuables with the submitted form info. These are local to this method and thread safe...
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");

		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		// This if happens only once. Sets the taskNumber, adds stuff to in-queue/out-queue and prints the loadingPage with refresh script
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			Task task = new Task(taskNumber, s, t, algorithm);
			Resultator r = new ResultatorImpl();
			inqueue.offer(task);
			outqueue.put(taskNumber, r);
			printLoadingPage(resp, s, t, algorithm, taskNumber);
		}else{
			//Check out-queue for finished job. If finished, print finishedPage, else printLoadingPage and sleep for 5seconds before checking again
			if(outqueue.get(taskNumber) != null && outqueue.get(taskNumber).isProcessed()){
				printFinishedPage(resp, taskNumber, s, t, algorithm);
			}
			else {
				printLoadingPage(resp, s, t, algorithm, taskNumber);
				try {
					System.out.println(outqueue.get(taskNumber).isProcessed());
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
	
	public void printLoadingPage(HttpServletResponse resp, String s, String t, String algorithm, String taskNumber) throws IOException{
		PrintWriter out = resp.getWriter();
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "...</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
		out.print("</b></font>");
		
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
	}
	
	public void printFinishedPage(HttpServletResponse resp, String taskNumber, String s, String t, String algorithm) throws IOException{
		PrintWriter out = resp.getWriter();
		out.print("<H1>Request for Job#: " + taskNumber + " finished!</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
		out.print("<br>" + algorithm + ": " + outqueue.get(taskNumber).getResult());
		out.print("</b></font>");
		out.print("</body>");	
		out.print("</html>");
	}
}