
public class CinderaceV extends PokémonV {

	public CinderaceV() {
		
		setCardName("Cinderace V");
		setMaxHP(220);
		addDamage(0);
		setType("Fire");
		setEvolutionStage(0);
		setAttack1Name("Blaze Kick");
		setAttack1Text("(F)(F)(C) Blaze Kick - 210 \nDiscard 2 (F) energy from this Pokémon.");
		setAttack1EnergyCost(3);
		setWeakness("Water");
		setResistance("None");
		setRetreatCost(2);
		
	}
	
	public int attack1Damage() {
		return 210;
	}
	
	public int attack1Effect() {
		return 4;
	}
	
}
