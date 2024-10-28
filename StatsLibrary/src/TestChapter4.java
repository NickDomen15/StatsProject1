
public class TestChapter4 {

	public static void main(String[] args) {
		
		Chapter4 test = new Chapter4();
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		// Uniform Distribution
		
		try {
			double uniformDistributionResult = test.uniformDistribution(0, 30, 25, 30);
			System.out.println("Uniform Distribution: " + uniformDistributionResult);
		}
		catch (InvalidPercentageException ex){
			System.out.println(ex.getMessage());
		}
		
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		
	}
}
