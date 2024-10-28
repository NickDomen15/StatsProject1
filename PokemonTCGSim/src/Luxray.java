
public class Luxray extends Pokémon {

	public Luxray() {
		
		setCardName("Luxray");
		setMaxHP(160);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(2);
		setPreEvolution("Luxio");
		setAttack1Name("Thunder Claws");
		setAttack1Text("(L)(C) Thunder Claws - 90");
		setAttack1EnergyCost(2);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(0);
		
	}
	
	public int attack1Damage() {
		return 90;
	}
	
}
