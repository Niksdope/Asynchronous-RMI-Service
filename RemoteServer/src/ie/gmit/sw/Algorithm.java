package ie.gmit.sw;

// An interface for all the algorithms. This works because at the end of the day they'll all be calculating distance.
// It returns a String because not all the algos return ints, but you can put any simple types into a String
public interface Algorithm {
	String distance(String s, String t);
}