import java.util.ArrayList;

/**
 * Chapter1 --- methods for 
 * formulas from chapter 1
 * @author Nick Domenico
 */
public class Chapter1 {
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Mean
	
	/**
	 * Returns the mean of the values 
	 * in an ArrayList of double values.
	 * @param ArrayList<Double> userInputNumbers
	 * @return double
	 */
	public double findMean(ArrayList<Double> userInputNumbers) {
		double sum = 0;
		
		for(double singleElement : userInputNumbers) {
			sum = sum + singleElement;
		}
		
		//storing a value for clarity
		double result = sum / userInputNumbers.size();
		
		return result;
	}
	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Median
	
	/**
	 * Returns the median of the values
	 * from an ArrayList of double values.
	 * @param ArrayList<Double> userInputNumbers
	 * @return double
	 */
	public double findMedian(ArrayList<Double> userInputNumbers) {
		
		return userInputNumbers.get(userInputNumbers.size()/2);
	}
	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	// Mode
	
	/**
	 * Returns the mode of the values
	 * of an ArrayList of double values.
	 * @param ArrayList<Double> userInputNumbers
	 * @return double
	 */
	public double findMode(ArrayList<Double> userInputNumbers) {
		int maxCount = 0;
		double mode = 0;
		
		for(int i = 0; i < userInputNumbers.size(); i++) {
			int count = 0;
			double value = userInputNumbers.get(i);
	
			for(int j = 0; j < userInputNumbers.size(); j++) {
				if (value == userInputNumbers.get(j)) {
					count++;
				}
			}
				
			if (count > maxCount) {
				maxCount = count;
				mode = userInputNumbers.get(i);
			}
		}
			
		return mode;	
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Standard Deviation	
	
	/**
	 * Returns the standard deviation for the 
	 * values of an ArrayList of double values.
	 * @param ArrayList<Double> userInputNumbers
	 * @return double
	 */
	public double findStandardDeviation(ArrayList<Double> userInputNumbers) {
		double sum = 0;
		
		for(double element : userInputNumbers) {
			sum = sum + (Math.pow((element - findMean(userInputNumbers)), 2));
		}
		
		double standardDeviation = Math.sqrt(sum/(userInputNumbers.size()-1));
		return standardDeviation;
	}
	
}
