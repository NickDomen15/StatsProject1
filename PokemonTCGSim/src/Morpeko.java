
public class Morpeko extends Pokémon {

	public Morpeko() {
		
		setCardName("Morpeko");
		setMaxHP(80);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(0);
		setAttack1Name("Targeted Spark");
		setAttack1Text("(L)(C) Targeted Spark \nThis attack does 30 damage to one of your opponent's Pokemon. (Don't apply Weakness and Resistance for Benched Pokemon).");
		setAttack1EnergyCost(2);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(1);
		
	}
	
	public int attack1Damage() {
		return 0;
	}
	
	public int attack1Effect() {
		return 6;
	}
	
}
