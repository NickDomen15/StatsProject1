
public class PokémonCatcher extends Item {

	public PokémonCatcher() {
		
		setCardName("Pokémon Catcher");
		setTrainerType("Item");
		setEffectText("Flip a coin. If heads, switch in 1 of your opponent's Benched Pokémon to the Active Spot.");
		
	}
	
	public int itemEffect() {
		return 4;
	}
	
}
