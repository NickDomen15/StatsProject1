import java.util.ArrayList;

public class TestChapter2 {

	public static void main(String[] args) {
		
		Chapter2 test = new Chapter2();		
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Set Operations
		
		ArrayList<String> daysOfWeek = new ArrayList<String>();
		daysOfWeek.add("Sunday");
		daysOfWeek.add("Monday");
		daysOfWeek.add("Tuesday");
		daysOfWeek.add("Wednesday");
		daysOfWeek.add("Thursday");
		daysOfWeek.add("Friday");
		daysOfWeek.add("Saturday");
		
		ArrayList<String> subset1 = new ArrayList<String>();
		subset1.add("Monday");
		subset1.add("Wednesday");
		subset1.add("Friday");
		subset1.add("Saturday");
		
		ArrayList<String> subset2 = new ArrayList<String>();
		subset2.add("Tuesday");
		subset2.add("Wednesday");
		subset2.add("Thursday");
		subset2.add("Saturday");
		
		System.out.println("Whole set: " + daysOfWeek + "\nSubset 1: " + subset1 + "\nSubset 2: " + subset2);
		System.out.println("Union of subset 1 and subset 2: " + test.union(subset1, subset2));
		System.out.println("Intersection of subset 1 and subset 2: " + test.intersect(subset1, subset2));
		System.out.println("Complement of subset 1: " + test.complement(daysOfWeek, subset1));
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		// Permutations and Combinations

		System.out.println("\nPermutation, P(n/r): " + test.permutation(30, 3));
		System.out.println("Combination, C(n/r): " + test.combination(50, 3));
				
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		// Conditional Probability and Bayes Theorem

		try {
			double conditionalProbabilityResult = test.conditionalProbability(0.6, 0.8,  0.5);
			System.out.println("\nConditional Probability (A|B): " + conditionalProbabilityResult);
		}
		catch (InvalidPercentageException ex){
			System.out.println(ex.getMessage());
		}

		try {
			double bayesTheoremResult = test.bayesTheorem(0.327, 0.333333, 0.090909);
			System.out.println("Bayes Theorem (B|A): " + bayesTheoremResult);
		}
		catch (InvalidPercentageException ex) {
			System.out.println(ex.getMessage());
		}
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		// Determine Independence/Dependence
	
		try {
			boolean determineIndependenceResult = test.determineIndependence(0.5, 0.5, 0);
			System.out.println("\nA and B are independent: " + determineIndependenceResult);
		}
		catch (InvalidPercentageException ex){
			System.out.println(ex.getMessage());
		}
		
	}
}
