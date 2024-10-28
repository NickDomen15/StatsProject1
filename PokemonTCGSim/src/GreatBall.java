
public class GreatBall extends Item {

	public GreatBall() {
		
		setCardName("Great Ball");
		setTrainerType("Item");
		setEffectText("Look at the top 7 cards of your deck. You may reveal a Pokémon you find there and put it into your hand. Shuffle the other cards back into your deck.");
		
	}
	
	public int itemEffect() {
		return 3;
	}
	
}
