
/**
 * Chapter4 --- methods for
 * formulas from Chapter 4
 * @author Nick Domenico
 */

public class Chapter4 {

	/**
	 * Returns the result of 
	 * a uniform distribution
	 * @param double a
	 * @param double b
	 * @param double c
	 * @param double d
	 * @return double
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentage is negative or 
	 *         greater than 1
	 */
	public double uniformDistribution(double a, double b, double c, double d) throws InvalidPercentageException {
		
		double result = (d - c) / (b - a);
		
		if (result < 0 || result > 1) {
			throw new InvalidPercentageException("\nPercentage cannot be negative or greater than 1");
		}
		else {
			return result;
		}
	}
	
	
}
