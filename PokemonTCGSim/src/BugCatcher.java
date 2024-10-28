
public class BugCatcher extends Supporter {

	public BugCatcher() {
		
		setCardName("Bug Catcher");
		setTrainerType("Supporter");
		setEffectText("Draw 2 cards. Flip a coin. If heads, draw 2 more cards.");
		
	}
	
	public int supporterEffect() {
		return 2;
	}
	
}
