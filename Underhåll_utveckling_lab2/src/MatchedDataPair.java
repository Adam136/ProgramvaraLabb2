
public class MatchedDataPair {
	
	private double xValue;
	private double yValue;
	
	public MatchedDataPair(double x, double y) {
		
		xValue = x;
		yValue = y;
	}
	
	public double getXValue() {
		
		return xValue;
	}

	public double getYValue() {
		
		return yValue;
	}
	
	@Override
	public String toString() {
		
		String tempString;
		tempString = (" X value: " + xValue + ", Y value: " +  yValue + "\n");
		return tempString;
	}
}
