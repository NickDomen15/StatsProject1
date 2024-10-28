
public class Switch extends Item {

	public Switch() {
		
		setCardName("Switch");
		setTrainerType("Item");
		setEffectText("Switch your Active Pokémon with 1 of your Benched Pokémon.");
		
	}
	
	public int itemEffect() {
		return 6;
	}
	
}
