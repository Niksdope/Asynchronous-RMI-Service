package ie.gmit.sw;

// A class used to store the user inputs that need to be sent up to the RMI server. Just simple getters/setters
public class Task {
	private String jobNumber;
	private String s;
	private String t;
	private String algorithm;
	
	public Task(){
		super();
	}
	
	public Task (String jobNumber, String s, String t, String algorithm){
		this();
		this.jobNumber = jobNumber;
		this.s = s;
		this.t = t;
		this.algorithm = algorithm;
	}
	
	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String getJobNumber() {
		return jobNumber;
	}
	public void setjobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
}
