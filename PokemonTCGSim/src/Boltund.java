
public class Boltund extends Pokémon {

	public Boltund() {
		
		setCardName("Boltund");
		setMaxHP(120);
		addDamage(0);
		setType("Lightning");
		setEvolutionStage(1);
		setPreEvolution("Yamper");
		setAttack1Name("Bite");
		setAttack1Text("(C)(C) Bite - 50");
		setAttack1EnergyCost(2);
		setAttack2Name("Electrodash");
		setAttack2Text("(L)(L)(C) Electrodash - 160 \nDuring your next turn, this Pokemon can't attack.");
		setAttack2EnergyCost(3);
		setWeakness("Fighting");
		setResistance("None");
		setRetreatCost(0);
		
	}
	
	public int attack1Damage() {
		return 50;
	}
	
	public int attack2Damage() {
		return 160;
	}
	
	public int attack2Effect() {
		return 8;
	}
	
}
