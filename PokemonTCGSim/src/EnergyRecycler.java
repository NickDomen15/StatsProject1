
public class EnergyRecycler extends Item {

	public EnergyRecycler() {
		
		setCardName("Energy Recycler");
		setTrainerType("Item");
		setEffectText("Shuffle up to 5 basic Energy cards from your discard pile into your deck.");
		
	}
	
	public int itemEffect() {
		return 1;
	}
	
}
