
public class Potion extends Item {

	public Potion() {
		
		setCardName("Potion");
		setTrainerType("Item");
		setEffectText("Heal 30 damage from 1 of your Pokémon.");
		
	}
	
	public int itemEffect() {
		return 5;
	}
	
}
