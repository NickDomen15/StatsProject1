
public class Sonia extends Supporter {

	public Sonia() {
		
		setCardName("Sonia");
		setTrainerType("Supporter");
		setEffectText("Search your deck for up to 2 Basic Pokémon or up to 2 basic Energy cards, reveal them, and put them into your hand. Then, shuffle your deck.");
		
	}
	
	public int supporterEffect() {
		return 5;
	}
	
}
