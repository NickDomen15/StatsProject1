
public class EnergyRetrieval extends Item {

	public EnergyRetrieval() {
		
		setCardName("Energy Retrieval");
		setTrainerType("Item");
		setEffectText("Put up to 2 Basic Energy cards from your discard pile into your hand.");
		
	}
	
	public int itemEffect() {
		return 2;
	}
	
}
