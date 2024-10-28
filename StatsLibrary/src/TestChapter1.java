import java.util.ArrayList;

public class TestChapter1 {

	public static void main(String[] args) {
		
		Chapter1 test = new Chapter1();
		
		ArrayList<Double> testNumbers = new ArrayList<Double>();
		testNumbers.add(4.0);
		testNumbers.add(8.0);
		testNumbers.add(15.0);
		testNumbers.add(15.0);
		testNumbers.add(16.0);
		testNumbers.add(23.0);
		testNumbers.add(42.0);
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		// Mean
		
		double meanTestResults = test.findMean(testNumbers);
		System.out.println("This is the average of testNumbers: " + meanTestResults);
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Median
		
		double medianTestResults = test.findMedian(testNumbers);
		System.out.println("\nThis is the median of testNumbers: " + medianTestResults);
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Mode
		
		double modeTestResults = test.findMode(testNumbers);
		System.out.println("\nThis is the mode of testNumbers: " + modeTestResults);
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Standard Deviation
		
		double standardDeviationTestResults = test.findStandardDeviation(testNumbers);
		System.out.println("\nThis is the standard deviation of testNumbers: " + standardDeviationTestResults);
		
	}
}
