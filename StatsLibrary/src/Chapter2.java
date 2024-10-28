import java.util.ArrayList;
import java.math.BigInteger;

/**
 * Chapter2 --- methods for
 * formulas from chapter 2
 * @author Nick Domenico
 */

// Possible extra credit: exceptions
public class Chapter2 {
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	// Set Operations
	
	/**
	 * Returns the union of 2 ArrayLists.
	 * @param ArrayList<String> setOne
	 * @param ArrayList<String> setTwo
	 * @return ArrayList<String>
	 */
	public ArrayList<String> union(ArrayList<String> setOne, ArrayList<String> setTwo) {
		
		ArrayList<String> unionList = new ArrayList<String>();
	
		/*
		 * Adds elements of setOne to unionList, excluding
		 * elements that also appear in setTwo
		 */
		for (int i = 0; i < setOne.size(); i++) {
			unionList.add(setOne.get(i));
			for (int j = 0; j < setTwo.size(); j++) {
				if (setOne.get(i) == setTwo.get(j)) {
					unionList.remove(unionList.size()-1);
				}
			}
		}
		// Adds elements of setTwo to unionList
		for (int i = 0; i < setTwo.size(); i++) {
			unionList.add(setTwo.get(i));
		}
		
		return unionList;
	}
	
	
	/**
	 * Returns the intersection of two ArrayLists.
	 * @param ArrayList<String> setOne
	 * @param ArrayList<String> setTwo
	 * @return ArrayList<String>
	 */
	public ArrayList<String> intersect(ArrayList<String> setOne, ArrayList<String> setTwo) {
		
		ArrayList<String> intersectionList = new ArrayList<String>();
		
		// Adds elements that appear in both setOne and setTwo to intersectionList
		for (int i = 0; i < setOne.size(); i++) {
			for (int j = 0; j < setTwo.size(); j++) {
				if (setOne.get(i) == setTwo.get(j)) {
					intersectionList.add(setOne.get(i));
				}
			}
		}
		
		return intersectionList;
	}
	
	
	/**
	 * Returns the complement of an ArrayList 
	 * that is a subset of another ArrayList.
	 * @param ArrayList<String> allValues
	 * @param ArrayList<String> subset
	 * @return ArrayList<String>
	 */
	public ArrayList<String> complement(ArrayList<String> allValues, ArrayList<String> subset) {
		
		ArrayList<String> complementList = new ArrayList<String>();
		
		/*
		 * Checks allValues to see if each value appears in the subset.
		 * If a value does not appear in the subset, 
		 * it is added to the complementList
		 */
		for (int i = 0; i < allValues.size(); i++) {
			boolean inSubset = false;
			for (int j = 0; j < subset.size(); j++) {
				if (allValues.get(i) == subset.get(j)) {
					inSubset = true;
				}
			}
			if (inSubset == false) {
				complementList.add(allValues.get(i));
			}
		}
		
		return complementList;
	}

	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	// Permutations and Combinations
	
	/**
	 * Returns the factorial of an int input.
	 * @param int input
	 * @return BigInteger
	 */
	/*
	 *  code used from:
	 *  https://javarevisited.blogspot.com/2015/08/how-to-calculate-large-factorials-using-BigInteger-Java-Example.html#axzz8HdeQmnni
	 */
	public BigInteger factorial(int input) {
		
		BigInteger result = new BigInteger("1");
		
		for (int i = input; i > 0; i--) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		
		return result;
	}
	
	
	/**
	 * Returns the permutation of n choose r.
	 * @param int n
	 * @param int r
	 * @return BigInteger
	 */
	public BigInteger permutation(int n, int r) {
		
		BigInteger result = factorial(n).divide(factorial(n-r));
		
		return result;
	}
	
	
	/**
	 * Returns the combination of n choose r.
	 * @param int n
	 * @param int r
	 * @return BigInteger
	 */
	public BigInteger combination(int n, int r) {
		
		BigInteger result = factorial(n).divide((factorial(r).multiply(factorial(n-r))));
		
		return result;
	}
	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	// Conditional Probability and Bayes Theorem

	/**
	 * Returns the conditional probability of A|B.
	 * @param double A
	 * @param double B
	 * @param double C
	 * @return double
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentage is negative or 
	 *         greater than 1
	 */
	public double conditionalProbability(double A, double B, double union) throws InvalidPercentageException {
		
		double result = 0;
		
		if (union == 0) {
			result = (A * B) / B;
		}
		else {
			result = union / B;
		}
		
		if (result < 0 || result > 1) {
			throw new InvalidPercentageException("\nPercentage cannot be negative or greater than 1");
		}
		
		else {
			return result;
		}
	}
	
	
	/**
	 * Returns the Bayes Theorem probability of B|A.
	 * @param double B
	 * @param double A
	 * @return double
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentage is negative or 
	 *         greater than 1
	 */
	public double bayesTheorem(double A, double B, double conditional) throws InvalidPercentageException {
		
		double result = ((conditional * B) / A);
		
		if (result < 0 || result > 1) {
			throw new InvalidPercentageException("\nPercentage cannot be negative or greater than 1");
		}
		
		else {
			return result;
		}
	}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	// Independence and Dependence
	
	/**
	 * Returns true if A and B are independent 
	 * and false if they are dependent.
	 * @param double A
	 * @param double B
	 * @return boolean
	 * @throws InvalidPercentageException - if the 
	 *         resulting percentages of either of the 
	 *         conditional probabilities are negative or 
	 *         greater than 1
	 */
	public boolean determineIndependence(double A, double B, double union) throws InvalidPercentageException {
		
		if (conditionalProbability(A, B, union) == A || conditionalProbability(B, A, union) == B) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
}