package ie.gmit.sw;

import org.apache.commons.lang3.StringUtils;

public class JaroWinklerDistance implements Algorithm{
	public String distance(String s, String t){
		return "" + StringUtils.getJaroWinklerDistance(s, t);
	}
}
