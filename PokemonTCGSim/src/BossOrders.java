
public class BossOrders extends Supporter {

	public BossOrders() {
		
		setCardName("Boss's Orders");
		setTrainerType("Supporter");
		setEffectText("Switch 1 of your opponent's Benched Pokémon with their Active Pokémon.");
		
	}
	
	public int supporterEffect() {
		return 1;
	}
	
}
