
public class Shauna extends Supporter {

	public Shauna() {
		
		setCardName("Shauna");
		setTrainerType("Supporter");
		setEffectText("Shuffle your hand into your deck. Then, draw 5 cards.");
		
	}
	
	public int supporterEffect() {
		return 4;
	}
	
}
