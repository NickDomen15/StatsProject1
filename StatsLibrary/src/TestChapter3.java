
public class TestChapter3 {

	public static void main(String[] args) {
		
		Chapter3 test = new Chapter3();
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
		// Binomial Distribution
		
		try {
			double binomialDistributionResult = test.binomialDistribution(10, 7, 0.8);
			System.out.println("Binomial Distribution: " + binomialDistributionResult);
		}
		catch (InvalidPercentageException ex) {
			System.out.println(ex.getMessage());
		}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		// Geometric Distribution
		
		try {
			double geometricDistributionResult = test.geometricDistribution(0.5, 5);
			System.out.println("\nGeometric Distribution: " + geometricDistributionResult);
		}
		catch (InvalidPercentageException ex) {
			System.out.println(ex.getMessage());
		}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Hypergeometric Distribution
		
		try {
			double hypergeometricDistributionResult = test.hypergeometricDistribution(10, 5, 4, 0);
			System.out.println("\nHypergeometric Distribution: " + hypergeometricDistributionResult);
		}
		catch (InvalidPercentageException ex) {
			System.out.println(ex.getMessage());
		}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Negative Binomial Distribution
		
		try {
			double negativeBinomialDistributionResult = test.negativeBinomialDistribution(0.9, 2, 1);
			System.out.println("\nNegative Binomial Distribution: " + negativeBinomialDistributionResult);
		}
		catch (InvalidPercentageException ex) {
			System.out.println(ex.getMessage());
		}	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Poisson Distribution
		
		try {
			double poissonDistributionResult = test.poissonDistribution(6, 5);
			System.out.println("\nPoisson Distribution: " + poissonDistributionResult);
		}
		catch (InvalidPercentageException ex) {
			System.out.println(ex.getMessage());
		}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Tchebysheff's Theorem
		
		try {
			double tchebysheffsTheoremResult = test.tchebysheffsTheorem(0.5, 0.01, 0.52);
			System.out.println("\nTchebysheff's Theorem: " + tchebysheffsTheoremResult);
		}
		catch (InvalidPercentageException ex) {
			System.out.println(ex.getMessage());
		}
	
	}
}
